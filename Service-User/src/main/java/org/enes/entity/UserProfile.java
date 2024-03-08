package org.enes.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.enes.utility.enums.ERole;
import org.enes.utility.enums.EStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class UserProfile extends BaseEntity{
    @Id
    private String id;
    private Long authId;
    private String username;
    private String tcNo;
    private String idCardUrl;
    private String phoneNumber;
    private String email;
    private String address;
    @Builder.Default
    private EStatus status=EStatus.PENDING;
    @Builder.Default
    private ERole role=ERole.USER;

}
