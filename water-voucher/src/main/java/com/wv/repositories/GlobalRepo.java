package com.wv.repositories;

import java.util.Collection;
import java.util.Optional;

public interface GlobalRepo <T>{
	
	public void save(T t);
	public int count();
	public Optional<T> get(Integer id);
	public Collection<T> getAll();

}
