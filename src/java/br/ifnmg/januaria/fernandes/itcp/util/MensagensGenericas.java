package br.ifnmg.januaria.fernandes.itcp.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author alisson
 */
public abstract class MensagensGenericas {
        
    public void msgGrowSaveGeneric() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("SUCESSO!", "As informações foram salvas. "));
        RequestContext.getCurrentInstance().update("frmGrowl");
    }
    
    public void msgGrowDeleteGeneric() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("SUCESSO!", "As informações foram excluidas. "));
        RequestContext.getCurrentInstance().update("frmGrowl");
    }
    
    public void msgGrowUpdateGeneric() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("SUCESSO!", "As informações foram atualizadas. "));
        RequestContext.getCurrentInstance().update("frmGrowl");
    }
    
    public void msgPanelErroInesperadoGeneric(){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro inesperado", "Contate o administrador do sistema!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
    public void msgPanelErro(String title, String msg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
            RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
}
