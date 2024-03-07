package org.enes.dto.Request;

import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
    @Size(min = 3)
    private String username;
    @Size(min = 8, max = 16)
    private String password;
}
