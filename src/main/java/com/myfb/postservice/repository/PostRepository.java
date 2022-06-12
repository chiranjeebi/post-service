package com.myfb.postservice.repository;

import com.myfb.postservice.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
public List<PostEntity> findAllByUserId(Long userId );
}
