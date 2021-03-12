package com.greenwich.comp1640.util.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CampaignStatusConst {

    ACTIVE("ACTIVE"),
    DISABLE("DISABLE");

    private String value;
}
