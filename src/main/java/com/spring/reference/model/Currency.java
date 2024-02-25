package com.spring.reference.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Currency {
    String from;
    String to;
    List<Double> values;
}
