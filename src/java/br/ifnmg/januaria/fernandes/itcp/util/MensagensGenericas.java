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

    //Mensagens de sucesso em GROW genéricas
    public void msgGrowSaveGeneric() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("SUCESSO!", "Informações salvas. "));
        RequestContext.getCurrentInstance().update("frmGrowl");
    }

    public void msgGrowDeleteGeneric() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("SUCESSO!", "Informações excluidas. "));
        RequestContext.getCurrentInstance().update("frmGrowl");
    }

    public void msgGrowUpdateGeneric() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("SUCESSO!", "Informações atualizadas. "));
        RequestContext.getCurrentInstance().update("frmGrowl");
    }

    //Mensagem de erro inesperado em painel genérica
    public void msgPanelErroInesperadoGeneric() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erro inesperado", "Contate o administrador do sistema!");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    //Mensagem de erro em painel customizavel
    public void msgPanelErroCustomizavel(String title, String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    //Mensagem de erro Growlcustomizavel
    public void msgGrowlErroCustomizavel(String title, String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg));
    }
    
    public void msgGrowlInfoCustomizavel(String title, String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg));
    }
}
