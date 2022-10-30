package io.study.security_basic.repository;

import io.study.security_basic.Entity.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagRepository extends JpaRepository<PostTag, Long> {
}
