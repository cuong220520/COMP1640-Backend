package com.greenwich.comp1640.dto.request.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.greenwich.comp1640.util.constant.TranslationConst;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignupRequestDto {

    @NotNull(message = TranslationConst.ValidationMessage.NOT_NULL)
    @NotEmpty(message = TranslationConst.ValidationMessage.NOT_EMPTY)
    @Length(max = 255, message = TranslationConst.ValidationMessage.LENGTH)
    @JsonProperty("username")
    private String username;

    @NotNull(message = TranslationConst.ValidationMessage.NOT_NULL)
    @NotEmpty(message = TranslationConst.ValidationMessage.NOT_EMPTY)
    @Length(max = 255, message = TranslationConst.ValidationMessage.LENGTH)
    @JsonProperty("password")
    private String password;

    @NotNull(message = TranslationConst.ValidationMessage.NOT_NULL)
    @NotEmpty(message = TranslationConst.ValidationMessage.NOT_EMPTY)
    @JsonProperty("roles")
    private List<Long> roles;
}
