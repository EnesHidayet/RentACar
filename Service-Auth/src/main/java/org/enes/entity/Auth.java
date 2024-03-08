package org.enes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.enes.utility.CodeGenerator;
import org.enes.utility.enums.EStatus;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_auth")
public class Auth extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @Builder.Default
    private String activationCode = CodeGenerator.generateCode();
    @Builder.Default
    private EStatus status = EStatus.PENDING;
}
