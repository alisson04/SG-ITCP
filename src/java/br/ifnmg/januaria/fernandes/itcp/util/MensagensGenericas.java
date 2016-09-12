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

    //Mensagens de GROW genéricas
    public void msgGrowSaveGeneric() {//Salvar
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "Informações salvas."));
    }

    public void msgGrowDeleteGeneric() {//Excluir
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "Informações excluidas."));
    }

    public void msgGrowUpdateGeneric() {//Atualizar
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "Informações atualizadas."));
    }

    public void msgGrowlErroCustomizavel(String title, String msg) {//Erro customizavel
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg));
    }

    public void msgGrowlInfoCustomizavel(String title, String msg) {//Informação customizavel
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg));
    }

    //Mensagem PAINEL genérica
    public void msgPanelErroInesperadoGeneric() {//Erro inesperado
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erro inesperado", "Contate o administrador do sistema!");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public void msgPanelErroCustomizavel(String title, String msg) {//Erro inesprado customizavel
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
}
