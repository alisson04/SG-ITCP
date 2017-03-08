package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.AtividadePlanejadaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MetaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.PlanoAcaoBean;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.Meta;
import br.ifnmg.januaria.fernandes.itcp.domain.PlanoAcao;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "ListarPlanoAcaoView")
@ViewScoped
public class ListarPlanoAcaoView extends MensagensGenericas implements Serializable {

    //Plano de ação VARS
    private PlanoAcaoBean bean;
    private PlanoAcao planoSelecionado;//Plano que é selecionado na lista
    private PlanoAcao objSalvar;//Para salvar nos planos e planos editados
    private List<PlanoAcao> listaPlanoAcao;//Lista de planos
    private List<PlanoAcao> listaPlanoAcaoFiltrados;//Lista de planos filtrados

    //Metas VARS
    private MetaBean metaBean;
    private List<Meta> listaSalvarMetas;
    private Meta metaSalvar;
    private Meta metaSelecionada;

    //Atividade VARS
    private AtividadePlanejadaBean atividadeBean;
    private AtividadePlanejada atividadeSalvar;
    private AtividadePlanejada atividadeSelecionada;
    private List<AtividadePlanejada> listaSalvarAtividades;

    //Empreendimento VARS
    private EmpreendimentoBean empreendimentoBean;
    private List<Empreendimento> listaEmpreendimentos;//Para filtro

    //TabVars
    private TabView tabview;

    public ListarPlanoAcaoView() {
        try {
            //Plano de ação CONSTRU
            bean = new PlanoAcaoBean();
            objSalvar = new PlanoAcao();
            listaPlanoAcao = bean.listarBean();

            //Meta CONSTRU
            metaBean = new MetaBean();
            listaSalvarMetas = new ArrayList();
            metaSalvar = new Meta();

            //Atividades CONSTRU
            atividadeBean = new AtividadePlanejadaBean();
            atividadeSalvar = new AtividadePlanejada();
            listaSalvarAtividades = new ArrayList();

            //Empreenimento CONSTRU
            empreendimentoBean = new EmpreendimentoBean();
            listaEmpreendimentos = empreendimentoBean.listarEesAtivosBean();

            //TabVars
            tabview = new TabView();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }
    //METODOS CADASTRAR

    public void onTabChange(TabChangeEvent event) {//necessário para que o "tabView" do bean esteja atualizado com o que esta na tela
        try {
            if (event.getTab().getId().equals("tabPlano")) {
                tabview.setActiveIndex(0);
            } else if (event.getTab().getId().equals("tabMetas")) {
                tabview.setActiveIndex(1);
            } else if (event.getTab().getId().equals("tabAtividades")) {
                tabview.setActiveIndex(2);
            } else {
                tabview.setActiveIndex(3);
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
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

    public void excluirMeta() {
        try {
            metaBean.excluirBean(metaSelecionada);//Exclui a meta do BD
            atualizaMetasAtividades();//Atualiza atividades e metas
            metaSelecionada = null;//Limpa o obj de meta selecionada
            msgGrowDeleteGeneric();
        } catch (Exception ex) {
            throw new FacesException(ex);
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
        }
    }

    public void salvarAtividade() {
        try {
            Date inicioAtividade = atividadeSalvar.getDataInicio();
            Date fimAtividade = atividadeSalvar.getDataFim();
            Date inicioPlano = objSalvar.getDataInicio();

            if (fimAtividade.before(inicioAtividade)) { //SE o FIM antes deINICIO
                msgPanelErroCustomizavel("Verifique a data", "A 'data de início' da atividade deve ser igual ou maior a sua data de fim!");
            } else if (inicioAtividade.before(inicioPlano)) {//SE INICIO ATIVIDADE antes INICIO PLANO
                msgPanelErroCustomizavel("Verifique a data", "A 'data de início' da atividade deve ser maior ou igual a 'data de iníco' do plano!");
            } else if (fimAtividade.after(atividadeSalvar.getMeta().getPrazo())) {//SE FIM ATIVIDADE depois PRAZO META
                msgPanelErroCustomizavel("Verifique a data", "A 'data de fim' da atividade deve ser antes ou igual ao prazo de sua meta!");
            } else {
                atividadeSalvar = atividadeBean.salvarBean(atividadeSalvar);//Salva a atividade no BD
                atualizaMetasAtividades();//Atualiza atividades e metas
                atividadeSalvar = new AtividadePlanejada();//Limpa o obj que foi salvo
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('wVarEditarAtividadeDialog').hide()");//Esconde a caixa de edição
                msgGrowSaveGeneric();
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //outros METODOS
    public String geraMsgGenericaCampoObrigatorioView() {
        try {
            return msgGenericaCampoObrigatorio();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void btnEditarPlano() {
        tabview.setActiveIndex(0);
        objSalvar = planoSelecionado;
        atualizaMetasAtividades();
    }

    public void fecharDlgPlano() {//Necessário para corrigir bug de multipla seleção indevida
        planoSelecionado = null;
    }

    public void transfereObj() {//Para botão de editar meta e atv
        try {
            System.out.println("TRANSFERE");
            if (tabview.getActiveIndex() == 1) {//Se estiver na aba de metas
                metaSalvar = metaSelecionada;
                System.out.println("METAS" + metaSelecionada.getNome());
            } else {//Esta na aba de atv
                atividadeSalvar = atividadeSelecionada;
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void btnNovoPlano() { //Para botão de cadastrar novo plano
        tabview.setActiveIndex(0); //seta a aba do plano
        objSalvar = new PlanoAcao(); //Limpa o plano
        listaSalvarMetas = new ArrayList(); //Limpa as metas
        listaSalvarAtividades = new ArrayList(); //Limpa as atividades
        metaSelecionada = null; //Limpa a meta selecionada
        atividadeSelecionada = null; //Limpa a atividade selecionada
    }

    public void reiniciaObj() {//Para botão de cadastrar nova meta e atividade
        try {
            metaSelecionada = null; //Limpa a meta selecionada
            atividadeSelecionada = null; //Limpa a atividade selecionada

            metaSalvar = new Meta();
            atividadeSalvar = new AtividadePlanejada();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void salvarView() {//Salvar Plano
        try {
            if (objSalvar.getDataInicio().before(objSalvar.getDataFim())) {// SE A DATA DE INÍCIO É ANTES DA DATA DE FIM
                objSalvar = bean.salvarBean(objSalvar);
                msgGrowSaveGeneric();
                tabview.setActiveIndex(1);
            } else {
                msgPanelErroCustomizavel("Erro na data", "A data de início deve ser antes da data de fim!");
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void excluirView() {
        try {
            bean.excluirBean(planoSelecionado);
            planoSelecionado = null;//Volta o usuario para o estado de nulo/ Não retire
            listaPlanoAcao = bean.listarBean();
            msgGrowDeleteGeneric();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public String converteData(Date data) {//Necessário pq não da p usar "paterrn" no local
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

    public TabView getTabview() {
        return tabview;
    }

    public void setTabview(TabView tabview) {
        this.tabview = tabview;
    }

    public Meta getMetaSelecionada() {
        return metaSelecionada;
    }

    public void setMetaSelecionada(Meta metaSelecionada) {
        this.metaSelecionada = metaSelecionada;
    }

    public List<Meta> getListaSalvarMetas() {
        return listaSalvarMetas;
    }

    public void setListaSalvarMetas(List<Meta> listaSalvarMetas) {
        this.listaSalvarMetas = listaSalvarMetas;
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

    public AtividadePlanejada getAtividadeSelecionada() {
        return atividadeSelecionada;
    }

    public void setAtividadeSelecionada(AtividadePlanejada atividadeSelecionada) {
        this.atividadeSelecionada = atividadeSelecionada;
    }

    public List<AtividadePlanejada> getListaSalvarAtividades() {
        return listaSalvarAtividades;
    }

    public void setListaSalvarAtividades(List<AtividadePlanejada> listaSalvarAtividades) {
        this.listaSalvarAtividades = listaSalvarAtividades;
    }
}
