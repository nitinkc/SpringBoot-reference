package com.spring.reference.dto;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefTableDTO implements Serializable {
    private Integer id;
    private String name;
}
