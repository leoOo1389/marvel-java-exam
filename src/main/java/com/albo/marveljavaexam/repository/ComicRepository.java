package com.albo.marveljavaexam.repository;

import com.albo.marveljavaexam.domain.document.Comic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicRepository extends MongoRepository<Comic, Integer> {
}
