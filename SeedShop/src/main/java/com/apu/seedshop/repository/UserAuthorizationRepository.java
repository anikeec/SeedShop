/*
 * 
 * 
 */
package com.apu.seedshop.repository;

import java.util.List;
import com.apu.seedshop.jpa.UserAuthorization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserAuthorizationRepository extends CrudRepository<UserAuthorization, Long> {
    public List<UserAuthorization> findByLogin(@Param("login") String login);
    @Override
    public List<UserAuthorization> findAll();

}
