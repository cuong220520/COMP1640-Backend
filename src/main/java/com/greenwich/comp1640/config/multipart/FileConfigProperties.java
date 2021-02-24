package com.greenwich.comp1640.config.multipart;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "multipart")
@Getter
@Setter
public class FileConfigProperties {

    private String uploadFileDir;

}
