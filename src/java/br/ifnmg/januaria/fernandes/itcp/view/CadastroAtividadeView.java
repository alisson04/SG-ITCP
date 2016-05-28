package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.AtividadeExecutadaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MensagensBean;
import br.ifnmg.januaria.fernandes.itcp.dao.AtividadeExecutadaDAO;
import br.ifnmg.januaria.fernandes.itcp.dao.EmpreendimentoDAO;
import br.ifnmg.januaria.fernandes.itcp.dao.UsuarioDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadeExecutada;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import java.io.Serializable;
import java.util.List;
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
    private AtividadeExecutada atividade = new AtividadeExecutada();
    private boolean atividadeSendoVisualizada;
    private AtividadeExecutadaBean bean = new AtividadeExecutadaBean();
    private AtividadeExecutadaDAO dao = new AtividadeExecutadaDAO();
    
    private Empreendimento empreendimento = new Empreendimento();
    private String[]  nomeEmpreendimentos;
    private List<Empreendimento> listaEmpreendimentos;
    private List<String> listaEmpreendimentosSelecionados;
    private EmpreendimentoDAO daoEpt = new EmpreendimentoDAO();
    
    private MensagensBean mensagensBean = new MensagensBean();
    
    
    private boolean skip;
    private String[] statusAtividade;//
    
    private List<Usuario> listaUsuarios;
    private String[]  nomeUsuarios;
    private UsuarioDAO daoUsu = new UsuarioDAO();

    public CadastroAtividadeView() {
        listaEmpreendimentos = daoEpt.listarTodosEmpreendimentos();
        nomeEmpreendimentos = new String[listaEmpreendimentos.size()];
        for (int i = 0; i < listaEmpreendimentos.size(); i++) {
            nomeEmpreendimentos[i] = listaEmpreendimentos.get(i).getNomeEpt();
        }
        
        listaUsuarios = daoUsu.listarTodosUsuarios();
        nomeUsuarios = new String[listaUsuarios.size()];
        for (int i = 0; i < listaUsuarios.size(); i++) {
            nomeUsuarios[i] = listaUsuarios.get(i).getNomeUsuario();
        }
                
        statusAtividade = new String[3];
        statusAtividade[0] = "Não iniciada";
        statusAtividade[1] = "PréIniciada";
        statusAtividade[2] = "Finalizada";
    }
    
    //Métodos
    public void salvarAtividade() {
        FacesMessage msg = new FacesMessage("Successful", "Welcome :");
        FacesContext.getCurrentInstance().addMessage(null, msg);   
        
        
        bean.salvarAtividadeBd(atividade);
        atividadeSendoVisualizada = true;
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
    
    public AtividadeExecutada getAtividade() {
        return atividade;
    }

    public void setAtividade(AtividadeExecutada atividade) {
        this.atividade = atividade;
    }

    public boolean isAtividadeSendoVisualizada() {
        return atividadeSendoVisualizada;
    }

    public void setAtividadeSendoVisualizada(boolean atividadeSendoVisualizada) {
        this.atividadeSendoVisualizada = atividadeSendoVisualizada;
    }

    public String[] getStatusAtividade() {
        return statusAtividade;
    }

    public void setStatusAtividade(String[] statusAtividade) {
        this.statusAtividade = statusAtividade;
    }

    public Empreendimento getEmpreendimento() {
        return empreendimento;
    }

    public void setEmpreendimento(Empreendimento empreendimento) {
        this.empreendimento = empreendimento;
    }

    public String[] getNomeEmpreendimentos() {
        return nomeEmpreendimentos;
    }

    public void setNomeEmpreendimentos(String[] nomeEmpreendimentos) {
        this.nomeEmpreendimentos = nomeEmpreendimentos;
    }

    public List<Empreendimento> getListaEmpreendimentos() {
        return listaEmpreendimentos;
    }

    public void setListaEmpreendimentos(List<Empreendimento> listaEmpreendimentos) {
        this.listaEmpreendimentos = listaEmpreendimentos;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String[] getNomeUsuarios() {
        return nomeUsuarios;
    }

    public void setNomeUsuarios(String[] nomeUsuarios) {
        this.nomeUsuarios = nomeUsuarios;
    }

    public List<String> getListaEmpreendimentosSelecionados() {
        return listaEmpreendimentosSelecionados;
    }

    public void setListaEmpreendimentosSelecionados(List<String> listaEmpreendimentosSelecionados) {
        this.listaEmpreendimentosSelecionados = listaEmpreendimentosSelecionados;
    }
    
    
}
