package org.enes.dto.request;

import lombok.*;
import org.enes.utility.enums.EStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequestDto {
    private Long authId;
    private String username;
    private String tcNo;
    private String idCardUrl;
    private String phoneNumber;
    private String email;
}
