package com.greenwich.comp1640.dao;

import com.greenwich.comp1640.model.Campaign;
import com.greenwich.comp1640.repository.readonly.CampaignRORepository;
import com.greenwich.comp1640.repository.readwrite.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CampaignDao extends BaseDao<Campaign, String> {
    private final CampaignRepository campaignRepository;
    private final CampaignRORepository campaignRORepository;

    @Autowired
    public CampaignDao(CampaignRepository campaignRepository, CampaignRORepository campaignRORepository) {
        super(campaignRepository, campaignRORepository);
        this.campaignRepository = campaignRepository;
        this.campaignRORepository = campaignRORepository;
    }

    public Campaign findByCode(String code) {
        return this.campaignRORepository.findByCode(code);
    }

    public Campaign saveCampaign(Campaign campaign) {
        return this.campaignRepository.save(campaign);
    }

    public List<Campaign> findAllCampaign() {
        return this.campaignRORepository.findAll();
    }
}
