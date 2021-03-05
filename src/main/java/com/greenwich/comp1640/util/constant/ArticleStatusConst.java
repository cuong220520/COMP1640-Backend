package com.greenwich.comp1640.util.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ArticleStatusConst {

    PENDING("PENDING"),
    ACCEPTED("ACCEPTED"),
    DENIED("DENIED");

    private String value;

}
