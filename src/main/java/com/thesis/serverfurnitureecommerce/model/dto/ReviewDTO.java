package com.thesis.serverfurnitureecommerce.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewDTO {
    Long id;
    String comment;
    Integer rating;
    Integer productID;
    Integer userID;
}
