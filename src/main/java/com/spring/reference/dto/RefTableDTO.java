package com.spring.reference.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RefTableDTO implements Serializable {
    private Integer id;
    private String name;
}
