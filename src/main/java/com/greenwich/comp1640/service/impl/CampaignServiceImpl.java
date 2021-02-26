package com.greenwich.comp1640.service.impl;

import com.greenwich.comp1640.dao.CampaignDao;
import com.greenwich.comp1640.dao.UserDao;
import com.greenwich.comp1640.dto.mapper.CampaignMapper;
import com.greenwich.comp1640.dto.request.campaign.CreateCampaignRequestDto;
import com.greenwich.comp1640.dto.request.campaign.UpdateCampaignRequestDto;
import com.greenwich.comp1640.dto.response.CampaignResponseDto;
import com.greenwich.comp1640.model.Campaign;
import com.greenwich.comp1640.model.User;
import com.greenwich.comp1640.response.GeneralResponse;
import com.greenwich.comp1640.response.ResponseFactory;
import com.greenwich.comp1640.service.abstr.CampaignService;
import com.greenwich.comp1640.util.constant.ResponseStatusCodeConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    CampaignDao campaignDao;

    @Autowired
    ResponseFactory responseFactory;

    @Autowired
    UserDao userDao;

    @Override
    public ResponseEntity<GeneralResponse<Object>> getCampaignList() {
        List<Campaign> campaignList = campaignDao.findAllCampaign();

        List<CampaignResponseDto> campaignResponseDtoList = CampaignMapper.toListDto(campaignList);

        return responseFactory.success(campaignResponseDtoList);
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> createCampaign(CreateCampaignRequestDto createCampaignRequestDto) {
        Campaign campaign = campaignDao.findByCode(createCampaignRequestDto.getCode());

        if (campaign != null) {
            log.error(String.format("Duplicated campaign with code: %s", createCampaignRequestDto.getCode()));
            return responseFactory.fail(String.format("Duplicated Campaign with code: %s", createCampaignRequestDto.getCode()),
                    ResponseStatusCodeConst.DUPLICATE_ERROR,
                    null);
        }

        Optional<User> admin = userDao.findById(createCampaignRequestDto.getAdminId());

        if (!admin.isPresent()) {
            log.error(String.format("Can not find user with user id: %d", createCampaignRequestDto.getAdminId()));
            return responseFactory.fail(String.format("Can not find user with user id: %d", createCampaignRequestDto.getAdminId()),
                    ResponseStatusCodeConst.DATA_NOT_FOUND_ERROR,
                    null);
        }

        Campaign newCampaign = CampaignMapper.createFromDto(createCampaignRequestDto, admin.get());

        campaignDao.saveCampaign(newCampaign);

        return responseFactory.success(CampaignMapper.toDto(newCampaign));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> updateCampaign(String code, UpdateCampaignRequestDto updateCampaignRequestDto) {
        Campaign campaign = campaignDao.findByCode(code);
        
        if (campaign == null) {
            log.error(String.format("Can not find campaign with campaign code: %s", code));
            return responseFactory.fail(String.format("Can not find campaign with campaign code: %s", code),
                    ResponseStatusCodeConst.DATA_NOT_FOUND_ERROR,
                    null);
        }

        Optional<User> admin = userDao.findById(updateCampaignRequestDto.getAdminId());

        if (!admin.isPresent()) {
            log.error(String.format("Can not find user with user id: %d", updateCampaignRequestDto.getAdminId()));
            return responseFactory.fail(String.format("Can not find user with user id: %d", updateCampaignRequestDto.getAdminId()),
                    ResponseStatusCodeConst.DATA_NOT_FOUND_ERROR,
                    null);
        }
        
        Campaign updatedCampaign = CampaignMapper.updateFromDto(campaign, updateCampaignRequestDto, admin.get());

        campaignDao.saveCampaign(updatedCampaign);

        return responseFactory.success(CampaignMapper.toDto(updatedCampaign));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> getCampaign(String code) {
        Campaign campaign = campaignDao.findByCode(code);

        if (campaign == null) {
            log.error(String.format("Can not find campaign with campaign code: %s", code));
            return responseFactory.fail(String.format("Can not find campaign with campaign code: %s", code),
                    ResponseStatusCodeConst.DATA_NOT_FOUND_ERROR,
                    null);
        }

        return responseFactory.success(CampaignMapper.toDto(campaign));
    }
}