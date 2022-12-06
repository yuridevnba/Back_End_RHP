package com.RHPback.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RHPback.projeto.entities.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {
}

