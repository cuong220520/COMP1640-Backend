package com.greenwich.comp1640.dto.request.campaign;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Data
public class UpdateCampaignRequestDto {

    @JsonProperty("submit_deadline")
    private Date submitDeadline;

    @JsonProperty("edit_deadline")
    private Date editDeadline;

    @JsonProperty("start_date")
    private Date startDate;

    @JsonProperty("admin_id")
    private Long adminId;

}
