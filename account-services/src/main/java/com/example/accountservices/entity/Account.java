package com.example.accountservices.entity;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
@Table(value = "accounts")
public class Account {

    @PrimaryKey
    private String id = UUID.randomUUID().toString();

    @Setter
    @Column(value = "uname")
    private String username;

    @Setter
    @Column(value = "name")
    private String name;

    @Setter
    @Column(value = "surname")
    private String surname;

    @Setter
    @Column(value = "email")
    private String email;

    @Setter
    @Column(value = "birth_date")
    private Date birthDate;

    @Setter
    @Column(value = "pwd")
    private String password;

    @Column(value = "created_at")
    private Date createdAt;

    @Column(value = "is_active")
    private Boolean active;

}
