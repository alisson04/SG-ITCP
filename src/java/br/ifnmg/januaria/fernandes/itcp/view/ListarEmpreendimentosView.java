package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.dao.EmpreendimentoDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author alisson
 */
@ManagedBean(name="ListarEmpreendimentosView")
@ViewScoped
public class ListarEmpreendimentosView implements Serializable{
    private Empreendimento empreendimentoSelecionado;
    private List<Empreendimento> listaEmpreendimentos; //
    private List<Empreendimento> listaEmpreendimentosFiltrados;
    private String[] processoIncubacao;//para a tela de listar usuarios
    private EmpreendimentoBean bean;
    
    public ListarEmpreendimentosView() {
        processoIncubacao = new String[5];
        processoIncubacao[0] = "Não incubado";
        processoIncubacao[1] = "Pré-incubação";
        processoIncubacao[2] = "Incubação";
        processoIncubacao[3] = "Desincubação";
        processoIncubacao[4] = "Desincubado";
        
        bean = new EmpreendimentoBean();
    }
    
    public void listarTodosEmpreendimentos() {
        System.out.println("BEAN(ListarEmpreendimentosView): listarTodosEmpreendimentos: ");
        try {
            listaEmpreendimentos = bean.listarTodosEptsBean();
        } catch (RuntimeException ex) {
            //FacesUtil.adicionarMsgErro("Erro ao carregar pesquisa:" + ex.getMessage());
            System.out.println("BEAN(ListarEmpreendimentosView): Erro ao Carregar lista de Empreendimentos: " + ex);
        } 
    }
    
    public void excluirEptView(){
        bean.excluirEptBean(empreendimentoSelecionado);
        empreendimentoSelecionado = null;//Volta o usuario para o estado de nulo/ Não retire
        FacesMessage msg = new FacesMessage("Empreendimento excluido do sistema");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onRowSelect(SelectEvent event) {
        System.out.println("BEAN(ListarEmpreendimentosView): onRowSelect: ");
        FacesMessage msg = new FacesMessage("Empreendimento " + empreendimentoSelecionado.getNomeFantasiaEpt()+ " selecionado!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public String conveteData(Date data) {
        if (data != null) {
            SimpleDateFormat forma = new SimpleDateFormat("dd/MM/yyyy");
            return forma.format(data);
        } else {
            return "";
        }
    }
    
    //SETS GETS
    public Empreendimento getEmpreendimentoSelecionado() {
        return empreendimentoSelecionado;
    }

    public void setEmpreendimentoSelecionado(Empreendimento empreendimentoSelecionado) {
        this.empreendimentoSelecionado = empreendimentoSelecionado;
    }

    public List<Empreendimento> getListaEmpreendimentos() {
        return listaEmpreendimentos;
    }

    public void setListaEmpreendimentos(List<Empreendimento> listaEmpreendimentos) {
        this.listaEmpreendimentos = listaEmpreendimentos;
    }

    public List<Empreendimento> getListaEmpreendimentosFiltrados() {
        return listaEmpreendimentosFiltrados;
    }

    public void setListaEmpreendimentosFiltrados(List<Empreendimento> listaEmpreendimentosFiltrados) {
        this.listaEmpreendimentosFiltrados = listaEmpreendimentosFiltrados;
    }
    
    public String[] getProcessoIncubacao() {
        return processoIncubacao;
    }

    public void setProcessoIncubacao(String[] processoIncubacao) {
        this.processoIncubacao = processoIncubacao;
    }
}
