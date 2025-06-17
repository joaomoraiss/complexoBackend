package com.example.Complexo.model;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data @Entity
@Table(name = "app_user")
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

    @Column(name = "profile_picture_base64", columnDefinition = "TEXT") // Usar text para strings longas (Base64)
    private String profilePictureBase64;

@OneToMany(mappedBy = "artistStudio", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
private List<Artist> artistStudio;

    @ElementCollection
    @CollectionTable(name = "studio_images", joinColumns = @JoinColumn(name = "studio_id"))
    @Column(name = "image_url", columnDefinition = "TEXT")
    private List<String> studioImages;


@Column(name = "studio_location")
private String studioLocation;

@Column(name = "studio_instagram")
private String studioInstagram;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

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

public User(UserRole role, String studioEmail, String studioPassword, String nomeEstudio) {
this.role = role;
this.studioEmail = studioEmail;
this.studioPassword = studioPassword;
this.studioName = nomeEstudio;
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

public void setStudioId(Long studioId) {
this.studioId = studioId;
}

public String getStudioEmail() {
return studioEmail;
}

}