package br.ifnmg.januaria.fernandes.itcp.util;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author alisson
 */
public abstract class MensagensGenericas implements Serializable {
    
    //Mensagem generica de campo obrigatório
    public String msgGenericaCampoObrigatorio(){
        return "Campo obrigatório!";
    }

    //Mensagens de GROW genéricas
    public void msgGrowSaveGeneric() {//Salvar
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "Informações salvas."));
        RequestContext.getCurrentInstance().update("frmMsgGenerico");
    }

    public void msgGrowDeleteGeneric() {//Excluir
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "Informações excluidas."));
        RequestContext.getCurrentInstance().update("frmMsgGenerico");
    }

    public void msgGrowUpdateGeneric() {//Atualizar
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "Informações atualizadas."));
        RequestContext.getCurrentInstance().update("frmMsgGenerico");
    }

    public void msgGrowlErroCustomizavel(String title, String msg) {//Erro customizavel
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg));
        RequestContext.getCurrentInstance().update("frmMsgGenerico");
    }

    public void msgGrowlInfoCustomizavel(String title, String msg) {//Informação customizavel
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg));
        RequestContext.getCurrentInstance().update("frmMsgGenerico");
    }

    //Mensagem PAINEL genérica
    public void msgPanelErroInesperadoGeneric() {//Erro inesperado
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erro inesperado", "Contate o administrador do sistema!");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public void msgPanelErroCustomizavel(String title, String msg) {//Erro inesprado com msg customizavel
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
}
