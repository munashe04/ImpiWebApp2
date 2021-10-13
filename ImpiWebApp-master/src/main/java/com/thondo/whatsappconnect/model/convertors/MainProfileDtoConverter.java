package com.thondo.whatsappconnect.model.convertors;

import com.thondo.whatsappconnect.entity.MainProfile;
import com.thondo.whatsappconnect.entity.Orders;
import com.thondo.whatsappconnect.model.MainProfileDto;
import com.thondo.whatsappconnect.model.OrderDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MainProfileDtoConverter {
    public MainProfileDto agentToDto(MainProfile mainProfile) {

        MainProfileDto agentResponse = new MainProfileDto();
        agentResponse.setFullName(mainProfile.getFullName());
        agentResponse.setMenuSelected(mainProfile.getMenuSelected());
        agentResponse.setMenuStage(mainProfile.getMenuStage());
        agentResponse.setId(mainProfile.getId());
        agentResponse.setGender(mainProfile.getGender());
        agentResponse.setDateCreated(mainProfile.getDateCreated());
        agentResponse.setIdNumber(mainProfile.getIdNumber());
        agentResponse.setMobileNumber(mainProfile.getMobileNumber());
        agentResponse.setApprovalStatus(mainProfile.getApprovalStatus());
        agentResponse.setFullAddress(mainProfile.getFullAddress());
        agentResponse.setDateOfBirth(mainProfile.getDateOfBirth());

        return agentResponse;
    }
    public List<MainProfileDto> agentToDto(List<MainProfile> agents) {
        return agents.stream().map(x -> agentToDto(x)).collect(Collectors.toList());
    }


    public MainProfile agentDtoToEntity(MainProfileDto mainProfileDto){
        MainProfile mainProfile = new MainProfile();
        mainProfile.setMenuStage(mainProfile.getMenuStage());
        mainProfile.setMenuSelected(mainProfile.getMenuSelected());
        mainProfile.setMobileNumber(mainProfileDto.getMobileNumber());
        mainProfile.setFullName(mainProfileDto.getFullName());
        mainProfile.setIdNumber(mainProfileDto.getIdNumber());
        mainProfile.setApprovalStatus(mainProfileDto.getApprovalStatus());
        mainProfile.setGender(mainProfileDto.getGender());
        mainProfile.setDateCreated(mainProfileDto.getDateCreated());
        mainProfile.setFullAddress(mainProfileDto.getFullAddress());
        mainProfile.setId(mainProfileDto.getId());
        mainProfile.setDateOfBirth(mainProfile.getDateOfBirth());
        return mainProfile;
    }
    public List<MainProfile>agentDtoToEntity(List<MainProfileDto>agentDto){
        return agentDto.stream().map(x -> agentDtoToEntity(x)).collect(Collectors.toList());

        }


}
