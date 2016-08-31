package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.ParceiroBean;
import br.ifnmg.januaria.fernandes.itcp.bean.UsuarioBean;
import br.ifnmg.januaria.fernandes.itcp.bean.AcompanhamentoEptBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.Parceiro;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.domain.AcompanhamentoEpt;
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
@ManagedBean(name = "ListarAcompanhamentosView")
@ViewScoped
public class ListarAcompanhamentosView extends MensagensGenericas implements Serializable {

    private AcompanhamentoEpt objSelecionado;
    private AcompanhamentoEpt objSalvar;
    private List<AcompanhamentoEpt> listaAcompanhamentosEpt;
    private List<AcompanhamentoEpt> listaAcompanhamentosFiltrados;
    private AcompanhamentoEptBean bean;
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

    int i = 0;

    public ListarAcompanhamentosView() {
        try {
            objSalvar = new AcompanhamentoEpt();
            bean = new AcompanhamentoEptBean();
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
            listaAcompanhamentosEpt = bean.listarBean();

        } catch (RuntimeException ex) {
            msgPanelErroInesperadoGeneric();
            System.out.println("Erro Inesperado no construtor: " + ex);
        }
    }

    public void transfereObj() {//Para botão de editar
        try {
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
        } catch (Exception ex) {
            System.out.println("ERRO AO TRANSFERIR OBJ: " + ex);
        }
    }

    public void reiniciaObj() {//Para botão de cadastrar
        tabview.setActiveIndex(0);
        System.out.println("objSalvar Reiniciado ====================== ");
        usuarios = usuarioBean.listarBean();
        parceiros = parceiroBean.listarBean();

        usuariosPickList.setSource(usuarios);
        parceirosPickList.setSource(parceiros);

        usuariosSelecionados = new ArrayList<Usuario>();
        usuariosPickList.setTarget(usuariosSelecionados);

        parceirosSelecionados = new ArrayList<Parceiro>();
        parceirosPickList.setTarget(parceirosSelecionados);

        objSalvar = new AcompanhamentoEpt();
    }

    public void salvarView() {
        try {
            System.out.println("SALVAR");
            objSalvar.setUsuarioList(usuariosPickList.getTarget());
            objSalvar.setParceiroList(parceirosPickList.getTarget());
            objSalvar = bean.salvarBean(objSalvar);

            //Limpa valores das PickLists
            usuarios = usuarioBean.listarBean();
            parceiros = parceiroBean.listarBean();
            usuariosSelecionados = new ArrayList<Usuario>();
            parceirosSelecionados = new ArrayList<Parceiro>();
            usuariosPickList = new DualListModel<Usuario>(usuarios, usuariosSelecionados);
            parceirosPickList = new DualListModel<Parceiro>(parceiros, parceirosSelecionados);

            objSalvar = new AcompanhamentoEpt();
            listaAcompanhamentosEpt = bean.listarBean();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('wVarEditarDialog').hide()");
            msgGrowSaveGeneric();
        } catch (Exception ex) {
            Logger.getLogger(ListarPlanoAcaoView.class.getName()).log(Level.SEVERE, null, ex);
            msgPanelErroInesperadoGeneric();
        }
    }
    
    public void teste(){
        System.out.println("_____TESTE: Usuário esta na tab: " + tabview.getActiveIndex());
        tabview.setActiveIndex(0);
    }
    
    public void testeOnclick(){
        System.out.println("_____TESTE ON CLICK: Usuário esta na tab: " + tabview.getActiveIndex());
        tabview.setActiveIndex(0);
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
            System.out.println("Usuário esta na tab: " + tabview.getActiveIndex());
        } else if (event.getTab().getId().equals("tabUsr")) {
            tabview.setActiveIndex(1);
            System.out.println("Usuário esta na tab: " + tabview.getActiveIndex());
        } else {
            tabview.setActiveIndex(2);
            System.out.println("Usuário esta na tab: " + tabview.getActiveIndex());
        }
    }

    //SETS E GETS
    public AcompanhamentoEpt getObjSelecionado() {
        return objSelecionado;
    }

    public void setObjSelecionado(AcompanhamentoEpt objSelecionado) {
        this.objSelecionado = objSelecionado;
    }

    public AcompanhamentoEpt getObjSalvar() {
        return objSalvar;
    }

    public void setObjSalvar(AcompanhamentoEpt objSalvar) {
        this.objSalvar = objSalvar;
    }

    public List<AcompanhamentoEpt> getListaAcompanhamentosEpt() {
        return listaAcompanhamentosEpt;
    }

    public void setListaAcompanhamentosEpt(List<AcompanhamentoEpt> listaAcompanhamentosEpt) {
        this.listaAcompanhamentosEpt = listaAcompanhamentosEpt;
    }

    public List<AcompanhamentoEpt> getListaAcompanhamentosFiltrados() {
        return listaAcompanhamentosFiltrados;
    }

    public void setListaAcompanhamentosFiltrados(List<AcompanhamentoEpt> listaAcompanhamentosFiltrados) {
        this.listaAcompanhamentosFiltrados = listaAcompanhamentosFiltrados;
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
