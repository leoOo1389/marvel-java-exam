package com.albo.marveljavaexam.controller.impl;

import com.albo.marveljavaexam.controller.ICollaboratorController;
import com.albo.marveljavaexam.mapper.ICollaboratorMapper;
import com.albo.marveljavaexam.response.CollaboratorListResponse;
import com.albo.marveljavaexam.service.ICollaboratorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collaborators")
public class CollaboratorController implements ICollaboratorController {

    private final ICollaboratorService service;
    private final ICollaboratorMapper mapper;

    public CollaboratorController(ICollaboratorService service, ICollaboratorMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    @GetMapping("/{name}")
    public CollaboratorListResponse getByCharacterName(@PathVariable(name = "name") String name) {
        return mapper.characterDTOToCollaboratorListResponse(service.getCollaborator(name));
    }

}
