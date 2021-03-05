package com.greenwich.comp1640.dto.request.article;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greenwich.comp1640.util.constant.TranslationConst;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Data
@NoArgsConstructor
public class CreateArticleRequestDto {

    @NotNull(message = TranslationConst.ValidationMessage.NOT_NULL)
    @NotEmpty(message = TranslationConst.ValidationMessage.NOT_EMPTY)
    @Length(max = 255, message = TranslationConst.ValidationMessage.LENGTH)
    @JsonProperty("name")
    private String name;

    @JsonProperty("message")
    private String message;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("document_url")
    private String documentUrl;

    @NotNull(message = TranslationConst.ValidationMessage.NOT_NULL)
    @NotEmpty(message = TranslationConst.ValidationMessage.NOT_EMPTY)
    @Length(max = 255, message = TranslationConst.ValidationMessage.LENGTH)
    @JsonProperty("user_username")
    private String userUsername;

    @NotNull(message = TranslationConst.ValidationMessage.NOT_NULL)
    @NotEmpty(message = TranslationConst.ValidationMessage.NOT_EMPTY)
    @Length(max = 255, message = TranslationConst.ValidationMessage.LENGTH)
    @JsonProperty("faculty_code")
    private String facultyCode;

    @NotNull(message = TranslationConst.ValidationMessage.NOT_NULL)
    @NotEmpty(message = TranslationConst.ValidationMessage.NOT_EMPTY)
    @Length(max = 255, message = TranslationConst.ValidationMessage.LENGTH)
    @JsonProperty("campaign_code")
    private String campaignCode;

}
