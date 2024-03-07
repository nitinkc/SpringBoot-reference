package com.spring.reference.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class HelloWorldReturnBean {

	private String message;
	private String time;
}
