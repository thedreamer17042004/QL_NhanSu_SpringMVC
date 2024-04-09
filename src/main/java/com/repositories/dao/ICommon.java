package com.repositories.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface ICommon <K,T>{
	boolean update(K entity);	
	boolean insert(K entity);
	boolean delete(K entity);
	K findById(T value);
	
}
