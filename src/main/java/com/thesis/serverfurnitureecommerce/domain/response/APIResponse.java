package com.thesis.serverfurnitureecommerce.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A generic class to standardize API responses.
 *
 * This class encapsulates the structure of an API response, including 
 * a status code, a message, and a result object that can be of any type.
 *
 * @param <T> the type of the result object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse<T> {

    /**
     * The HTTP status code of the response.
     * This indicates the result of the API request, 
     * such as success or failure.
     */
    private Integer code;

    /**
     * A message providing additional information about the 
     * result of the API request. 
     * It can indicate success, failure, or other relevant information.
     */
    private String message;

    /**
     * The result object that contains the data returned from the API.
     * This can be of any type, and it may be null if not applicable.
     */
    private T result;
}
