package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.AtividadeBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MensagensBean;
import br.ifnmg.januaria.fernandes.itcp.dao.AtividadeDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Atividade;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "CadastroAtividadeView")
@ViewScoped
public class CadastroAtividadeView implements Serializable{
    private Atividade atividade = new Atividade();
    private Empreendimento empreendimento = new Empreendimento();
    private MensagensBean mensagensBean = new MensagensBean();
    private AtividadeBean bean = new AtividadeBean();
    private AtividadeDAO dao = new AtividadeDAO();
    private boolean atividadeSendoVisualizada;
    private boolean skip;
    
    //Métodos
    public void salvarAtividade() {
        FacesMessage msg = new FacesMessage("Successful", "Welcome :");
        FacesContext.getCurrentInstance().addMessage(null, msg);   
        //bean.salvarAtividadeBd(atividade);
        //atividadeSendoVisualizada = true;
    }
    
    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }
    
    

    
    //SETS E GETS
    public boolean isSkip() {
        return skip;
    }
 
    public void setSkip(boolean skip) {
        this.skip = skip;
    }
    
    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public boolean isAtividadeSendoVisualizada() {
        return atividadeSendoVisualizada;
    }

    public void setAtividadeSendoVisualizada(boolean atividadeSendoVisualizada) {
        this.atividadeSendoVisualizada = atividadeSendoVisualizada;
    }
    
    
}
