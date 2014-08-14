package com.algaworks.conadev.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.conadev.model.Funcionario;
import com.algaworks.conadev.model.StatusFuncionario;
import com.algaworks.conadev.repository.Funcionarios;
import com.algaworks.conadev.util.FacesMessages;
import com.algaworks.conadev.util.Transacional;

@Named
@ViewScoped
public class GestaoFuncionariosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Funcionarios funcionarios;
	
	@Inject
	private FacesMessages messages;
	
	private List<Funcionario> todosFuncionarios;
	private Funcionario funcionarioEdicao;

	public GestaoFuncionariosBean() {
		funcionarioEdicao = new Funcionario();
	}
	
	public void consultar() {
		todosFuncionarios = funcionarios.todos();
	}
	
	@Transacional
	public void excluir(Funcionario funcionario) {
		funcionarios.remover(funcionario);
		consultar();
		
		messages.info("Funcionário excluído com sucesso!");
	}
	
	@Transacional
	public void salvar() {
		funcionarios.guardar(funcionarioEdicao);
		funcionarioEdicao = new Funcionario();
		consultar();
		
		messages.info("Funcionário salvo com sucesso!");
	}
	
	public StatusFuncionario[] getStatuses() {
		return StatusFuncionario.values();
	}
	
	public List<Funcionario> getTodosFuncionarios() {
		return todosFuncionarios;
	}

	public Funcionario getFuncionarioEdicao() {
		return funcionarioEdicao;
	}

	public void setFuncionarioEdicao(Funcionario funcionarioEdicao) {
		this.funcionarioEdicao = funcionarioEdicao;
	}
	
}