package com.greenwich.comp1640.controller;

import com.greenwich.comp1640.config.multipart.FileConfigProperties;
import com.greenwich.comp1640.response.GeneralResponse;
import com.greenwich.comp1640.response.ResponseFactory;
import com.greenwich.comp1640.service.abstr.FileService;
import com.greenwich.comp1640.util.DateUtil;
import com.greenwich.comp1640.util.constant.ResponseStatusCodeConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping(value = "/api/file")
public class FileController {

    @Autowired
    FileConfigProperties fileConfigProperties;

    @Autowired
    FileService fileService;

    @Autowired
    ResponseFactory responseFactory;

    String[] acceptedFileTypes = {".png", ".jpeg", ".docx", ".doc", ".pdf"};

    @PostMapping(value = "/upload")
    public ResponseEntity<GeneralResponse<Object>> uploadFile(
            @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        boolean isValidType = false;

        for (String type : acceptedFileTypes) {
            if (file.getOriginalFilename().endsWith(type)) {
                isValidType = true;
            }
        }

        try {
            if (isValidType) {
                String fileName = DateUtil.currentEpochSecond() + "_" + file.getOriginalFilename();
                String filePath = request.getServletContext().getRealPath("")
                        + fileConfigProperties.getUploadFileDir()
                        + File.separator
                        + fileName;

                System.out.println(fileService.uploadFile(file, filePath, fileName).toString());

                return fileService.uploadFile(file, filePath, fileName);
            } else {
                return responseFactory.fail("Invalid file type", ResponseStatusCodeConst.VALIDATION_ERROR, null);
            }
        } catch (Exception ex) {
            return responseFactory.fail(ex.getMessage(), ResponseStatusCodeConst.VALIDATION_ERROR, null);
        }
    }

}
