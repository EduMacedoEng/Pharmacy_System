package br.com.pharmacy.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil {
	// The first step to show messages in application I need to create this class with message methods.
	public static void adicionarMensagemSucesso (String mensagem) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
		FacesContext contexto = FacesContext.getCurrentInstance();
		
		contexto.addMessage(null, msg);
	}
	
	public static void adicionarMensagemErro (String mensagem) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
		FacesContext contexto = FacesContext.getCurrentInstance();
		
		contexto.addMessage(null, msg);
	}
}
