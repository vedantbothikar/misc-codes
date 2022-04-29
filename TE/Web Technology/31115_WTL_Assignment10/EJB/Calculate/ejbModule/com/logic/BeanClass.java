package com.logic;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class BeanClass
 */
@Stateless
@LocalBean
public class BeanClass implements BeanClassLocal {
	 @Override
	    public Integer squarenumber(int a) {
	        return a*a;
	    }
	@Override
	    public Integer cubenumber(int b) {
	        return b*b*b;
	    }
	@Override
	    public Double squareRoot(int c) {
	        return Math.sqrt(c);
	    }
}
