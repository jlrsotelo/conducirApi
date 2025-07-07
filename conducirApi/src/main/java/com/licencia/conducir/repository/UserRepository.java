package com.licencia.conducir.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.licencia.conducir.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    @Query(value = "select u from UserEntity u where u.nomUser =:nomUser and u.pwdUser =:pwdUser and u.state='1'") // JPQL ( Java Persistence Query Language)
    Optional<UserEntity> validar(@Param("nomUser") String nomUser, @Param("pwdUser") String pwdUser);
}