package org.enes.dto.Request;

import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordRequestDto {

    private Long id;
    @Size(min = 8, max = 16)
    private String oldPassword;
    @Size(min = 8, max = 16)
    private String newPassword;

}
