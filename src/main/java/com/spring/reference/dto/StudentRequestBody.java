package com.spring.reference.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.*;

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
