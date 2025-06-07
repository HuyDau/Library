package com.example.BE_Library.user.entity;

import com.example.BE_Library.common.constant.TextMaxLength;
import com.example.BE_Library.common.entity.SoftDeleteEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = User.TABLE_NAME)
public class User extends SoftDeleteEntity {
    public static final String TABLE_NAME = "`user`";

    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String FULL_NAME = "full_name";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String ROLE = "role";


    @Id
    @Column(name = ID, length = TextMaxLength.UUID)
    private String id;

    @Column(name = EMAIL)
    private String email;

    @Column(name = PASSWORD)
    private String password;

    @Column(name = FULL_NAME)
    private String fullName;

    @Column(name = PHONE_NUMBER)
    private String phoneNumber;

    @Column(name = ROLE)
    private String role;
}
