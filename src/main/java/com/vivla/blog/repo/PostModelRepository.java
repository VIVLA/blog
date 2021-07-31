package com.vivla.blog.repo;

import com.vivla.blog.models.PostModel;
import org.springframework.data.repository.CrudRepository;

public interface PostModelRepository extends CrudRepository<PostModel, Long> {
}
