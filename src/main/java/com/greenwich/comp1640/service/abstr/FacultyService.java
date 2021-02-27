package com.greenwich.comp1640.service.abstr;

import com.greenwich.comp1640.dto.request.faculty.CreateFacultyRequestDto;
import com.greenwich.comp1640.dto.request.faculty.UpdateFacultyRequestDto;
import com.greenwich.comp1640.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

public interface FacultyService {

    ResponseEntity<GeneralResponse<Object>> getFacultyList();

    ResponseEntity<GeneralResponse<Object>> createFaculty(CreateFacultyRequestDto createFacultyRequestDto);

    ResponseEntity<GeneralResponse<Object>> updateFaculty(String code, UpdateFacultyRequestDto updateFacultyRequestDto);

    ResponseEntity<GeneralResponse<Object>> getFaculty(String code);

    ResponseEntity<GeneralResponse<Object>> getFacultyByCoordinatorId(Long coordinatorId);
    
}
