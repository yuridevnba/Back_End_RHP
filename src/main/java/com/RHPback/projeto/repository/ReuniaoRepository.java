package com.RHPback.projeto.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.RHPback.projeto.entities.Reuniao;


@Repository
public interface ReuniaoRepository extends JpaRepository<Reuniao, Long> {


    boolean existsByDataAndLocalAndHorarioInicioAndHorarioFinal(String data, String local, String HorarioInicio,String HorarioFinal);
}
