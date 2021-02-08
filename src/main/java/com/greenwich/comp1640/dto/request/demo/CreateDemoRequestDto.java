package com.greenwich.comp1640.dto.request.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.greenwich.comp1640.util.constant.TranslationConst;
import com.greenwich.comp1640.util.regexp.DemoRegularExpression;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateDemoRequestDto {

    @NotNull(message = TranslationConst.ValidationMessage.NOT_NULL)
    @NotEmpty(message = TranslationConst.ValidationMessage.NOT_EMPTY)
    @Length(max = 100, message = TranslationConst.ValidationMessage.LENGTH)
    @Pattern(regexp = DemoRegularExpression.NAME, message = TranslationConst.ValidationMessage.PATTERN)
    @JsonProperty("first_name")
    private String firstName;

    @NotNull(message = TranslationConst.ValidationMessage.NOT_NULL)
    @NotEmpty(message = TranslationConst.ValidationMessage.NOT_EMPTY)
    @Length(max = 100, message = TranslationConst.ValidationMessage.LENGTH)
    @Pattern(regexp = DemoRegularExpression.NAME, message = TranslationConst.ValidationMessage.PATTERN)
    @JsonProperty("last_name")
    private String lastName;

    @NotNull(message = TranslationConst.ValidationMessage.NOT_NULL)
    @NotEmpty(message = TranslationConst.ValidationMessage.NOT_EMPTY)
    @Length(max = 100, message = TranslationConst.ValidationMessage.LENGTH)
    @JsonProperty("email")
    private String email;

    @NotNull(message = TranslationConst.ValidationMessage.NOT_NULL)
    @NotEmpty(message = TranslationConst.ValidationMessage.NOT_EMPTY)
    @Length(max = 50, message = TranslationConst.ValidationMessage.LENGTH)
    @Pattern(regexp = DemoRegularExpression.PHONE_NUMBER, message = TranslationConst.ValidationMessage.PATTERN)
    @JsonProperty("phone_number")
    private String phoneNumber;

}
