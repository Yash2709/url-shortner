package com.yash.urlshortner.controller;

import com.yash.urlshortner.dto.UrlConvertRequest;
import com.yash.urlshortner.model.UrlData;
import com.yash.urlshortner.repository.UrlDataRepository;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ShortUrlToActualUrlController {

    final UrlDataRepository urlDataRepository;

    public ShortUrlToActualUrlController(UrlDataRepository urlDataRepository) {
        this.urlDataRepository = urlDataRepository;
    }

    @PostMapping("/makeUrlShort")
    public String convertUrlToShortUrl(@Valid @RequestBody UrlConvertRequest convertRequest) {
        UrlData data = new UrlData() {{
            setActualUrl(convertRequest.getUrl());
            setShortUrl(convertRequest.getUrl());
        }};

        try {
            urlDataRepository.save(data);
        } catch (Exception e) {
            throw e;
        }

        return "";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
