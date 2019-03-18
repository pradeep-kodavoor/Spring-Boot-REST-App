package com.practice.springboot.basics.SpringBootRESTApp.util;

import java.util.List;

import javax.validation.Valid;

import lombok.Data;
import lombok.Delegate;

@Data
public class ValidateList<E> implements List<E> {

	@Valid
	@Delegate
	private List<E> list;

}
