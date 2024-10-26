package com.thesis.serverfurnitureecommerce.domain.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * A request class for sending an email.
 * <p>
 * This class is used to encapsulate the email address that will be
 * processed for various operations such as sending verification or
 * notification emails.
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailRequest {

    /**
     * The email address to be used for sending emails.
     */
    String email;
}
