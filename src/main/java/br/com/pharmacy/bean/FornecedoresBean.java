package br.com.pharmacy.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.pharmacy.DAO.FornecedoresDAO;
import br.com.pharmacy.domain.Fornecedores;

@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedoresBean {
	private ListDataModel<Fornecedores> itens;
	private Fornecedores fornecedores;

	public Fornecedores getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}

	public ListDataModel<Fornecedores> getItens() {
		return itens;
	}

	public void setItens(ListDataModel<Fornecedores> itens) {
		this.itens = itens;
	}
	
	@PostConstruct
	public void prepararPesquisa() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			ArrayList<Fornecedores> lista = fdao.listar();
			
			itens = new ListDataModel<Fornecedores>(lista);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void beforeNovo() {
		fornecedores = new Fornecedores();
	}
	
	public void novo() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.salvar(fornecedores);
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	}
	
}
