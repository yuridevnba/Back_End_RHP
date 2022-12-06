package com.RHPback.projeto.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.RHPback.projeto.Dto.ReuniaoDto;
import com.RHPback.projeto.entities.Reuniao;
import com.RHPback.projeto.service.ReuniaoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/evento")
public class ReuniaoController {

    final ReuniaoService eventoService;

    public ReuniaoController(ReuniaoService eventoService){
        this.eventoService = eventoService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> saveEvento(@RequestBody @Valid ReuniaoDto eventoDto) {
        if(eventoService.existByDataAndLocalAndHorarioInicioAndHorarioFinal(eventoDto.getData(), eventoDto.getLocal(), eventoDto.getHorarioInicio(),eventoDto.getHorarioFinal())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Horário indisponível");
        }
        var evento = new Reuniao();
        BeanUtils.copyProperties(eventoDto, evento);
        return eventoService.save(evento);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Reuniao>> getAllEventos() {
        return ResponseEntity.status(HttpStatus.OK).body(eventoService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneEvento(@PathVariable(value = "id") Long id) {
        Optional<Reuniao> eventoOptional = Optional.of(eventoService.findById(id));
        if(eventoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(eventoOptional.get());
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Object> deleteEvento(@PathVariable(value = "id") Long id) {
        Optional<Reuniao> eventoOptional = Optional.of(eventoService.findById(id));
        if(eventoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento não encontrado");
        }
        eventoService.delete(eventoOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Evento deletado com sucesso");
    }

    
    @PutMapping(value = "/alterar")
	public ResponseEntity<?> update( @RequestBody Reuniao evento) {
		return eventoService.save(evento);
	}
    }