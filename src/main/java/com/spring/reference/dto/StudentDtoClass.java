package com.spring.reference.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.*;

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
