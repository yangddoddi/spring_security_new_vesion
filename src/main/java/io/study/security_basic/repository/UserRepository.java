package io.study.security_basic.repository;

import io.study.security_basic.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository가 Repository를 상속하고 있으므로 어노테이션 불필요함
public interface UserRepository extends JpaRepository<User, Long> {
}
