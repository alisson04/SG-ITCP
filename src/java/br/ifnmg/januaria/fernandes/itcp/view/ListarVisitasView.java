package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.ParceiroBean;
import br.ifnmg.januaria.fernandes.itcp.bean.UsuarioBean;
import br.ifnmg.januaria.fernandes.itcp.bean.VisitaEptBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.Parceiro;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.domain.VisitaEpt;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "ListarVisitasView")
@ViewScoped
public class ListarVisitasView extends MensagensGenericas implements Serializable {

    private VisitaEpt objSelecionado;
    private VisitaEpt objSalvar;
    private List<VisitaEpt> listaVisitasEpt;
    private List<VisitaEpt> listaParceirosFiltrados;
    private VisitaEptBean bean;
    private EmpreendimentoBean empreendimentoBean;
    private List<Empreendimento> listaEmpreendimentos;

    TabView tabview = new TabView();

    //PickList Usuario
    private UsuarioBean usuarioBean;
    private List<Usuario> usuarios;
    private List<Usuario> usuariosSelecionados;
    private DualListModel<Usuario> usuariosPickList;

    //PickList Parceiro
    private ParceiroBean parceiroBean;
    private List<Parceiro> parceiros;
    private List<Parceiro> parceirosSelecionados;
    private DualListModel<Parceiro> parceirosPickList;

    public ListarVisitasView() {
        try {
            objSalvar = new VisitaEpt();
            bean = new VisitaEptBean();
            empreendimentoBean = new EmpreendimentoBean();

            usuarioBean = new UsuarioBean();
            usuarios = usuarioBean.listarBean();
            usuariosSelecionados = new ArrayList<Usuario>();
            usuariosPickList = new DualListModel<Usuario>(usuarios, usuariosSelecionados);

            parceiroBean = new ParceiroBean();
            parceiros = parceiroBean.listarBean();
            parceirosSelecionados = new ArrayList<Parceiro>();
            parceirosPickList = new DualListModel<Parceiro>(parceiros, parceirosSelecionados);

            listaEmpreendimentos = empreendimentoBean.listarBean();
            listaVisitasEpt = bean.listarBean();
        } catch (RuntimeException ex) {
            msgPanelErroInesperadoGeneric();
        }
    }

    public void transfereObj() {//Para botão de editar
        tabview.setActiveIndex(0);
        objSalvar = objSelecionado;
        usuarios = usuarioBean.listarBean();
        parceiros = parceiroBean.listarBean();

        usuariosPickList.setSource(usuarios);
        parceirosPickList.setSource(parceiros);

        //PARCEIRO PICK LIST
        if (objSalvar.getParceiroList().size() > 0) {
            parceirosPickList.setTarget(objSalvar.getParceiroList());
            parceiros.removeAll(objSalvar.getParceiroList());
        } else {
            parceirosSelecionados = new ArrayList<Parceiro>();
            parceirosPickList.setTarget(parceirosSelecionados);
        }

        //USUARIO PICK LIST
        if (objSalvar.getUsuarioList().size() > 0) {
            usuariosPickList.setTarget(objSalvar.getUsuarioList());
            usuarios.removeAll(objSalvar.getUsuarioList());
        } else {
            usuariosSelecionados = new ArrayList<Usuario>();
            usuariosPickList.setTarget(usuariosSelecionados);
        }
    }

    public void reiniciaObj() {//Para botão de cadastrar
        System.out.println("objSalvar Reiniciado ====================== ");
        usuarios = usuarioBean.listarBean();
        parceiros = parceiroBean.listarBean();

        usuariosPickList.setSource(usuarios);
        parceirosPickList.setSource(parceiros);

        usuariosSelecionados = new ArrayList<Usuario>();
        usuariosPickList.setTarget(usuariosSelecionados);

        parceirosSelecionados = new ArrayList<Parceiro>();
        parceirosPickList.setTarget(parceirosSelecionados);

        objSalvar = new VisitaEpt();
        tabview.setActiveIndex(0);
    }

    public void salvarView() {
        try {
            objSalvar.setUsuarioList(usuariosPickList.getTarget());
            objSalvar.setParceiroList(parceirosPickList.getTarget());
            objSalvar = bean.salvarBean(objSalvar);

            usuarios = usuarioBean.listarBean();
            parceiros = parceiroBean.listarBean();
            usuariosSelecionados = new ArrayList<Usuario>();
            parceirosSelecionados = new ArrayList<Parceiro>();
            usuariosPickList = new DualListModel<Usuario>(usuarios, usuariosSelecionados);
            parceirosPickList = new DualListModel<Parceiro>(parceiros, parceirosSelecionados);

            objSalvar = new VisitaEpt();
            listaVisitasEpt = bean.listarBean();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('wVarEditarDialog').hide()");
            msgGrowSaveGeneric();
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

    public void onTabChange(TabChangeEvent event) {//Esse método é necessário para que o "tabView" do bean esteja atualizado com o que esta na tela
        System.out.println("Usuário esta na tab: " + event.getTab().getId());
        if (event.getTab().getId().equals("tabInfo")) {
            tabview.setActiveIndex(0);
        } else if (event.getTab().getId().equals("tabUsr")) {
            tabview.setActiveIndex(1);
        } else {
            tabview.setActiveIndex(2);
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

    public DualListModel<Usuario> getUsuariosPickList() {
        return usuariosPickList;
    }

    public void setUsuariosPickList(DualListModel<Usuario> usuariosPickList) {
        this.usuariosPickList = usuariosPickList;
    }

    public DualListModel<Parceiro> getParceirosPickList() {
        return parceirosPickList;
    }

    public void setParceirosPickList(DualListModel<Parceiro> parceirosPickList) {
        this.parceirosPickList = parceirosPickList;
    }

    public TabView getTabview() {
        return tabview;
    }

    public void setTabview(TabView tabview) {
        this.tabview = tabview;
    }
}
