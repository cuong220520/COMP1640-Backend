package com.greenwich.comp1640.dto.mapper;

import com.greenwich.comp1640.dto.request.campaign.CreateCampaignRequestDto;
import com.greenwich.comp1640.dto.request.campaign.UpdateCampaignRequestDto;
import com.greenwich.comp1640.dto.response.CampaignResponseDto;
import com.greenwich.comp1640.model.Campaign;
import com.greenwich.comp1640.model.User;

import java.util.ArrayList;
import java.util.List;

public class CampaignMapper {

    private CampaignMapper() {

    }

    public static CampaignResponseDto toDto(Campaign campaign) {
        CampaignResponseDto campaignResponseDto = new CampaignResponseDto();
        campaignResponseDto.setCode(campaign.getCode());
        campaignResponseDto.setSubmitDeadline(campaign.getSubmitDeadline());
        campaignResponseDto.setEditDeadline(campaign.getEditDeadline());
        campaignResponseDto.setStartDate(campaign.getStartDate());
        campaignResponseDto.setAdminId(campaign.getAdmin().getId());

        return campaignResponseDto;
    }

    public static List<CampaignResponseDto> toListDto(List<Campaign> campaignList) {
        List<CampaignResponseDto> campaignResponseDtoList = new ArrayList<>();

        for (Campaign campaign : campaignList) {
            campaignResponseDtoList.add(toDto(campaign));
        }

        return campaignResponseDtoList;
    }

    public static Campaign createFromDto(CreateCampaignRequestDto createCampaignRequestDto, User admin) {
        Campaign campaign = new Campaign();

        campaign.setCode(createCampaignRequestDto.getCode());
        campaign.setSubmitDeadline(createCampaignRequestDto.getSubmitDeadline());
        campaign.setEditDeadline(createCampaignRequestDto.getEditDeadline());
        campaign.setStartDate(createCampaignRequestDto.getEditDeadline());
        campaign.setAdmin(admin);

        return campaign;
    }

    public static Campaign updateFromDto(Campaign campaign, UpdateCampaignRequestDto updateCampaignRequestDto, User admin) {
        if (updateCampaignRequestDto.getSubmitDeadline() != null) {
            campaign.setSubmitDeadline(updateCampaignRequestDto.getSubmitDeadline());
        }

        if (updateCampaignRequestDto.getEditDeadline() != null) {
            campaign.setEditDeadline(updateCampaignRequestDto.getEditDeadline());
        }

        if (updateCampaignRequestDto.getStartDate() != null) {
            campaign.setStartDate(updateCampaignRequestDto.getStartDate());
        }

        if (updateCampaignRequestDto.getAdminId() != null) {
            campaign.setAdmin(admin);
        }

        return campaign;
    }

}
