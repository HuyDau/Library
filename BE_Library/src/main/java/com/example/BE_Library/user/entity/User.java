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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = User.TABLE_NAME)
public class User extends SoftDeleteEntity implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
