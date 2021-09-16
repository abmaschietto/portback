package com.example.portback.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.portback.models.CursoVO;

@Repository
public interface CursoRepository extends JpaRepository<CursoVO, Long>{
	
	public Optional<CursoVO> findByUrlCertificado(String certificado);

}
