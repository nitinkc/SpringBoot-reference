package com.springHelloWorld.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class StudentRequestBody {
    private int count;
    @JsonProperty("studentIds")//If the name in the request body differs from variable name
    private List<String> studentIdList;
    private String greeting;
}
