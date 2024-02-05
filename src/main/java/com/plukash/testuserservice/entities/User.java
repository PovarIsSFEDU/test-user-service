package com.plukash.testuserservice.entities;


import com.plukash.testuserservice.utilities.Token.Token;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotEmpty
    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull
    @NotEmpty
    @NotBlank
    @Column(nullable = false, unique = true, length = 500)
    @Size(min = 3, max = 500)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private String password;


    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Token> tokens;

    @ToString.Exclude
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "account_id")
    private Account account;

    @Size(min = 1)
    @ToString.Exclude
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<EmailData> emails = new LinkedHashSet<>();

    @Size(min = 1)
    @ToString.Exclude
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<PhoneData> phoneDatas = new LinkedHashSet<>();

    @Enumerated(EnumType.STRING)
    private com.plukash.testuserservice.entities.Role role = Role.USER;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return emails.stream().findAny().orElseThrow().getEmail();
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
}
