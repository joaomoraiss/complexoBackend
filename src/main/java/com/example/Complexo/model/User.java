package com.example.Complexo.model;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data @Entity
@Table(name = "app_user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studio_id")
    private Long studioId;

    @Column(name = "studio_name")
    private String studioName;

    @Column(name = "studio_role")
    private UserRole role;

    @Column(name = "studio_adress")
    private String studioAdress;

    @Column(name = "studio_description")
    private String studioDescription;

    @Column(name = "studio_email")
    private String studioEmail;

    @Column(name = "studio_password")
    private String studioPassword;

    @OneToMany(mappedBy = "artistStudio")
    @JsonIgnore
    private List<Artist> artistStudio;

    @Column(name = "studio_images")
    private List<String> studioImages;

    @Column(name = "studio_location")
    private String studioLocation;

    @Column(name = "studio_instagram")
    private String studioInstagram;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_STUDIO"));
    }

    @Override
    public String getPassword() {
        return studioPassword;
    }

    @Override
    public String getUsername() {
        return studioEmail;
    }

    public User(UserRole role, String studioEmail, String studioPassword) {
        this.role = role;
        this.studioEmail = studioEmail;
        this.studioPassword = studioPassword;
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
