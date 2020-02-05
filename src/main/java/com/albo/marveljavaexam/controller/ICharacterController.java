package com.albo.marveljavaexam.controller;

import com.albo.marveljavaexam.response.CharacterResponse;
import com.albo.marveljavaexam.response.CollaboratorCharacterListResponse;

import java.util.List;

public interface ICharacterController {

    List<CharacterResponse> getAll();

    CollaboratorCharacterListResponse getByCharacterName(String name);
}
