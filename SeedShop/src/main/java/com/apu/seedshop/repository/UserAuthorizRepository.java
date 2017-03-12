/*
 * 
 * 
 */
package com.apu.seedshop.repository;

import java.util.List;
import com.apu.seedshop.jpa.UserAuthorization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserAuthorizRepository extends CrudRepository<UserAuthorization, Integer> {
    public List<UserAuthorization> findByLogin(@Param("login") String login);
    public List<UserAuthorization> findByUserId(@Param("userId") Integer userId);
    @Override
    public List<UserAuthorization> findAll();

}
