package com.RHPback.projeto.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.RHPback.projeto.entities.RespostaModelo;
import com.RHPback.projeto.entities.Reuniao;
import com.RHPback.projeto.repository.ReuniaoRepository;
import com.RHPback.projeto.service.exceptions.ResourceNotFoundException;

@Service
public class ReuniaoService {
	
	public RespostaModelo rm;
    final ReuniaoRepository eventoRepository;

    public ReuniaoService(ReuniaoRepository eventoRepository){
        this.eventoRepository = eventoRepository;
    }

    @Transactional
    public ResponseEntity<?> save(Reuniao evento) {
    	if(evento.getData().equals("")) {
    		rm.setMensagem("Todos os campos são obrigatórios!");
    		return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
    	}if(evento.getHorarioInicio().equals("")) {
    		rm.setMensagem("Todos os campos são obrigatórios!");
    		return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
    		
    	}if(evento.getHorarioFinal().equals("")) {
    		rm.setMensagem("Todos os campos são obrigatórios!");
    		return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
    	}else {
    		return new ResponseEntity<Reuniao>(eventoRepository.save(evento), HttpStatus.OK);
    	}
    	
      
    
    }


    public boolean existByDataAndLocalAndHorarioInicioAndHorarioFinal(String data, String local, String horarioInicio,String horarioFinal) {
        return eventoRepository.existsByDataAndLocalAndHorarioInicioAndHorarioFinal(data, local, horarioInicio,horarioFinal);
    }

    public List<Reuniao> findAll() {
        return eventoRepository.findAll();
    }

    public Reuniao findById(Long id) {
    	Optional<Reuniao> obj = eventoRepository.findById(id);
    	return obj.orElseThrow(()-> new ResourceNotFoundException(id));
      
    }

    @Transactional
    public void delete(Reuniao evento) {
        eventoRepository.delete(evento);
    }
}
