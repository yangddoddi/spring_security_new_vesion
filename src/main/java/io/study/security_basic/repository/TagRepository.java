package io.study.security_basic.repository;

import io.study.security_basic.Entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
