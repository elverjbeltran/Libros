package com.example.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
