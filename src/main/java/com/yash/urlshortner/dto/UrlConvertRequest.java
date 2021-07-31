package com.yash.urlshortner.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UrlConvertRequest {

    @NotBlank
    String url;
}
