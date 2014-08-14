package com.algaworks.conadev.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.conadev.model.Funcionario;

public class Funcionarios implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Funcionario porId(Long id) {
		return manager.find(Funcionario.class, id);
	}
	
	public List<Funcionario> todos() {
		return manager.createQuery("from Funcionario", Funcionario.class).getResultList();
	}
	
	public Funcionario guardar(Funcionario funcionario) {
		return manager.merge(funcionario);
	}
	
	public void remover(Funcionario funcionario) {
		funcionario = porId(funcionario.getId());
		manager.remove(funcionario);
	}

}