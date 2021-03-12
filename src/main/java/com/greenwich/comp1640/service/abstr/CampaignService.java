package com.greenwich.comp1640.service.abstr;

import com.greenwich.comp1640.dto.request.campaign.CreateCampaignRequestDto;
import com.greenwich.comp1640.dto.request.campaign.UpdateCampaignRequestDto;
import com.greenwich.comp1640.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

public interface CampaignService {

    ResponseEntity<GeneralResponse<Object>> getCampaignList();

    ResponseEntity<GeneralResponse<Object>> createCampaign(CreateCampaignRequestDto createCampaignRequestDto);

    ResponseEntity<GeneralResponse<Object>> updateCampaign(String code, UpdateCampaignRequestDto updateCampaignRequestDto);

    ResponseEntity<GeneralResponse<Object>> getCampaign(String code);

    ResponseEntity<GeneralResponse<Object>> updateStatus(String code);

    ResponseEntity<GeneralResponse<Object>> getActiveCampaign();

}
