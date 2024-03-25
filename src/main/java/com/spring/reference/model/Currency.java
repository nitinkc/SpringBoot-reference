package com.spring.reference.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Currency {
    String from;
    String to;
    List<Double> values;
}
