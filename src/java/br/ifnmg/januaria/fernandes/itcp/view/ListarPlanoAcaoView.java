package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.PlanoAcaoBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.PlanoAcao;
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

/**
 *
 * @author alisson
 */
@ManagedBean(name = "ListarPlanoAcaoView")
@ViewScoped
public class ListarPlanoAcaoView implements Serializable {

    PlanoAcaoBean bean = new PlanoAcaoBean();
    EmpreendimentoBean empreendimentoBean = new EmpreendimentoBean();
    private PlanoAcao planoSelecionado;
    private PlanoAcao objSalvar = new PlanoAcao();
    private List<PlanoAcao> listaPlanoAcao;
    private List<PlanoAcao> listaPlanoAcaoFiltrados;
    private List<Empreendimento> listaEmpreendimentos;

    public ListarPlanoAcaoView() {
    }

    public void ListarPlanosAcao() {
        try {
            listaEmpreendimentos = empreendimentoBean.listarBean();
            listaPlanoAcao = bean.listarBean();
        } catch (RuntimeException ex) {
            System.out.println("BEAN(ListarEmpreendimentosView): Erro ao Carregar lista de Planos: " + ex);
        }
    }
    
    public void transfereObj(){//Para botão de editar
        objSalvar = planoSelecionado;
    }

    public void reiniciaObj(){//Para botão de cadastrar
        System.out.println("objSalvar Reiniciado ====================== ");
        objSalvar = new PlanoAcao();
    }

    public void salvarView() {
        try {
            bean.salvarBean(objSalvar);
            objSalvar = new PlanoAcao();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('wVarDlgEditar').hide()");
            context.execute("PF('dlgEdicaoPronta').show()");
        } catch (Exception ex) {
            Logger.getLogger(ListarPlanoAcaoView.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro de exceção:", ex.getMessage()));
        }
    }

    public void excluirView() {
        try {
            bean.excluirBean(planoSelecionado);
            planoSelecionado = null;//Volta o usuario para o estado de nulo/ Não retire
            FacesMessage msg = new FacesMessage("Plano de ação excluido com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception ex) {
            Logger.getLogger(ListarPlanoAcaoView.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro de exceção:", ex.getMessage()));
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

    //SETS E GETS
    public PlanoAcao getPlanoSelecionado() {
        return planoSelecionado;
    }

    public void setPlanoSelecionado(PlanoAcao planoSelecionado) {
        this.planoSelecionado = planoSelecionado;
    }

    public List<PlanoAcao> getListaPlanoAcao() {
        return listaPlanoAcao;
    }

    public void setListaPlanoAcao(List<PlanoAcao> listaPlanoAcao) {
        this.listaPlanoAcao = listaPlanoAcao;
    }

    public List<PlanoAcao> getListaPlanoAcaoFiltrados() {
        return listaPlanoAcaoFiltrados;
    }

    public void setListaPlanoAcaoFiltrados(List<PlanoAcao> listaPlanoAcaoFiltrados) {
        this.listaPlanoAcaoFiltrados = listaPlanoAcaoFiltrados;
    }

    public List<Empreendimento> getListaEmpreendimentos() {
        return listaEmpreendimentos;
    }

    public void setListaEmpreendimentos(List<Empreendimento> listaEmpreendimentos) {
        this.listaEmpreendimentos = listaEmpreendimentos;
    }

    public PlanoAcao getObjSalvar() {
        return objSalvar;
    }

    public void setObjSalvar(PlanoAcao objSalvar) {
        this.objSalvar = objSalvar;
    }
}
