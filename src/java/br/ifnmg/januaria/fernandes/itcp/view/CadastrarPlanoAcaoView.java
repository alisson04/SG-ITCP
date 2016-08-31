package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.AtividadePlanejadaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MetaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.ParceiroBean;
import br.ifnmg.januaria.fernandes.itcp.bean.PlanoAcaoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.UsuarioBean;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.Meta;
import br.ifnmg.januaria.fernandes.itcp.domain.Parceiro;
import br.ifnmg.januaria.fernandes.itcp.domain.PlanoAcao;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@ManagedBean(name = "CadastrarPlanoAcaoView")
@ViewScoped
public class CadastrarPlanoAcaoView extends MensagensGenericas implements Serializable {

    PlanoAcaoBean bean;
    EmpreendimentoBean empreendimentoBean;
    MetaBean metaBean;
    AtividadePlanejadaBean atividadeBean;

    private PlanoAcao objSalvar;
    private Meta metaSalvar;
    private Meta metaSelecionada;

    private AtividadePlanejada atividadeSalvar;
    private AtividadePlanejada atividadeSelecionada;

    private List<Empreendimento> listaEmpreendimentos;
    private List<AtividadePlanejada> listaSalvarAtividades;
    private List<Meta> listaSalvarMetas;

    TabView tabview = new TabView();
    TabView tabviewAtividades = new TabView();

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

    //Construtor
    public CadastrarPlanoAcaoView() {
        try {
            bean = new PlanoAcaoBean();
            empreendimentoBean = new EmpreendimentoBean();
            metaBean = new MetaBean();
            atividadeBean = new AtividadePlanejadaBean();
            objSalvar = new PlanoAcao();
            metaSalvar = new Meta();
            atividadeSalvar = new AtividadePlanejada();
            atividadeSelecionada = new AtividadePlanejada();
            listaSalvarAtividades = new ArrayList();
            listaSalvarMetas = new ArrayList();
            listaEmpreendimentos = empreendimentoBean.listarBean();

            //PickList Usuario
            usuarioBean = new UsuarioBean();
            usuarios = usuarioBean.listarBean();
            usuariosSelecionados = new ArrayList<Usuario>();
            usuariosPickList = new DualListModel<Usuario>(usuarios, usuariosSelecionados);

            //PickList Parceiro
            parceiroBean = new ParceiroBean();
            parceiros = parceiroBean.listarBean();
            parceirosSelecionados = new ArrayList<Parceiro>();
            parceirosPickList = new DualListModel<Parceiro>(parceiros, parceirosSelecionados);
        } catch (RuntimeException ex) {
            System.out.println("BEAN(ListarEmpreendimentosView): Erro ao Carregar lista de Planos: " + ex);
            msgPanelErroInesperadoGeneric();
        }
    }

    public void excluirMeta() {
        System.out.println("Número de atividades na lista: " + metaSelecionada.getAtividadePlanejadaList().size());
        metaBean.excluirBean(metaSelecionada);//Exclui a meta do BD
        listaSalvarMetas.remove(metaSelecionada);//Exclui a meta da Tela

        System.out.println("A atividades na meta");
        for (int i = 0; i < listaSalvarAtividades.size(); i++) {
            System.out.println("RODOU: " + i);
            if (listaSalvarAtividades.get(i).getMeta().equals(metaSelecionada)) {
                System.out.println("Apagou a atividade: " + listaSalvarAtividades.get(i).getNome());
                listaSalvarAtividades.remove(i);
            }
        }

        metaSelecionada = null;
        msgGrowDeleteGeneric();
    }

    public void excluirAtividade() {
        atividadeBean.excluirBean(atividadeSelecionada);
        listaSalvarAtividades.remove(atividadeSelecionada);
        atividadeSelecionada = null;
        msgGrowDeleteGeneric();
    }

    public void salvarAtividade() {
        Date inicioAtividade = atividadeSalvar.getDataInicio();
        Date fimAtividade = atividadeSalvar.getDataFim();
        Date inicioPlano = objSalvar.getDataInicio();

        if (fimAtividade.before(inicioAtividade)) { //SE o FIM antes deINICIO
            msgPanelErro("Erro na data", "A 'data de início' da atividade deve ser igual ou maior a sua data de fim!");
        } else if (inicioAtividade.before(inicioPlano)) {//SE INICIO ATIVIDADE antes INICIO PLANO
            msgPanelErro("Erro na data", "A 'data de início' da atividade deve ser maior ou igual a 'data de iníco' do plano!");
        } else if (fimAtividade.after(atividadeSalvar.getMeta().getPrazo())) {//SE FIM ATIVIDADE depois PRAZO META
            msgPanelErro("Erro na data", "A 'data de fim' da atividade deve ser antes ou igual ao prazo de sua meta!");
        } else {
            System.out.println("ADD O OBJ: " + atividadeSalvar.getId());
            if (listaSalvarAtividades.contains(atividadeSalvar)) {//ESTA EDITANDO
                listaSalvarAtividades.remove(atividadeSalvar);
            }

            atividadeSalvar.setUsuarioList(usuariosPickList.getTarget());
            atividadeSalvar.setParceiroList(parceirosPickList.getTarget());
            atividadeSalvar = atividadeBean.salvarBean(atividadeSalvar);
            listaSalvarAtividades.add(atividadeSalvar);

            atividadeSalvar = new AtividadePlanejada();//Limpa o obj que foi salvo

            //Limpa valores das PickLists
            usuarios = usuarioBean.listarBean();
            parceiros = parceiroBean.listarBean();
            usuariosSelecionados = new ArrayList<Usuario>();
            parceirosSelecionados = new ArrayList<Parceiro>();
            usuariosPickList = new DualListModel<Usuario>(usuarios, usuariosSelecionados);
            parceirosPickList = new DualListModel<Parceiro>(parceiros, parceirosSelecionados);

            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('wVarEditarAtividadeDialog').hide()");
            msgGrowSaveGeneric();
        }
    }

    public void salvarMeta() {
        if (metaSalvar.getPrazo().before(objSalvar.getDataInicio())) {
            msgPanelErro("Erro na data", "O prazo da meta deve ser maior ou igual a 'data de início' do plano de ação!");
        } else if (metaSalvar.getPrazo().after(objSalvar.getDataFim())) {
            msgPanelErro("Erro na data", "O prazo da meta deve ser menor ou igual a 'data de fim' do plano de ação'!");
        } else {
            System.out.println("ADD O OBJ: " + metaSalvar.getIdMeta());
            if (listaSalvarMetas.contains(metaSalvar)) {//ESTA EDITANDO
                listaSalvarMetas.remove(metaSalvar);
            } else {//ESTA CADASTRANDO
                metaSalvar.setPlanoAcao(objSalvar);
            }
            metaSalvar = metaBean.salvarBean(metaSalvar);
            listaSalvarMetas.add(metaSalvar);
            metaSalvar = new Meta();
            System.out.println("Tamanho das metas: " + listaSalvarMetas.size());
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('wVarEditarDialog').hide()");
            msgGrowSaveGeneric();
        }
    }

    public void salvarView() {
        try {
            if (objSalvar.getDataInicio().before(objSalvar.getDataFim())) {// SE A DATA DE INÍCIO É ANTES DA DATA DE FIM
                objSalvar = bean.salvarBean(objSalvar);
                System.out.println("Tamanho das metas DO PLANO: " + objSalvar.getMetaList().size());
                msgGrowSaveGeneric();
                tabview.setActiveIndex(1);
            } else {
                msgPanelErro("Erro na data", "A data de início deve ser antes da data de fim!");
            }
        } catch (Exception ex) {
            Logger.getLogger(ListarPlanoAcaoView.class.getName()).log(Level.SEVERE, null, ex);
            msgPanelErro("Erro inesperado", "Erro ao tentar salvar, contate o administrador do sistema!");
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

    public void onTabChange(TabChangeEvent event) {//Esse método é necessário para que o "tabView" do bean esteja atualizado com o que esta na tela
        System.out.println("Usuário esta na tab: " + event.getTab().getId());
        if (event.getTab().getId().equals("tabPlano")) {
            tabview.setActiveIndex(0);
        } else if (event.getTab().getId().equals("tabMetas")) {
            tabview.setActiveIndex(1);
        } else {
            tabview.setActiveIndex(2);
        }
    }

    public void onTabAtividadesChange(TabChangeEvent event) {
        System.out.println("Usuário esta na tab: " + event.getTab().getId());
        if (event.getTab().getId().equals("tabInfo")) {
            tabviewAtividades.setActiveIndex(0);
            System.out.println("Usuário esta na tab ATV: " + tabviewAtividades.getActiveIndex());
        } else if (event.getTab().getId().equals("tabUsr")) {
            tabviewAtividades.setActiveIndex(1);
            System.out.println("Usuário esta na tab ATV: " + tabviewAtividades.getActiveIndex());
        } else {
            tabviewAtividades.setActiveIndex(2);
            System.out.println("Usuário esta na tab ATV: " + tabviewAtividades.getActiveIndex());
        }
    }

    public void transfereObj() {//Para botão de editar
        System.out.println("Usuário esta na aba: " + tabview.getActiveIndex());
        if (tabview.getActiveIndex() == 1) {//Se estiver na aba de metas
            metaSalvar = metaSelecionada;
        } else {//senão, esta na aba de atividades
            atividadeSalvar = atividadeSelecionada;

            usuarios = usuarioBean.listarBean();
            parceiros = parceiroBean.listarBean();
            usuariosPickList.setSource(usuarios);
            parceirosPickList.setSource(parceiros);

            //PARCEIRO PICK LIST
            if (atividadeSalvar.getParceiroList().size() > 0) {
                parceirosPickList.setTarget(atividadeSalvar.getParceiroList());
                parceiros.removeAll(atividadeSalvar.getParceiroList());
            } else {
                parceirosSelecionados = new ArrayList<Parceiro>();
                parceirosPickList.setTarget(parceirosSelecionados);
            }

            //USUARIO PICK LIST
            if (atividadeSalvar.getUsuarioList().size() > 0) {
                usuariosPickList.setTarget(atividadeSalvar.getUsuarioList());
                usuarios.removeAll(atividadeSalvar.getUsuarioList());
            } else {
                usuariosSelecionados = new ArrayList<Usuario>();
                usuariosPickList.setTarget(usuariosSelecionados);
            }
        }
    }

    public void reiniciaObj() {//Para botão de cadastrar
        if (tabview.getActiveIndex() == 1) {//Se estiver na aba de metas
            metaSalvar = new Meta();
        } else {//senão, esta na aba de atividades
            usuarios = usuarioBean.listarBean();
            parceiros = parceiroBean.listarBean();

            usuariosPickList.setSource(usuarios);
            parceirosPickList.setSource(parceiros);

            usuariosSelecionados = new ArrayList<Usuario>();
            usuariosPickList.setTarget(usuariosSelecionados);

            parceirosSelecionados = new ArrayList<Parceiro>();
            parceirosPickList.setTarget(parceirosSelecionados);

            atividadeSalvar = new AtividadePlanejada();
        }
    }

    //SETS E GETS
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

    public Meta getMetaSalvar() {
        return metaSalvar;
    }

    public void setMetaSalvar(Meta metaSalvar) {
        this.metaSalvar = metaSalvar;
    }

    public AtividadePlanejada getAtividadeSalvar() {
        return atividadeSalvar;
    }

    public void setAtividadeSalvar(AtividadePlanejada atividadeSalvar) {
        this.atividadeSalvar = atividadeSalvar;
    }

    public Meta getMetaSelecionada() {
        return metaSelecionada;
    }

    public void setMetaSelecionada(Meta metaSelecionada) {
        this.metaSelecionada = metaSelecionada;
    }

    public List<AtividadePlanejada> getListaSalvarAtividades() {
        return listaSalvarAtividades;
    }

    public void setListaSalvarAtividades(List<AtividadePlanejada> listaSalvarAtividades) {
        this.listaSalvarAtividades = listaSalvarAtividades;
    }

    public AtividadePlanejada getAtividadeSelecionada() {
        return atividadeSelecionada;
    }

    public void setAtividadeSelecionada(AtividadePlanejada atividadeSelecionada) {
        this.atividadeSelecionada = atividadeSelecionada;
    }

    public TabView getTabview() {
        return tabview;
    }

    public void setTabview(TabView tabview) {
        this.tabview = tabview;
    }

    public List<Meta> getListaSalvarMetas() {
        return listaSalvarMetas;
    }

    public void setListaSalvarMetas(List<Meta> listaSalvarMetas) {
        this.listaSalvarMetas = listaSalvarMetas;
    }

    public TabView getTabviewAtividades() {
        return tabviewAtividades;
    }

    public void setTabviewAtividades(TabView tabviewAtividades) {
        this.tabviewAtividades = tabviewAtividades;
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
}
