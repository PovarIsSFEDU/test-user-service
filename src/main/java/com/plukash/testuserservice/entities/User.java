package com.plukash.testuserservice.entities;


import com.plukash.testuserservice.utilities.Token.Token;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

    @Column(nullable = false, unique = true)
    private String name;

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

    @ToString.Exclude
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<EmailData> emails = new LinkedHashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(null);
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
