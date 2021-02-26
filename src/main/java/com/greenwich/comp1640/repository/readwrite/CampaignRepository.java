package com.greenwich.comp1640.repository.readwrite;

import com.greenwich.comp1640.model.Campaign;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends GenericRepository<Campaign, String> {
}
