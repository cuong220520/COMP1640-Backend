package com.greenwich.comp1640.dto.request.faculty;

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
public class CreateFacultyRequestDto {

    @NotNull(message = TranslationConst.ValidationMessage.NOT_NULL)
    @NotEmpty(message = TranslationConst.ValidationMessage.NOT_EMPTY)
    @Length(max = 4, message = TranslationConst.ValidationMessage.LENGTH)
    @JsonProperty("code")
    private String code;

    @NotNull(message = TranslationConst.ValidationMessage.NOT_NULL)
    @NotEmpty(message = TranslationConst.ValidationMessage.NOT_EMPTY)
    @Length(max = 100, message = TranslationConst.ValidationMessage.LENGTH)
    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @NotNull(message = TranslationConst.ValidationMessage.NOT_NULL)
    @NotEmpty(message = TranslationConst.ValidationMessage.NOT_EMPTY)
    @Length(max = 4, message = TranslationConst.ValidationMessage.LENGTH)
    @JsonProperty("coordinator_username")
    private String coordinatorUsername;

}
