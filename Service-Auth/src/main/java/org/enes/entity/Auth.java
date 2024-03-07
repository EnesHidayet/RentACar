package org.enes.entity;

import jakarta.persistence.*;
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
    private String username;
    private String password;
    private String tcNo;
    private String idCardUrl;
    private String phoneNumber;
    private String email;
    @Builder.Default
    private String activationCode = CodeGenerator.generateCode();
    @Builder.Default
    private EStatus status = EStatus.PENDING;
}
