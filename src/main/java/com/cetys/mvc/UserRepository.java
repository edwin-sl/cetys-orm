package com.cetys.mvc;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by edwin on May, 2020
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    // find Limiter By Attribute Logic … OrderBy Attribute Order

    // find - By Email - OrderBy Name Desc
    // email == s // email.compareTo(s)
    List<User> findByEmailOrderByNameDesc(String s);
    // find - By Email Containing OrderBy Name Desc
    List<User> findByEmailContainsOrderByNameDesc(String s);
    // find - By Age GreaterThanEqual - - -
    List<User> findByAgeGreaterThanEqual(int i);
    // find - By [Email Containing] And [Age GreaterThanEqual]
    List<User> findByEmailContainingAndAgeGreaterThanEqual(String s, int i);
}
