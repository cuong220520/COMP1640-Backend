package com.greenwich.comp1640.controller;

import com.greenwich.comp1640.dto.request.campaign.CreateCampaignRequestDto;
import com.greenwich.comp1640.dto.request.campaign.UpdateCampaignRequestDto;
import com.greenwich.comp1640.response.GeneralResponse;
import com.greenwich.comp1640.service.abstr.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/campaign")
public class CampaignController {

    @Autowired
    CampaignService campaignService;

    @PostMapping
    public ResponseEntity<GeneralResponse<Object>> createCampaign(@RequestBody @Valid CreateCampaignRequestDto createCampaignRequestDto) {
        return campaignService.createCampaign(createCampaignRequestDto);
    }

    @GetMapping
    public ResponseEntity<GeneralResponse<Object>> getAllCampaign() {
        return campaignService.getCampaignList();
    }

    @PutMapping(value = "/update/{code}")
    public ResponseEntity<GeneralResponse<Object>> updateCampaign(
            @PathVariable("code") String code,
            @Valid @RequestBody UpdateCampaignRequestDto updateCampaignRequestDto) {
        return campaignService.updateCampaign(code, updateCampaignRequestDto);
    }

    @GetMapping(value = "/{code}")
    public ResponseEntity<GeneralResponse<Object>> getCampaign(@PathVariable("code") String code) {
        return campaignService.getCampaign(code);
    }

    @PutMapping(value = "/update-status")
    public ResponseEntity<GeneralResponse<Object>> updateCampaignStatus(@RequestParam("code") String code) {
        return campaignService.updateStatus(code);
    }

    @GetMapping(value = "/get-current-campaign")
    public ResponseEntity<GeneralResponse<Object>> getCurrentCampaign() {
        return campaignService.getActiveCampaign();
    }

}
