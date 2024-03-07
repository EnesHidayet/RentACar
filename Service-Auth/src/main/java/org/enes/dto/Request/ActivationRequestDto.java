package org.enes.dto.Request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivationRequestDto {
    private Long id;
    private String activationCode;
}
