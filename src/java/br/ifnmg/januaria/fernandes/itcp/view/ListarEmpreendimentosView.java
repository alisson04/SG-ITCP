package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "ListarEmpreendimentosView")
@ViewScoped
public class ListarEmpreendimentosView implements Serializable {

    private Empreendimento empreendimentoSelecionado;
    private Empreendimento objSalvar = new Empreendimento();
    private List<Empreendimento> listaEmpreendimentos; //
    private List<Empreendimento> listaEmpreendimentosFiltrados;
    private String[] processoIncubacao;//para a tela de listar usuarios
    private EmpreendimentoBean bean = new EmpreendimentoBean();
    private String[] tiposEpt;//tipos de empreendimentos
    private String[] situacaoEpt;//Situação do empreendimentos

    public ListarEmpreendimentosView() {
        processoIncubacao = new String[5];
        processoIncubacao[0] = "Não incubado";
        processoIncubacao[1] = "Pré-incubação";
        processoIncubacao[2] = "Incubação";
        processoIncubacao[3] = "Desincubação";
        processoIncubacao[4] = "Desincubado";

        tiposEpt = new String[3];
        tiposEpt[0] = "Associação";
        tiposEpt[1] = "Cooperativa";
        tiposEpt[2] = "Grupo não formalizado";

        situacaoEpt = new String[5];
        situacaoEpt[0] = "Não incubado";
        situacaoEpt[1] = "Pré-incubação";
        situacaoEpt[2] = "Incubação";
        situacaoEpt[3] = "Desincubação";
        situacaoEpt[4] = "Desincubado";
    }
    
    public void transfereObj(){//Para botão de editar
        objSalvar = empreendimentoSelecionado;
    }

    public void reiniciaObj(){//Para botão de editar
        objSalvar = new Empreendimento();
    }
    
    public void listarTodosEmpreendimentos() {
        System.out.println("BEAN(ListarEmpreendimentosView): listarTodosEmpreendimentos: ");
        try {
            listaEmpreendimentos = bean.listarBean();
        } catch (RuntimeException ex) {
            System.out.println("RuntimeException: " + ex);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Erro inesperado", "Erro ao tentar listar os empreendimentos, contate o administrador do sistema!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void salvarView() {
        try {
            bean.salvarBean(objSalvar);
            objSalvar = new Empreendimento();//Volta o usuario para o estado inicial/ Não retire
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('wVarEditarDialog').hide()");
            context.execute("PF('dlgEdicaoPronta').show()");
        } catch (Exception ex) {
            Logger.getLogger(ListarPlanoAcaoView.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Erro inesperado", "Erro ao tentar editar o empreendimento, contate o administrador do sistema!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void excluirView() {
        bean.excluirBean(empreendimentoSelecionado);
        empreendimentoSelecionado = null;//Volta o usuario para o estado inicial/ Não retire
        FacesMessage msg = new FacesMessage("Empreendimento excluido do sistema");
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

    public String[] getTiposEpt() {
        return tiposEpt;
    }

    public void setTiposEpt(String[] tiposEpt) {
        this.tiposEpt = tiposEpt;
    }

    public String[] getSituacaoEpt() {
        return situacaoEpt;
    }

    public void setSituacaoEpt(String[] situacaoEpt) {
        this.situacaoEpt = situacaoEpt;
    }

    public Empreendimento getObjSalvar() {
        return objSalvar;
    }

    public void setObjSalvar(Empreendimento objSalvar) {
        this.objSalvar = objSalvar;
    }
}
