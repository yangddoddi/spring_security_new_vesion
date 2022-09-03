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

    @Builder
    public User(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = "ROLE_USER";
    }
}
