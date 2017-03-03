/*
 * 
 * 
 */
package com.apu.seedshop.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.apu.seedshop.jpa.Users;

public interface UsersRepository extends CrudRepository<Users, Integer> {
    public List<Users> findBySecNameAndFirstName(String secName, String firstName);
    @Override
    public List<Users> findAll();
}
