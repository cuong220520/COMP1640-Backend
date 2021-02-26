package com.greenwich.comp1640.repository.readonly;

import com.greenwich.comp1640.model.Campaign;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRORepository extends GenericRORepository<Campaign, String> {

    Campaign findByCode(String code);

}
