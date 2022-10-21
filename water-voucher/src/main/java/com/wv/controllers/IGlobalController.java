package com.wv.controllers;

import java.util.Collection;

public interface IGlobalController <T>{
	
	public void save(T t);
	public void delete(Long id);
	public T get(Long id);
	public Collection<T> getAll();

}
