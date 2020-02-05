package com.albo.marveljavaexam.repository;

import com.albo.marveljavaexam.domain.document.AssociateCreator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociateCreatorRepository extends MongoRepository<AssociateCreator, Integer> {

    List<AssociateCreator> findByCharacterId(int characterId);

    void deleteByCharacterId(int characterId);
}
