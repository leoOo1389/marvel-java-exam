package com.albo.marveljavaexam.controller.impl;

import com.albo.marveljavaexam.controller.ICharacterController;
import com.albo.marveljavaexam.mapper.ICollaboratorMapper;
import com.albo.marveljavaexam.response.CharacterResponse;
import com.albo.marveljavaexam.response.CollaboratorCharacterListResponse;
import com.albo.marveljavaexam.service.ICollaboratorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController implements ICharacterController {

    private final ICollaboratorService service;
    private final ICollaboratorMapper mapper;

    public CharacterController(ICollaboratorService service, ICollaboratorMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    @GetMapping
    public List<CharacterResponse> getAll() {
        return mapper.characterDTOListToCharacterResponseList(service.getCollaborators());
    }

    @Override
    @GetMapping("/{name}")
    public CollaboratorCharacterListResponse getByCharacterName(@PathVariable(name = "name") String name) {
        return mapper.characterDTOToCollaboratorCharacterListResponse(service.getCollaborator(name));
    }
}
