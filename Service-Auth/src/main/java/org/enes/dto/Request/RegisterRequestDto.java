package org.enes.dto.Request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    @Column(unique = true)
    @Size(min = 3)
    private String username;
    @Size(min = 8, max = 16)
    private String password;
    @Column(unique = true)
    @Size(min = 11, max = 11)
    private String tcNo;
    @NotNull
    private String idCardUrl;
    @Size(min = 10, max = 10, message = "Başında 0 olmadan yazılmalıdır.")
    private String phoneNumber;
    @Email
    private String email;
}
