package com.springHelloWorld.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Temperature {
    String from;
    String to;
    List<Double> values;
}
