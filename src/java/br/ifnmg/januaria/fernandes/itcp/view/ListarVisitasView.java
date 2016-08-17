package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.VisitaEptBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.VisitaEpt;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import java.io.Serializable;
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
@ManagedBean(name = "ListarVisitasView")
@ViewScoped
public class ListarVisitasView extends MensagensGenericas implements Serializable {

    private VisitaEpt objSelecionado;
    private VisitaEpt objSalvar = new VisitaEpt();
    private List<VisitaEpt> listaVisitasEpt;
    private List<VisitaEpt> listaParceirosFiltrados;
    private VisitaEptBean bean = new VisitaEptBean();
    private EmpreendimentoBean empreendimentoBean = new EmpreendimentoBean();
    private List<Empreendimento> listaEmpreendimentos;

    public ListarVisitasView() {
        try {
            listaEmpreendimentos = empreendimentoBean.listarBean();
            listaVisitasEpt = bean.listarBean();
        } catch (RuntimeException ex) {
            //FacesUtil.adicionarMsgErro("Erro ao carregar pesquisa:" + ex.getMessage());
            System.out.println("VIEW(listarTodosParceiros): Erro ao Carregar lista de Parceiros: " + ex);
        }
    }

    public void transfereObj() {//Para botão de editar
        objSalvar = objSelecionado;
    }

    public void reiniciaObj() {//Para botão de cadastrar
        System.out.println("objSalvar Reiniciado ====================== ");
        objSalvar = new VisitaEpt();
    }

    public void salvarView() {
        try {
            bean.salvarBean(objSalvar);
            objSalvar = new VisitaEpt();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('wVarEditarDialog').hide()");
            context.execute("PF('dlgEdicaoPronta').show()");
        } catch (Exception ex) {
            Logger.getLogger(ListarPlanoAcaoView.class.getName()).log(Level.SEVERE, null, ex);
            msgPanelErroInesperadoGeneric();
        }
    }

    public void excluirParceiroView() {
        try {
            bean.excluirBean(objSelecionado);
            objSelecionado = null;//Volta o usuario para o estado de nulo/ Não retire
            msgGrowDeleteGeneric();
        } catch (Exception ex) {
            Logger.getLogger(ListarPlanoAcaoView.class.getName()).log(Level.SEVERE, null, ex);
            msgPanelErroInesperadoGeneric();
        }
    }

    //SETS E GETS
    public VisitaEpt getObjSelecionado() {
        return objSelecionado;
    }

    public void setObjSelecionado(VisitaEpt objSelecionado) {
        this.objSelecionado = objSelecionado;
    }

    public VisitaEpt getObjSalvar() {
        return objSalvar;
    }

    public void setObjSalvar(VisitaEpt objSalvar) {
        this.objSalvar = objSalvar;
    }

    public List<VisitaEpt> getListaVisitasEpt() {
        return listaVisitasEpt;
    }

    public void setListaVisitasEpt(List<VisitaEpt> listaVisitasEpt) {
        this.listaVisitasEpt = listaVisitasEpt;
    }

    public List<VisitaEpt> getListaParceirosFiltrados() {
        return listaParceirosFiltrados;
    }

    public void setListaParceirosFiltrados(List<VisitaEpt> listaParceirosFiltrados) {
        this.listaParceirosFiltrados = listaParceirosFiltrados;
    }

    public List<Empreendimento> getListaEmpreendimentos() {
        return listaEmpreendimentos;
    }

    public void setListaEmpreendimentos(List<Empreendimento> listaEmpreendimentos) {
        this.listaEmpreendimentos = listaEmpreendimentos;
    }
}
