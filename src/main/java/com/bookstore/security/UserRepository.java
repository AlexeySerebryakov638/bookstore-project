package com.bookstore.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
		
    //@Query("SELECT u FROM users u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);
}