/*
 * 
 * 
 */
package com.apu.seedshop.repository;

import java.util.List;
import com.apu.seedshop.jpa.UserGender;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserGenderRepository extends CrudRepository<UserGender, Integer> {
    public List<UserGender> findByName(@Param("name") String name);
    public List<UserGender> findByGenderId(@Param("genderId") Integer id);
    @Override
    public List<UserGender> findAll();

    @Override
    public <S extends UserGender> S save(S s);
}
