package com.greenwich.comp1640.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greenwich.comp1640.util.constant.CampaignStatusConst;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class CampaignResponseDto {

    @JsonProperty("code")
    private String code;

    @JsonProperty("submit_deadline")
    private Long submitDeadline;

    @JsonProperty("edit_deadline")
    private Long editDeadline;

    @JsonProperty("start_date")
    private Long startDate;

    @JsonProperty("admin_username")
    private String adminUsername;

    @JsonProperty("status")
    private CampaignStatusConst status;

}
