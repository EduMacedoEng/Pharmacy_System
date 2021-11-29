package br.com.pharmacy.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.pharmacy.DAO.FornecedoresDAO;
import br.com.pharmacy.domain.Fornecedores;
import br.com.pharmacy.util.JSFUtil;

@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedoresBean {
	private ArrayList<Fornecedores> itens;
	private ArrayList<Fornecedores> itensFiltrados;
	private Fornecedores fornecedores;

	public Fornecedores getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}

	public ArrayList<Fornecedores> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fornecedores> itens) {
		this.itens = itens;
	}
	
	public ArrayList<Fornecedores> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Fornecedores> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			itens = fdao.listar();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
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
			
			// Here I allow the table when to reload to be updated.
			itens = fdao.listar();
			
			// The third step I need to call message method in JSFUtil class.
			JSFUtil.adicionarMensagemSucesso("Fornecedor salvo com sucesso!");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.getStackTrace();
		}
	}
	
	public void excluir() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.deletar(fornecedores);
			
			itens = fdao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Fornecedores excluido com sucesso!!!");
			
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Não é possível excluir um fornecedores que tenha produtos associados.");
			e.printStackTrace();
		}
	}
	
	public void editar() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.editar(fornecedores);
			
			itens = fdao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Fornecedores editado com sucesso!!!");
			
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Não é possível editar um fornecedor(es) com produtos associados.");
			e.printStackTrace();
		}
	} 
}
