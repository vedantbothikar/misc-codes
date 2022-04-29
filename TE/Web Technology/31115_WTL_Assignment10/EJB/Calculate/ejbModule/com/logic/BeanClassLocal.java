package com.logic;

import javax.ejb.Local;

@Local
public interface BeanClassLocal {
	public Integer squarenumber(int a);
	public Integer cubenumber(int b);
	public Double squareRoot(int c);
}
