package com.springHelloWorld.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class StudentDtoClass {
    private int count;
    @JsonProperty("data") //This will appear in the JSON response.
    private List<StudentDto> studentDtoList;
}
