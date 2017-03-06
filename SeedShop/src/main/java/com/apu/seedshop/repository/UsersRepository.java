/*
 * 
 * 
 */
package com.apu.seedshop.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.apu.seedshop.jpa.Users;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends CrudRepository<Users, Integer> {
    public List<Users> findByFirstName(@Param("firstName") String firstName);
    public List<Users> findBySecNameAndFirstName(String secName, String firstName);
    @Override
    public List<Users> findAll();
}
