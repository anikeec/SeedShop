/*
 * 
 * 
 */
package com.apu.seedshop.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.apu.seedshop.jpa.User;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {
    public List<User> findByFirstName(@Param("firstName") String firstName);
    public List<User> findBySecNameAndFirstName(String secName, String firstName);
    @Override
    public List<User> findAll();
}
