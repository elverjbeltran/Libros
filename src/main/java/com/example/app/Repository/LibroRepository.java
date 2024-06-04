package com.example.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{

}
