package com.libraryapp.entity;

import com.libraryapp.entity.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "registered_users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String username;
    private String password;

    @OneToOne
    @JoinColumn(name = "finishedBooks_id")
    private List<Book> finishedBooks;

    @OneToOne
    @JoinColumn(name = "readingBooks_id")
    private List<Book> readingBooks;

    @OneToOne
    @JoinColumn(name = "wantToReadBooks_id")
    private List<Book> wantToReadBooks;

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(Role.USER.toString()));
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
