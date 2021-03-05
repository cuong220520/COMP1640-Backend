package com.greenwich.comp1640.dto.request.article;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greenwich.comp1640.util.constant.ArticleStatusConst;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class UpdateArticleRequestDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("message")
    private String message;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("status")
    private ArticleStatusConst status;

    @JsonProperty("document_url")
    private String documentUrl;

}
