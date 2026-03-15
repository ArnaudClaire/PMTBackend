package com.mooc.formulaone.dao;

import com.mooc.formulaone.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
