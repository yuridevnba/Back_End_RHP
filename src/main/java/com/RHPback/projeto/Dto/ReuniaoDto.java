package com.RHPback.projeto.Dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.RHPback.projeto.entities.Funcionario;

import lombok.Data;

@Data
public class ReuniaoDto {
	@NotBlank
    private String local;

   
    @NotBlank
    private String data;

    @NotBlank
    private String horarioInicio;

    @NotBlank
    private String horarioFinal;

    private String criadorDoEvento;

   

    private List<Funcionario> inscritos;

    private String quantidade;

   

    private String email;

}
