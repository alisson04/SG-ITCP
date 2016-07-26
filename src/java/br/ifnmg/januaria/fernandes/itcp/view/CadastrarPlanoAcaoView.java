package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.AtividadePlanejadaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MetaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.PlanoAcaoBean;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.Meta;
import br.ifnmg.januaria.fernandes.itcp.domain.PlanoAcao;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "CadastrarPlanoAcaoView")
@ViewScoped
public class CadastrarPlanoAcaoView implements Serializable {

    PlanoAcaoBean bean = new PlanoAcaoBean();
    EmpreendimentoBean empreendimentoBean = new EmpreendimentoBean();
    AtividadePlanejadaBean atividadeBean = new AtividadePlanejadaBean();

    private PlanoAcao objSalvar = new PlanoAcao();
    private Meta metaSalvar = new Meta();
    private Meta metaSelecionada = new Meta();

    private AtividadePlanejada atividadeSalvar = new AtividadePlanejada();
    private AtividadePlanejada atividadeSelecionada = new AtividadePlanejada();

    private List<Empreendimento> listaEmpreendimentos;
    private List<AtividadePlanejada> listaSalvarAtividades = new ArrayList();

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

    public void salvarAtividade() {
        if(atividadeSalvar.getDataFim().before(atividadeSalvar.getDataInicio())){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro na data", "A 'data de início' da atividade deve ser igual ou maior que sua data de fim!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }else{
            if (atividadeSalvar.getDataInicio().after(objSalvar.getDataInicio()) || atividadeSalvar.getDataInicio().equals(objSalvar.getDataInicio())) {
                if (atividadeSalvar.getDataFim().before(atividadeSalvar.getMeta().getPrazo())
                    || atividadeSalvar.getDataFim().equals(atividadeSalvar.getMeta().getPrazo())) {
                atividadeSalvar = atividadeBean.salvarBean(atividadeSalvar);
                listaSalvarAtividades.add(atividadeSalvar);
                atividadeSalvar = new AtividadePlanejada();
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('wVarEditarAtividadeDialog').hide()");
                } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Erro na data", "A 'data de fim' da atividade deve ser antes ou igual ao prazo de sua meta!");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                }
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Erro na data", "A 'data de fim' da atividade deve ser antes ou igual ao prazo de sua meta!");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }
        }l
    }

    public void salvarMeta() {
        List<Meta> listaSalvarMetas = objSalvar.getMetaList();
        if (listaSalvarMetas == null) {
            listaSalvarMetas = new ArrayList();
        }

        if (metaSalvar.getPrazo().after(objSalvar.getDataInicio()) && metaSalvar.getPrazo().before(objSalvar.getDataFim())) {
            metaSalvar.setPlanoAcao(objSalvar);
            listaSalvarMetas.add(metaSalvar);
            objSalvar.setMetaList(listaSalvarMetas);
            metaSalvar = new Meta();
            System.err.println("Tamanho das metas: " + listaSalvarMetas.size());
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('wVarEditarDialog').hide()");
            salvarView();
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro na data", "O prazo da meta deve ser depois da 'data de início' e antes da 'data de fim'!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void salvarView() {
        try {
            if (objSalvar.getDataInicio().before(objSalvar.getDataFim())) {// SE A DATA DE INÍCIO É ANTES DA DATA DE FIM
                objSalvar = bean.salvarBean(objSalvar);
                System.err.println("Tamanho das metas DO PLANO: " + objSalvar.getMetaList().size());
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Salvo com sucesso!", "As informações foram salvas com sucesso!");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                tabview.setActiveIndex(1);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Erro na data", "A data de início deve ser antes da data de fim!");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }
        } catch (Exception ex) {
            Logger.getLogger(ListarPlanoAcaoView.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro inesperado", "Erro ao tentar salvar, contate o administrador do sistema!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
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
}
