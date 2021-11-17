package br.com.pharmacy.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.pharmacy.DAO.FornecedoresDAO;
import br.com.pharmacy.domain.Fornecedores;
import br.com.pharmacy.util.JSFUtil;

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
			ArrayList<Fornecedores> lista = fdao.listar();
			itens = new ListDataModel<Fornecedores>(lista);
			
			// The third step I need to call message method in JSFUtil class.
			JSFUtil.adicionarMensagemSucesso("Fornecedor salvo com sucesso!");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.getStackTrace();
		}
	}
	
	public void beforeExcluir() {
		fornecedores = itens.getRowData();
	}
	
	public void excluir() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.deletar(fornecedores);
			
			ArrayList<Fornecedores> lista = fdao.listar();
			itens = new ListDataModel<Fornecedores>(lista);
			
			JSFUtil.adicionarMensagemSucesso("Fornecedores excluido com sucesso!!!");
			
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Não é possível excluir um fornecedores que tenha produtos associados.");
			e.printStackTrace();
		}
	}
}
