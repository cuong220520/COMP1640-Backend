package com.greenwich.comp1640.repository.readonly;

import com.greenwich.comp1640.config.datasource.ReadOnlyRepository;
import com.greenwich.comp1640.model.Campaign;
import com.greenwich.comp1640.util.constant.CampaignStatusConst;
import org.springframework.stereotype.Repository;

@ReadOnlyRepository
@Repository
public interface CampaignRORepository extends GenericRORepository<Campaign, String> {

    Campaign findByCode(String code);

    Campaign findByStatus(CampaignStatusConst status);

}
