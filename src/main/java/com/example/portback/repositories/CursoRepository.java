package com.example.portback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.portback.models.CursoVO;

@Repository
public interface CursoRepository extends JpaRepository<CursoVO, Long>{

}
