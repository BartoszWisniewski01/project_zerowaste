package com.example.project_zerowaste.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "home_address")
    private Address home_address;
    @Column(name = "name")
    @NotBlank(message = "Name cannot be empty.")
    private String name;
    @Column(name = "surname")
    @NotBlank(message = "Surname cannot be empty.")
    private String surname;
    @Column(name = "email", unique = true)
    @NotBlank(message = "Email cannot be empty.")
    private String email;
    @Column(name = "role")
    private String role;
    @Column(name = "login", unique = true)
    @NotBlank(message = "Login cannot be empty.")
    private String username;
    @Column(name = "password")
    @NotBlank(message = "Password cannot be empty.")
    private String password;
    @Column(name = "account_status")
    private String account_status;
    @Override
    public Collection< ? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<User_Seller> user_seller;
}
