package com.greenwich.comp1640.controller;

import com.greenwich.comp1640.dto.request.faculty.CreateFacultyRequestDto;
import com.greenwich.comp1640.dto.request.faculty.UpdateFacultyRequestDto;
import com.greenwich.comp1640.response.GeneralResponse;
import com.greenwich.comp1640.service.abstr.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/faculty")
public class FacultyController {

    @Autowired
    FacultyService facultyService;

    @PostMapping
    public ResponseEntity<GeneralResponse<Object>> createFaculty(@RequestBody @Valid CreateFacultyRequestDto createFacultyRequestDto) {
        return facultyService.createFaculty(createFacultyRequestDto);
    }

    @GetMapping
    public ResponseEntity<GeneralResponse<Object>> getAllFaculty() {
        return facultyService.getFacultyList();
    }

    @PutMapping(value = "/update/{code}")
    public ResponseEntity<GeneralResponse<Object>> updateFaculty(
            @PathVariable("code") String code,
            @Valid @RequestBody UpdateFacultyRequestDto updateFacultyRequestDto) {
        return facultyService.updateFaculty(code, updateFacultyRequestDto);
    }

    @GetMapping(value = "/{code}")
    public ResponseEntity<GeneralResponse<Object>> getFaculty(@PathVariable("code") String code) {
        return facultyService.getFaculty(code);
    }

    @GetMapping(value = "/get-by-coordinator/{coordinatorUsername}")
    public ResponseEntity<GeneralResponse<Object>> getFacultyByCoordinator(@PathVariable("coordinatorUsername") String username) {
        return facultyService.getFacultyByCoordinatorUsername(username);
    }
}
