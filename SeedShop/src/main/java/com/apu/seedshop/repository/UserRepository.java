/*
 * 
 * 
 */
package com.apu.seedshop.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.apu.seedshop.jpa.Appuser;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<Appuser, Long> {
    public List<Appuser> findByFirstName(@Param("firstName") String firstName);
    public List<Appuser> findBySecNameAndFirstName(String secName, String firstName);
    public List<Appuser> findBySessId(@Param("sessId") String sessId);
    @Override
    public List<Appuser> findAll();

    @Override
    public <S extends Appuser> S save(S s);
}
