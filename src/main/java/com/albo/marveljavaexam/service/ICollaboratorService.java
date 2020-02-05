package com.albo.marveljavaexam.service;

import com.albo.marveljavaexam.dto.CharacterDTO;

import java.util.List;

public interface ICollaboratorService {

    CharacterDTO getCollaborator(String name);

    List<CharacterDTO> getCollaborators();
}
