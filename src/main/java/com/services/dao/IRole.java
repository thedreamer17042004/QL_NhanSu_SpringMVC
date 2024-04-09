package com.services.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.entities.user.Role;

@Service
public interface IRole {
	List<Role> list();
}
