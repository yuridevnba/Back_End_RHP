package com.RHPback.projeto.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_funcionario")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor


public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;

	////////////// ATRIBUTOS////////////////////////
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFuncionario;

	@Column(nullable = false, name = "nome", length = 50)
	private String nome;

	@Column(nullable = false, unique = true, name = "email", length = 50)
	private String email;

	@Column(name = "senha", length = 50)
	private String senha;
	
	

	////////////////////// ASSOCIAÇÕES/////////////////////
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "inscritos")
	private Set<Reuniao> reuniao = new HashSet<>();

	
	
	///////////////////////////// COLLETCTIONS GET/////////

	
	public Set<Reuniao> getReuniao() {
		return reuniao;
	}
	

}
