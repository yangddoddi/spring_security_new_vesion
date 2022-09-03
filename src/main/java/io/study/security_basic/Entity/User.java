package io.study.security_basic.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table(name="users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BasedEntity {
    @Id
    @GeneratedValue
    @Column(name="user_id")
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;

    private String provider;
    private String providerId;

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
