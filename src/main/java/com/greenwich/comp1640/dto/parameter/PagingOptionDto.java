package com.greenwich.comp1640.dto.parameter;

import com.google.common.base.CaseFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
public class PagingOptionDto {
    @Min(0)
    @Max(100)
    private int limit = 10;
    @Min(0)
    private int page = 0;

    public Pageable createPageable(int page, int limit, String sort) {
        if (sort == null || sort.isEmpty()) {
            return PageRequest.of(page, limit);
        }

        Sort sortBy;
        if (sort.charAt(0) == '-') {
            sortBy = Sort.by(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, sort.substring(1))).descending();
        } else {
            sortBy = Sort.by(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, sort));
        }

        return PageRequest.of(page, limit, sortBy);
    }
}
