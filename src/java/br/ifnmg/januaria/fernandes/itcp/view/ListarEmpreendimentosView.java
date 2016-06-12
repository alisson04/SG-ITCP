package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.dao.EmpreendimentoDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import java.io.Serializable;
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
    private boolean btnEdicoesEpt;
    private List<Empreendimento> listaEmpreendimentos; //
    private List<Empreendimento> listaEmpreendimentosFiltrados;
    private String[] processoIncubacao;//para a tela de listar usuarios
    
    public ListarEmpreendimentosView() {
        btnEdicoesEpt = true; //recebe true para desabillitar o botão da tela
        processoIncubacao = new String[5];
        processoIncubacao[0] = "Não incubado";
        processoIncubacao[1] = "Pré-incubação";
        processoIncubacao[2] = "Incubação";
        processoIncubacao[3] = "Desincubação";
        processoIncubacao[4] = "Desincubado";
    }
    
    public void listarTodosEmpreendimentos() {
        System.out.println("BEAN(ListarEmpreendimentosView): listarTodosEmpreendimentos: ");
        try {
            EmpreendimentoDAO empreendimentoDAO = new EmpreendimentoDAO();
            listaEmpreendimentos = empreendimentoDAO.listarTodosEmpreendimentos();
        } catch (RuntimeException ex) {
            //FacesUtil.adicionarMsgErro("Erro ao carregar pesquisa:" + ex.getMessage());
            System.out.println("BEAN(ListarEmpreendimentosView): Erro ao Carregar lista de Empreendimentos: " + ex);
        }
    }
    
    public void onRowSelect(SelectEvent event) {
        System.out.println("BEAN(ListarEmpreendimentosView): onRowSelect: ");
        btnEdicoesEpt = false;
        FacesMessage msg = new FacesMessage("Empreendimento " + empreendimentoSelecionado.getNomeFantasiaEpt()+ " selecionado!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
    
    //SETS GETS
    public Empreendimento getEmpreendimentoSelecionado() {
        return empreendimentoSelecionado;
    }

    public void setEmpreendimentoSelecionado(Empreendimento empreendimentoSelecionado) {
        this.empreendimentoSelecionado = empreendimentoSelecionado;
    }

    public boolean isBtnEdicoesEpt() {
        return btnEdicoesEpt;
    }

    public void setBtnEdicoesEpt(boolean btnEdicoesEpt) {
        this.btnEdicoesEpt = btnEdicoesEpt;
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
