package com.albo.marveljavaexam.repository;

import com.albo.marveljavaexam.domain.document.Character;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends MongoRepository<Character, Integer> {

    Character findByName(String name);
}
