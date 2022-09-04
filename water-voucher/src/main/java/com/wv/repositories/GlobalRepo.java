package com.wv.repositories;

import java.util.Collection;
import java.util.Optional;

public interface GlobalRepo <T>{
	
	public int save(T t);
	public int count();
	public Optional<T> get(Long id);
	public Collection<T> getAll();

}
