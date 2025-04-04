package com.thesis.serverfurnitureecommerce.common.filter;
import com.thesis.serverfurnitureecommerce.common.logs.AppVersionLogger;
import com.thesis.serverfurnitureecommerce.domain.exception.CustomBadRequestException;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class RequestWrapper extends HttpServletRequestWrapper {

    private static final AppVersionLogger log = new AppVersionLogger(LoggerFactory.getLogger(RequestWrapper.class));
    private String body = null;

    public static final String exceptionCase = "(\"reason\":)(\\s*)(\"[^,]*<(?:\"[^\"]\"['\"]*|'[^']*'['\"]*|[^'\">])+>.*?\",)";

    public static final List<String> regexList = Collections.unmodifiableList(
            new ArrayList<String>() {{
                add("<(?:\"[^\"]*\"['\"]*|'[^']*'['\"]*|[^'\">])+>");
                add("eval\\((.*)\\)");
                add("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']");
                add("(?i)<script.*?>.*?<script.*?>");
                add("(?i)<script.*?>.*?</script.*?>");
                add("(?i)<.*?javascript:.*?>.*?</.*?>");
                add("(?i)<.*?\\s+on.*?>.*?</.*?>");
                add("<script>");
                add("</script>");
                add("%3cscript%3e");
                add("alert\\(.*?\\)");
                add("\">");
            }});
    public static final Map<String, List<String>> byPassSanitizePaths = new HashMap<String, List<String>>() {{
        put("content/(.*?)/update", new ArrayList<String>() {{
            add("content");
            add("content_body");
        }});
        put("content/create", new ArrayList<String>() {{
            add("content");
            add("content_body");
        }});
    }};
    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);

        String contentType = getContentType(request.getHeader("content-type"));
        boolean isAjaxRequest = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
        boolean isSanitize = "true".equals(request.getHeader("need-sanitize"));

        if (isSanitize || (!contentType.contains("urlencoded") && !contentType.contains("multipart/form-data") && !isAjaxRequest)) {
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader bufferedReader = null;
            try {
                InputStream inputStream = request.getInputStream();
                if (inputStream != null) {
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    char[] charBuffer = new char[128];
                    int bytesRead = -1;
                    while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                        stringBuilder.append(charBuffer, 0, bytesRead);
                    }

                } else {
                    stringBuilder.append("");
                }
            } catch (IOException ex) {
                throw ex;
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException ex) {
                        throw ex;
                    }
                }
            }
            // Store request body content in 'requestBody' variable
            String requestBody = stringBuilder.toString();
            sanitizeValue(requestBody);
            body = requestBody;
        }
    }

    private static String getContentType(String header) {
        if(StringUtils.isEmpty(header)){
            return "application/x-www-form-urlencoded" ;
        }
        return header;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (body == null) {
            return super.getInputStream();
        }
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        ServletInputStream servletInputStream = new ServletInputStream() {
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }
        };
        return servletInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = sanitizeValue(values[i]);
        }
        return encodedValues;
    }

    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        String path = super.getRequestURI();

        if (StringUtils.isEmpty(value) || isByPassSanitize(path, parameter)) {
            return value;
        }
        return sanitizeValue(value);
    }

    private String sanitizeValue(String value) {
        String tmpValue = value.replaceAll(exceptionCase, "");
        if(StringUtils.isNotEmpty(tmpValue)){
            for (String regex : regexList) {
                if (Pattern.compile(regex).matcher(tmpValue.toLowerCase()).find()) {
                    throw new CustomBadRequestException();
                }
            }
            value = value.replaceAll("'", " ");
        }
        return value;
    }

    private String getByPassSanitizePath(String path, List<String> byPassPaths) {
        if(StringUtils.isNotEmpty(path)){
            for(String byPassPath: byPassPaths){
                if(Pattern.compile(byPassPath).matcher(path.toLowerCase()).find()){
                    return byPassPath;
                }
            }
        }

        return "";
    }

    private boolean isByPassSanitizeParam(String param, List<String> byPassParams) {
        if(StringUtils.isNotEmpty(param)){
            for(String byPassParam: byPassParams){
                if(byPassParam.equals(param.toLowerCase())){
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isByPassSanitize(String path, String param) {
        List<String> byPassPaths = new ArrayList<String>(byPassSanitizePaths.keySet());
        String byPassPath = getByPassSanitizePath(path, byPassPaths);

        if (byPassPath.isEmpty()) {
            return false;
        }

        List<String> byPassParams = byPassSanitizePaths.get(byPassPath);
        return isByPassSanitizeParam(param, byPassParams);
    }
}
