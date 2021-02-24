package com.greenwich.comp1640.service.abstr;

import com.greenwich.comp1640.response.GeneralResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileService {

    ResponseEntity<GeneralResponse<Object>> uploadFile(MultipartFile file, String filePath, String fileName);

}
