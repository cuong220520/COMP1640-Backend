package com.greenwich.comp1640.dto.request.campaign;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.greenwich.comp1640.util.constant.TranslationConst;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Data
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCampaignRequestDto {

    @NotNull(message = TranslationConst.ValidationMessage.NOT_NULL)
    @NotEmpty(message = TranslationConst.ValidationMessage.NOT_EMPTY)
    @Length(max = 255, message = TranslationConst.ValidationMessage.LENGTH)
    @JsonProperty("code")
    private String code;

    @NotNull(message = TranslationConst.ValidationMessage.NOT_NULL)
    @JsonProperty("submit_deadline")
    private Date submitDeadline;

    @NotNull(message = TranslationConst.ValidationMessage.NOT_NULL)
    @JsonProperty("edit_deadline")
    private Date editDeadline;

    @NotNull(message = TranslationConst.ValidationMessage.NOT_NULL)
    @JsonProperty("start_date")
    private Date startDate;

    @NotNull(message = TranslationConst.ValidationMessage.NOT_NULL)
    @NotEmpty(message = TranslationConst.ValidationMessage.NOT_EMPTY)
    @Length(max = 255, message = TranslationConst.ValidationMessage.LENGTH)
    @JsonProperty("admin_username")
    private String adminUsername;

}
