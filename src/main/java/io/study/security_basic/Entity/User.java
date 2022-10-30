package io.study.security_basic.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@Builder
@Table(name="users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BasedEntity {
    @Id
    @GeneratedValue
    @Column(name="user_id")
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String role;

    private String provider;
    private String providerId;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    public User(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = "ROLE_USER";
    }

    @Builder
    public User(String username, String password, String email, String role, String provider, String providerId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }
}
