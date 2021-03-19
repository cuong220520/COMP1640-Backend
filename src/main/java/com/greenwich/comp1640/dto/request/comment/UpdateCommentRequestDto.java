package com.greenwich.comp1640.dto.request.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class UpdateCommentRequestDto {

    @JsonProperty("content")
    private String content;

}
