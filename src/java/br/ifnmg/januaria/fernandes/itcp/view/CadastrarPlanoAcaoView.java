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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "CadastrarPlanoAcaoView")
@ViewScoped
public class CadastrarPlanoAcaoView extends MensagensGenericas implements Serializable {

    PlanoAcaoBean bean = new PlanoAcaoBean();
    EmpreendimentoBean empreendimentoBean = new EmpreendimentoBean();
    MetaBean metaBean = new MetaBean();
    AtividadePlanejadaBean atividadeBean = new AtividadePlanejadaBean();

    private PlanoAcao objSalvar = new PlanoAcao();
    private Meta metaSalvar = new Meta();
    private Meta metaSelecionada;

    private AtividadePlanejada atividadeSalvar = new AtividadePlanejada();
    private AtividadePlanejada atividadeSelecionada = new AtividadePlanejada();

    private List<Empreendimento> listaEmpreendimentos;
    private List<AtividadePlanejada> listaSalvarAtividades = new ArrayList();
    private List<Meta> listaSalvarMetas = new ArrayList();

    TabView tabview = new TabView();

    private boolean skip;

    //Construtor
    public CadastrarPlanoAcaoView() {
        try {
            listaEmpreendimentos = empreendimentoBean.listarBean();
            //tabview.setActiveIndex(0);
        } catch (RuntimeException ex) {
            System.out.println("BEAN(ListarEmpreendimentosView): Erro ao Carregar lista de Planos: " + ex);

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
        System.out.println("ddddddddddddddddddddddd");
        atividadeBean.excluirBean(atividadeSelecionada);
        System.out.println("ddddddddddddddddddddddd");
        listaSalvarAtividades.remove(atividadeSelecionada);
        System.out.println("ddddddddddddddddddddddd");
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
            atividadeSalvar = atividadeBean.salvarBean(atividadeSalvar);
            listaSalvarAtividades.add(atividadeSalvar);
            atividadeSalvar = new AtividadePlanejada();
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

    public void transfereObj() {//Para botão de editar
        System.out.println("Usuário esta na aba: " + tabview.getActiveIndex());
        if (tabview.getActiveIndex() == 1) {//Se estiver na aba de metas
            metaSalvar = metaSelecionada;
        } else {//senão, esta na aba de atividades
            atividadeSalvar = atividadeSelecionada;
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

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
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
}
