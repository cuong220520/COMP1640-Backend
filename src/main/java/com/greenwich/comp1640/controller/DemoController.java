package com.greenwich.comp1640.controller;

import com.greenwich.comp1640.dto.parameter.PagingOptionDto;
import com.greenwich.comp1640.dto.request.demo.CreateDemoRequestDto;
import com.greenwich.comp1640.dto.request.demo.UpdateDemoRequestDto;
import com.greenwich.comp1640.response.GeneralResponse;
import com.greenwich.comp1640.service.abstr.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/demo")
public class DemoController {

    @Autowired
    DemoService demoService;

    @GetMapping(value = "/")
    public ResponseEntity<GeneralResponse<Object>> getDemoList(PagingOptionDto pagingOptionDto, String sort) {
        Pageable pageable = pagingOptionDto.createPageable(pagingOptionDto.getPage(), pagingOptionDto.getLimit(), sort);

        return demoService.getDemoList(pageable);
    }

    @PostMapping(value = "/")
    public ResponseEntity<GeneralResponse<Object>> createDemo(@Valid @RequestBody CreateDemoRequestDto createDemoRequestDto) {
        return demoService.createDemo(createDemoRequestDto);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<GeneralResponse<Object>> updateDemo(@PathVariable("id") Long id, @Valid @RequestBody UpdateDemoRequestDto updateDemoRequestDto) {
        return demoService.updateDemo(id, updateDemoRequestDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GeneralResponse<Object>> getDemo(@PathVariable("id") Long id) {
        return demoService.getDemo(id);
    }

}
