package com.thesis.serverfurnitureecommerce.application.services.faqs;

import com.thesis.serverfurnitureecommerce.infrastructure.persistence.FaqsRepository;
import com.thesis.serverfurnitureecommerce.domain.model.vo.FaqsVO;
import com.thesis.serverfurnitureecommerce.domain.model.entity.FaqsEntity;
import com.thesis.serverfurnitureecommerce.common.mapper.FaqsMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class FaqsServiceImpl implements FaqsService {
    FaqsRepository faqsRepository;
    FaqsMapper faqsMapper;

    @Override
    public List<FaqsVO> getAllFaqs() {
        log.info("Service get all faqs");
        List<FaqsEntity> faqsEntities = (List<FaqsEntity>) faqsRepository.findAll();
        List<FaqsVO> faqsVOS = new ArrayList<FaqsVO>();
        for (FaqsEntity faqs : faqsEntities){
            FaqsVO faqsVO = faqsMapper.convertToDTO(faqs);
            faqsVOS.add(faqsVO);
        }
        return faqsVOS;
    }
}
