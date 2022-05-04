package fr.diginamic.jpa.dao.dao;

import java.util.List;

public interface Idao<T> {
	
	boolean add(T e) throws Exception;
	boolean update(T e) throws Exception;
	boolean delete(T e) throws Exception;
	T getOne(T e) throws Exception;
	List<T> getAll() throws Exception;

}
