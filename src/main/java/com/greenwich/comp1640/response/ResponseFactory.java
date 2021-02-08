package com.greenwich.comp1640.response;

import com.greenwich.comp1640.locale.LocaleService;
import com.greenwich.comp1640.util.constant.ResponseStatusCodeConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class ResponseFactory {
    /*@Autowired
    ECDSA ecdsa;*/

    /*@Autowired
    APIKeyConfig apiKeyConfig;*/

    @Autowired
    LocaleService localeService;

    public <D> ResponseEntity success(D data) {
        CustomResponseStatusCode customResponseStatusCode = new CustomResponseStatusCode(
                ResponseStatusCodeConst.SUCCESS.getCode(), (Object[]) null, localeService);
        customResponseStatusCode.setLocaleService(localeService);
        GeneralResponse responseObject = GeneralResponse.createResponse(data);
        responseObject.setSuccess(true);
        responseObject.setErrorCode(customResponseStatusCode.getCode());
        responseObject.setMessage(customResponseStatusCode.getMessage());
        // String sign = sign(responseObject);
        // responseObject.setSign(sign);
        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.ok().headers(responseHeaders).body(responseObject);
    }

    public <D> ResponseEntity fail(D data,
                                   ResponseStatusCodeConst code, @Nullable Object[] args) {
        CustomResponseStatusCode customResponseStatusCode = new CustomResponseStatusCode(code.getCode(), args, localeService);
        GeneralResponse responseObject = GeneralResponse.createResponse(data);
        responseObject.setErrorCode(customResponseStatusCode.getCode());
        responseObject.setMessage(customResponseStatusCode.getMessage());
        return ResponseEntity.status(code.getHttpCode()).body(responseObject);
    }

    /*@SneakyThrows
    public String sign(GeneralResponse response) {
        ObjectMapper oMapper = new ObjectMapper();
        oMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        Map<String, Object> map = oMapper.convertValue(response, Map.class);
        String message = Message.createMessage(map);
        return ecdsa.sign(apiKeyConfig.getPrivateKey(), message);
    }*/
}
