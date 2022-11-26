package com.laxmi.binding;

import java.io.Serializable;

import lombok.Data;

@Data
public class Pet implements Serializable {

	private Integer id;
	private String type;
	private Double price;
}
