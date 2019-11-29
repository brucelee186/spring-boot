package com.framework.test.mq;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable{

	private static final long serialVersionUID = 6331462105696784693L;
	private int id;
	private String name;
	
}
