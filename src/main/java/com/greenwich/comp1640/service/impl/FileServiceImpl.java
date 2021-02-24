package com.greenwich.comp1640.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.greenwich.comp1640.config.multipart.FileConfigProperties;
import com.greenwich.comp1640.response.GeneralResponse;
import com.greenwich.comp1640.response.ResponseFactory;
import com.greenwich.comp1640.service.abstr.FileService;
import com.greenwich.comp1640.util.FileUtil;
import com.greenwich.comp1640.util.constant.ResponseStatusCodeConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Autowired
    ResponseFactory responseFactory;

    @Autowired
    FileConfigProperties fileConfigProperties;

    @Override
    public ResponseEntity<GeneralResponse<Object>> uploadFile(MultipartFile file, String filePath, String fileName) {
        try {
            FileUtil.saveFile(file.getInputStream(), filePath);

            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode response = objectMapper.createObjectNode();
            response.put("file_name", fileName);
            response.put("file_path", fileConfigProperties.getUploadFileDir() + "/" + fileName);

            return responseFactory.success(response);
        } catch (Exception ex) {
            log.error("Error when save file, detail: {}", ex.getMessage());
            return responseFactory.fail(ex.getMessage(), ResponseStatusCodeConst.INTERNAL_SERVER_ERROR, null);
        }
    }
}
