package com.albo.marveljavaexam.controller;

import com.albo.marveljavaexam.response.CollaboratorListResponse;

public interface ICollaboratorController {

    CollaboratorListResponse getByCharacterName(String name);
}
