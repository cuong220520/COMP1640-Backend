package com.greenwich.comp1640.service.abstr;

import com.greenwich.comp1640.dto.request.demo.CreateDemoRequestDto;
import com.greenwich.comp1640.dto.request.demo.UpdateDemoRequestDto;
import com.greenwich.comp1640.response.GeneralResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface DemoService {

    ResponseEntity<GeneralResponse<Object>> getDemoList(Pageable pageable);

    ResponseEntity<GeneralResponse<Object>> createDemo(CreateDemoRequestDto createDemoRequestDto);

    ResponseEntity<GeneralResponse<Object>> updateDemo(Long id, UpdateDemoRequestDto updateDemoRequestDto);

    ResponseEntity<GeneralResponse<Object>> getDemo(Long id);

}
