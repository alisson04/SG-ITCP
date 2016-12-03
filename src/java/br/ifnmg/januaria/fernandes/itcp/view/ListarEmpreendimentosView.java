package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "ListarEmpreendimentosView")
@ViewScoped
public class ListarEmpreendimentosView extends MensagensGenericas implements Serializable {

    private Empreendimento empreendimentoSelecionado;
    private Empreendimento objSalvar;
    private List<Empreendimento> listaEmpreendimentos;
    private List<Empreendimento> listaEmpreendimentosFiltrados;
    private String[] processoIncubacao;//para a tela de listar usuarios
    private EmpreendimentoBean bean;
    private String[] tiposEpt;//tipos de empreendimentos
    private String[] situacaoEpt;//Situação do empreendimentos

    //Construtor
    public ListarEmpreendimentosView() {

        objSalvar = new Empreendimento();
        bean = new EmpreendimentoBean();
        listaEmpreendimentos = bean.listarBean();

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
    
    //METODOS
    public String geraMsgGenericaCampoObrigatorioView(){
        return msgGenericaCampoObrigatorio();
    }

    public void transfereObj() {//Para botão de editar
        objSalvar = empreendimentoSelecionado;
    }

    public void reiniciaObj() {//Para botão de editar
        objSalvar = new Empreendimento();
    }

    public void salvarView() {
        try {
            bean.salvarBean(objSalvar);
            objSalvar = new Empreendimento();//Volta o usuario para o estado inicial/ Não retire
            listaEmpreendimentos = bean.listarBean();//Atualiza a lista de empreendimentos
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('wVarEditarDialog').hide()");
            msgGrowSaveGeneric();
        } catch (Exception ex) {
            Logger.getLogger(ListarPlanoAcaoView.class.getName()).log(Level.SEVERE, null, ex);
            msgPanelErroInesperadoGeneric();
        }
    }

    public void excluirView() {
        try {
            bean.excluirBean(empreendimentoSelecionado);
            empreendimentoSelecionado = null;//Volta o usuario para o estado inicial/ Não retire
            listaEmpreendimentos = bean.listarBean();//Atualiza a lista de empreendimentos
            msgGrowDeleteGeneric();
        } catch (Exception ex) {
            System.out.println("Erro ao excluir: " + ex);
            msgPanelErroInesperadoGeneric();
        }
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
