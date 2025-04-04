package com.thesis.serverfurnitureecommerce.domain.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewVO {
    Long id;
    String comment;
    Integer rating;
    Integer like;
    Integer commentParentID;
    Integer productID;
    String username;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
