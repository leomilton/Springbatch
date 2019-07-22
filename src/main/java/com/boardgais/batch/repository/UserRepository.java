package com.boardgais.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boardgais.batch.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
