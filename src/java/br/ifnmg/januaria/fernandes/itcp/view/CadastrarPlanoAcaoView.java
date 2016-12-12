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
import javax.faces.FacesException;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author alisson
 */
@ViewScoped
@Named("CadastrarPlanoAcaoView")
public class CadastrarPlanoAcaoView extends MensagensGenericas implements Serializable {

    //Variáveis
    private PlanoAcaoBean bean;
    private EmpreendimentoBean empreendimentoBean;
    private MetaBean metaBean;
    private AtividadePlanejadaBean atividadeBean;

    private PlanoAcao objSalvar;
    private Meta metaSalvar;
    private Meta metaSelecionada;
    private AtividadePlanejada atividadeSalvar;
    private AtividadePlanejada atividadeSelecionada;

    private List<Empreendimento> listaEmpreendimentos;
    private List<AtividadePlanejada> listaSalvarAtividades;
    private List<Meta> listaSalvarMetas;

    TabView tabview;
    TabView tabviewAtividades;

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
            tabview = new TabView();
            tabviewAtividades = new TabView();

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
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //METODOS
    public String geraMsgGenericaCampoObrigatorioView() {
        try {
            return msgGenericaCampoObrigatorio();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void excluirMeta() {
        try {
            metaBean.excluirBean(metaSelecionada);//Exclui a meta do BD
            atualizaMetasAtividades();//Atualiza atividades e metas
            metaSelecionada = null;//Limpa o obj de meta selecionada
            msgGrowDeleteGeneric();
        } catch (Exception ex) {
            throw new FacesException(ex);
        } finally {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('blockUiGeral').hide()");
        }
    }

    public void excluirAtividade() {
        try {
            atividadeBean.excluirBean(atividadeSelecionada);//Exclui a atividade do BD
            atualizaMetasAtividades();//Atualiza atividades e metas
            atividadeSelecionada = null;//Limpa o obj de atividade selecionada
            msgGrowDeleteGeneric();
        } catch (Exception ex) {
            throw new FacesException(ex);
        } finally {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('blockUiGeral').hide()");
        }
    }

    public void salvarAtividade() {
        try {
            Date inicioAtividade = atividadeSalvar.getDataInicio();
            Date fimAtividade = atividadeSalvar.getDataFim();
            Date inicioPlano = objSalvar.getDataInicio();

            if (fimAtividade.before(inicioAtividade)) { //SE o FIM antes deINICIO
                msgPanelErroCustomizavel("Erro na data", "A 'data de início' da atividade deve ser igual ou maior a sua data de fim!");
            } else if (inicioAtividade.before(inicioPlano)) {//SE INICIO ATIVIDADE antes INICIO PLANO
                msgPanelErroCustomizavel("Erro na data", "A 'data de início' da atividade deve ser maior ou igual a 'data de iníco' do plano!");
            } else if (fimAtividade.after(atividadeSalvar.getMeta().getPrazo())) {//SE FIM ATIVIDADE depois PRAZO META
                msgPanelErroCustomizavel("Erro na data", "A 'data de fim' da atividade deve ser antes ou igual ao prazo de sua meta!");
            } else {
                atividadeSalvar.setUsuarioList(usuariosPickList.getTarget());//Seta usuários na atividade
                atividadeSalvar.setParceiroList(parceirosPickList.getTarget());//Seta parceiros na atividade
                atividadeSalvar = atividadeBean.salvarBean(atividadeSalvar);//Salva a atividade no BD

                atualizaMetasAtividades();//Atualiza atividades e metas

                atividadeSalvar = new AtividadePlanejada();//Limpa o obj que foi salvo

                //Limpa valores das PickLists
                usuarios = usuarioBean.listarBean();
                parceiros = parceiroBean.listarBean();
                usuariosSelecionados = new ArrayList<Usuario>();
                parceirosSelecionados = new ArrayList<Parceiro>();
                usuariosPickList = new DualListModel<Usuario>(usuarios, usuariosSelecionados);
                parceirosPickList = new DualListModel<Parceiro>(parceiros, parceirosSelecionados);

                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('wVarEditarAtividadeDialog').hide()");//Esconde a caixa de edição
                msgGrowSaveGeneric();
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        } finally {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('blockUiGeral').hide()");
        }
    }

    public void salvarMeta() {
        try {
            if (metaSalvar.getPrazo().before(objSalvar.getDataInicio())) {
                msgPanelErroCustomizavel("Erro na data", "O prazo da meta deve ser maior ou igual a 'data de início' do plano de ação!");
            } else if (metaSalvar.getPrazo().after(objSalvar.getDataFim())) {
                msgPanelErroCustomizavel("Erro na data", "O prazo da meta deve ser menor ou igual a 'data de fim' do plano de ação'!");
            } else {
                metaSalvar.setPlanoAcao(objSalvar);
                metaSalvar = metaBean.salvarBean(metaSalvar);//Salva a meta no BD
                atualizaMetasAtividades();//Atualiza atividades e metas
                metaSalvar = new Meta();//Limpa o obj que foi salvo
                System.out.println("Tamanho das metas: " + listaSalvarMetas.size());
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('wVarEditarDialog').hide()");
                msgGrowSaveGeneric();
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        } finally {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('blockUiGeral').hide()");
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
                msgPanelErroCustomizavel("Erro na data", "A data de início deve ser antes da data de fim!");
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        } finally {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('blockUiGeral').hide()");
        }
    }

    public void atualizaMetasAtividades() {
        try {
            listaSalvarMetas = metaBean.buscarMetasPorPlanoBean(objSalvar);//Atualiza a lista de metas
            System.out.println("Num de metas no plano: " + listaSalvarMetas.size());
            listaSalvarAtividades = new ArrayList();//limpa a lista de atividades
            for (int i = 0; i < listaSalvarMetas.size(); i++) {//Roda todas as metas do plano em questão
                System.out.println("Num de ativi na meta: "
                        + atividadeBean.buscarAtividadesPorMetaBean(listaSalvarMetas.get(i)));
                listaSalvarAtividades.addAll(atividadeBean.buscarAtividadesPorMetaBean(listaSalvarMetas.get(i))); //insere todas as atividades na lista
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void onTabChange(TabChangeEvent event) {//necessário para que o "tabView" do bean esteja atualizado com o que esta na tela
        try {
            if (event.getTab().getId().equals("tabPlano")) {
                tabview.setActiveIndex(0);
            } else if (event.getTab().getId().equals("tabMetas")) {
                tabview.setActiveIndex(1);
            } else {
                tabview.setActiveIndex(2);
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void onTabAtividadesChange(TabChangeEvent event) {
        try {
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
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void transfereObj() {//Para botão de editar
        try {
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
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void reiniciaObj() {//Para botão de cadastrar
        try {
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
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public String conveteData(Date data) {
        try {
            if (data != null) {
                SimpleDateFormat forma = new SimpleDateFormat("dd/MM/yyyy");
                return forma.format(data);
            } else {
                return "";
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
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
