package com.aluracursos.forohub.repository;


import com.aluracursos.forohub.domain.usuario.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM Usuario u WHERE u.correoElectronico = :correoElectronico")
    Optional<User> findByCorreo(String correoElectronico);

    UserDetails findByCorreoElectronico(String username);
}
