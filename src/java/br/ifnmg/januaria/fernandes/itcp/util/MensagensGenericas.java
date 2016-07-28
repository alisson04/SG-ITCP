package br.ifnmg.januaria.fernandes.itcp.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author alisson
 */
public abstract class MensagensGenericas {
    FacesContext context = FacesContext.getCurrentInstance();
    
    public void msgGrowSaveGeneric() {
        context.addMessage(null, new FacesMessage("SUCESSO!", "As informações foram salvas. "));
        RequestContext.getCurrentInstance().update("frmGrowl");
    }
    
    public void msgGrowDeleteGeneric() {
        context.addMessage(null, new FacesMessage("SUCESSO!", "As informações foram excluidas. "));
        RequestContext.getCurrentInstance().update("frmGrowl");
    }
    
    public void msgGrowUpdateGeneric() {
        context.addMessage(null, new FacesMessage("SUCESSO!", "As informações foram atualizadas. "));
        RequestContext.getCurrentInstance().update("frmGrowl");
    }
    
    public void msgPanelErro(String title, String msg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
            RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
}
