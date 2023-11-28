package com.carinsurance.loginandregister;

import javax.persistence.*;

import com.carinsurance.common.entity.AbstractEntity;
import lombok.*;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User extends AbstractEntity {
    private String username;
    private String password;
}
