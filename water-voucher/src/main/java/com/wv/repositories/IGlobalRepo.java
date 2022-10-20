package com.wv.repositories;

import java.util.Collection;
import java.util.Optional;

public interface IGlobalRepo <T>{
	
	public void save(T t);
	public void delete(Long id);
	public Optional<T> get(Long id);
	public Collection<T> getAll();

}
