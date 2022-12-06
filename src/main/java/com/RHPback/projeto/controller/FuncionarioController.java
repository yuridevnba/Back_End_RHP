package com.RHPback.projeto.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.RHPback.projeto.entities.Funcionario;
import com.RHPback.projeto.service.FuncionarioService;

@RestController
@RequestMapping(value = "/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;
	
       /////////////////// GET//////////////////
	/////////////////////////////////////////////
	
	@GetMapping
	public ResponseEntity<List<Funcionario>> findAll() {

		List<Funcionario> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Funcionario> findById(@PathVariable Long id) {
		Funcionario obj = service.findById(id);//
		return ResponseEntity.ok().body(obj);
	}
           ///////////////////////POST//////////////////////
	/////////////////////////////////////////////////////////////
	
	@PostMapping
	public ResponseEntity<Funcionario> insert(@RequestBody Funcionario obj) {
		obj = service.insert(obj);
		// return ResponseEntity.ok().body( obj);// retorna 200 q n é o ideal

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdFuncionario()).toUri();

		return ResponseEntity.created(uri).body(obj);
	}
        //////////////// DELETE//////////////////////////
	////////////////////////////////////////////////////////
	@DeleteMapping(value = "/{id}")

	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();

	}
	
	////////////////////// PUT////////////////////////
	///////////////////////////////////////////////////

	@PutMapping(value="/{id}")

	public ResponseEntity<Funcionario> update(@PathVariable Long id,@RequestBody Funcionario obj){
	 obj=service.update(id, obj);
	return ResponseEntity.ok().body(obj);
}
}

