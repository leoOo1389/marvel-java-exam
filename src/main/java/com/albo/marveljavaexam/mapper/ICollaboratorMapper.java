package com.albo.marveljavaexam.mapper;

import com.albo.marveljavaexam.dto.CharacterDTO;
import com.albo.marveljavaexam.dto.CreatorDTO;
import com.albo.marveljavaexam.response.CharacterResponse;
import com.albo.marveljavaexam.response.CollaboratorCharacterListResponse;
import com.albo.marveljavaexam.response.CollaboratorCharacterResponse;
import com.albo.marveljavaexam.response.CollaboratorListResponse;
import com.albo.marveljavaexam.response.CollaboratorResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ICollaboratorMapper {

    CollaboratorListResponse characterDTOToCollaboratorListResponse(CharacterDTO characterDTO);

    CollaboratorResponse creatorDTOToCollaboratorResponse(CreatorDTO creatorDTO);

    CollaboratorCharacterListResponse characterDTOToCollaboratorCharacterListResponse(CharacterDTO characterDTO);

    List<CharacterResponse> characterDTOListToCharacterResponseList(List<CharacterDTO> characterDTO);

    CharacterResponse characterDTOToCharacterResponse(CharacterDTO characterDTO);

    CollaboratorCharacterResponse characterDTOToCollaboratorCharacterResponse(CharacterDTO characterDTO);
}
