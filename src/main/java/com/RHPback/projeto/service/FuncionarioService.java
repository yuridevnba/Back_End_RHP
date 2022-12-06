package com.RHPback.projeto.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.RHPback.projeto.entities.Funcionario;
import com.RHPback.projeto.repository.FuncionarioRepository;
import com.RHPback.projeto.service.exceptions.DatabaseException;
import com.RHPback.projeto.service.exceptions.ResourceNotFoundException;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;

	public List<Funcionario> findAll() {
		return repository.findAll();
	}

	public Funcionario findById(Long id) {
		Optional<Funcionario> obj = repository.findById(id);
		//return obj.get();
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}

	public Funcionario insert(Funcionario obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		//repository.deleteById(id);
		try {
			repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			//e.printStackTrace();
			throw new ResourceNotFoundException(id);
		}catch (DataIntegrityViolationException e) { // violação de integridades de dados.
			//e.printStackTrace();
			throw new DatabaseException(e.getMessage());// exceção da camada de serviço.
		}

	}
	

	public Funcionario update(Long id, Funcionario obj) {
		try {
		Funcionario entity=repository.getReferenceById(id);
		//Funcionario entity = findById(id);
		updateData(entity, obj);
		return repository.save(entity);
		
		}
			//catch(RuntimeException e) {
		    catch(EntityNotFoundException e) {
			//e.printStackTrace();
			throw new ResourceNotFoundException(id);
		}
		}
	
	private void updateData(Funcionario entity, Funcionario obj) {
		//entity.setNome(obj.getNome());

		entity.setEmail(obj.getEmail());
	
	}

	
}
