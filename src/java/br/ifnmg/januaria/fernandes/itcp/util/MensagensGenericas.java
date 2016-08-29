package br.ifnmg.januaria.fernandes.itcp.util;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author alisson
 */
public abstract class MensagensGenericas implements Serializable{
        
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
    
    public void msgPanelErroInesperadoGeneric(){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro inesperado", "Contate o administrador do sistema!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
    public void msgGrow(String title, String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(title, msg));
        RequestContext.getCurrentInstance().update("frmGrowl");
    }
    
    public void msgPanelErro(String title, String msg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
            RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
}
