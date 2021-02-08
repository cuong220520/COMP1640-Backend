package com.greenwich.comp1640.service.impl;

import com.greenwich.comp1640.dao.DemoDao;
import com.greenwich.comp1640.dto.mapper.DemoMapper;
import com.greenwich.comp1640.dto.request.demo.CreateDemoRequestDto;
import com.greenwich.comp1640.dto.request.demo.UpdateDemoRequestDto;
import com.greenwich.comp1640.dto.response.DemoResponseDto;
import com.greenwich.comp1640.exception.CustomExceptions;
import com.greenwich.comp1640.model.Demo;
import com.greenwich.comp1640.response.GeneralResponse;
import com.greenwich.comp1640.response.ResponseFactory;
import com.greenwich.comp1640.service.abstr.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    DemoDao demoDao;

    @Autowired
    ResponseFactory responseFactory;

    @Override
    public ResponseEntity<GeneralResponse<Object>> getDemoList(Pageable pageable) {
        Page<Demo> demoList = demoDao.findAll(pageable);

        List<DemoResponseDto> demoResponseDtoList = DemoMapper.toListDto(demoList.getContent());

        GeneralResponse.PaginationMetadata paginationMetadata = new GeneralResponse.PaginationMetadata(
                demoList.getSize(),
                demoList.getTotalElements(),
                demoList.getTotalPages(),
                demoList.getNumber()
        );

        return responseFactory.success(GeneralResponse.paginated(paginationMetadata, demoResponseDtoList));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> createDemo(CreateDemoRequestDto createDemoRequestDto) {
        Demo demo = demoDao.findByEmail(createDemoRequestDto.getEmail());

        if (demo != null) {
            throw new CustomExceptions.DuplicateEntityException(String.format("Duplicated demo with email: %s", createDemoRequestDto.getEmail()));
        }

        Demo newDemo = DemoMapper.createFromDto(createDemoRequestDto);

        demoDao.saveDemo(newDemo);

        return responseFactory.success(DemoMapper.toDto(newDemo));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> updateDemo(Long id, UpdateDemoRequestDto updateDemoRequestDto) {
        Optional<Demo> demoOptional = demoDao.findById(id);

        if (!demoOptional.isPresent()) {
            throw new CustomExceptions.EntityNotFoundException(String.format("Can not find demo with id: %d", id));
        }

        Demo demo = demoOptional.get();
        Demo updatedDemo = DemoMapper.updateFromDto(demo, updateDemoRequestDto);

        demoDao.saveDemo(updatedDemo);

        return responseFactory.success(DemoMapper.toDto(updatedDemo));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> getDemo(Long id) {
        Optional<Demo> demoOptional = demoDao.findById(id);

        if (!demoOptional.isPresent()) {
            throw new CustomExceptions.EntityNotFoundException(String.format("Can not find demo with id: %d", id));
        }

        Demo demo = demoOptional.get();

        return responseFactory.success(DemoMapper.toDto(demo));
    }

}
