package com.productShop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * {@link UserInfo}
 *
 * @author Dmytro Trotsenko on 2/10/23
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(schema = "my_store", name = "user")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
