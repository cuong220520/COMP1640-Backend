package com.greenwich.comp1640.dto.mapper;

import com.greenwich.comp1640.dto.request.campaign.CreateCampaignRequestDto;
import com.greenwich.comp1640.dto.request.campaign.UpdateCampaignRequestDto;
import com.greenwich.comp1640.dto.response.CampaignResponseDto;
import com.greenwich.comp1640.model.Campaign;
import com.greenwich.comp1640.model.User;
import com.greenwich.comp1640.util.constant.CampaignStatusConst;

import java.util.ArrayList;
import java.util.List;

public class CampaignMapper {

    private CampaignMapper() {

    }

    public static CampaignResponseDto toDto(Campaign campaign) {
        CampaignResponseDto campaignResponseDto = new CampaignResponseDto();
        campaignResponseDto.setCode(campaign.getCode());
        campaignResponseDto.setSubmitDeadline(campaign.getSubmitDeadline().toInstant().getEpochSecond());
        campaignResponseDto.setEditDeadline(campaign.getEditDeadline().toInstant().getEpochSecond());
        campaignResponseDto.setStartDate(campaign.getStartDate().toInstant().getEpochSecond());
        campaignResponseDto.setAdminUsername(campaign.getAdmin().getUsername());
        campaignResponseDto.setStatus(campaign.getStatus());

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
        campaign.setStatus(CampaignStatusConst.DISABLE);

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

        if (updateCampaignRequestDto.getAdminUsername() != null) {
            campaign.setAdmin(admin);
        }

        return campaign;
    }

}
