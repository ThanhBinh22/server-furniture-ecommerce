package com.thesis.serverfurnitureecommerce.internal.services.faqs;

import com.thesis.serverfurnitureecommerce.internal.repositories.FaqsRepository;
import com.thesis.serverfurnitureecommerce.model.dto.FaqsDTO;
import com.thesis.serverfurnitureecommerce.model.entity.FaqsEntity;
import com.thesis.serverfurnitureecommerce.pkg.mapper.FaqsMapper;
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
    public List<FaqsDTO> getAllFaqs() {
        log.info("Service get all faqs");
        List<FaqsEntity> faqsEntities = (List<FaqsEntity>) faqsRepository.findAll();
        List<FaqsDTO> FaqsDTOs = new ArrayList<FaqsDTO>();
        for (FaqsEntity faqs : faqsEntities){
            FaqsDTO faqsDTO = faqsMapper.convertToDTO(faqs);
            FaqsDTOs.add(faqsDTO);
        }
        return FaqsDTOs;
    }
}
