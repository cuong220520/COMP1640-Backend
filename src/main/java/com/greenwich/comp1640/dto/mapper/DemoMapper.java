package com.greenwich.comp1640.dto.mapper;

import com.greenwich.comp1640.dto.request.demo.CreateDemoRequestDto;
import com.greenwich.comp1640.dto.request.demo.UpdateDemoRequestDto;
import com.greenwich.comp1640.dto.response.DemoResponseDto;
import com.greenwich.comp1640.model.Demo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class DemoMapper {

    private DemoMapper() {};

    public static DemoResponseDto toDto(Demo demo) {
        return new DemoResponseDto()
                .setId(demo.getId())
                .setFirstName(demo.getFirstName())
                .setLastName(demo.getLastName())
                .setEmail(demo.getEmail())
                .setPhoneNumber(demo.getPhoneNumber());
    }

    public static List<DemoResponseDto> toListDto(List<Demo> demoList) {
        List<DemoResponseDto> demoResponseDtoList = new ArrayList<>();

        for (Demo demo : demoList) {
            demoResponseDtoList.add(toDto(demo));
        }

        return demoResponseDtoList;
    }

    public static Demo createFromDto(CreateDemoRequestDto createDemoRequestDto) {
        Demo demo = new Demo();

        demo.setFirstName(createDemoRequestDto.getFirstName());
        demo.setLastName(createDemoRequestDto.getLastName());
        demo.setEmail(createDemoRequestDto.getEmail());
        demo.setPhoneNumber(createDemoRequestDto.getPhoneNumber());

        return demo;
    }

    public static Demo updateFromDto(Demo demo, UpdateDemoRequestDto updateDemoRequestDto) {
        if (updateDemoRequestDto.getFirstName() != null && !updateDemoRequestDto.getFirstName().isEmpty()) {
            demo.setFirstName(updateDemoRequestDto.getFirstName());
        }

        if (updateDemoRequestDto.getLastName() != null && !updateDemoRequestDto.getLastName().isEmpty()) {
            demo.setLastName(updateDemoRequestDto.getLastName());
        }

        if (updateDemoRequestDto.getEmail() != null && !updateDemoRequestDto.getEmail().isEmpty()) {
            demo.setEmail(updateDemoRequestDto.getEmail());
        }

        if (updateDemoRequestDto.getPhoneNumber() != null && !updateDemoRequestDto.getPhoneNumber().isEmpty()) {
            demo.setPhoneNumber(updateDemoRequestDto.getPhoneNumber());
        }

        return demo;
    }

}
