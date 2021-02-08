package com.greenwich.comp1640.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeneralResponse<D> {
    
	private boolean success;
    
	@JsonProperty("error_code")
	private String errorCode;
    
    @JsonProperty("error_id")
    private String errorId;
    
    private String message;
    private D data;
    private String sign;

    public static <T> GeneralResponse<T> createResponse(T data) {
        GeneralResponse<T> response = new GeneralResponse<>();
        response.setData(data);
        return response;
    }

    public static <T> Object paginated(PaginationMetadata paginationMetadata, T items) {
        Map<String, Object> data = new HashMap<>();
        data.put("page_data", items);
        data.put("pagination", paginationMetadata);
        return data;
    }

    @Getter
    @Accessors(chain = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PaginationMetadata {
        private final int limit;
        private final long totalElements;
        private final int totalPages;
        private final int currentPage;

        public PaginationMetadata(int limit, long totalElements, int totalPages, int currentPage) {
            this.limit = limit;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
            this.currentPage = currentPage;
        }
    }
}
