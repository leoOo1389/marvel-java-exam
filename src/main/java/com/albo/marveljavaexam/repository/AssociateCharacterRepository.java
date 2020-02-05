package com.albo.marveljavaexam.repository;

import com.albo.marveljavaexam.domain.document.AssociateCharacter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociateCharacterRepository extends MongoRepository<AssociateCharacter, Integer> {

    List<AssociateCharacter> findByCharacterId(int characterId);

    void deleteByCharacterId(int characterId);
}
