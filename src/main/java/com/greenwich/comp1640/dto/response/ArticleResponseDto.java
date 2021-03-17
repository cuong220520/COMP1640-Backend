package com.greenwich.comp1640.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greenwich.comp1640.util.constant.ArticleStatusConst;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Data
@NoArgsConstructor
public class ArticleResponseDto {
    
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("message")
    private String message;

    @JsonProperty("status")
    private String status;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("document_url")
    private String documentUrl;
    
    @JsonProperty("user_username")
    private String userUsername;
    
    @JsonProperty("faculty_code")
    private String facultyCode;
    
    @JsonProperty("campaign_code")
    private String campaignCode;

    @JsonProperty("created_at")
    private Long createdAt;

    @JsonProperty("updated_at")
    private Long updatedAt;
    
}
