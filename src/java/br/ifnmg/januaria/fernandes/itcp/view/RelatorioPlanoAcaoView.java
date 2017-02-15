package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.PlanoAcaoBean;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.Meta;
import br.ifnmg.januaria.fernandes.itcp.domain.PlanoAcao;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import br.ifnmg.januaria.fernandes.itcp.util.RelatoriosManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.CartesianChartModel;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "RelatorioPlanoAcaoView")
@ViewScoped
public class RelatorioPlanoAcaoView extends MensagensGenericas implements Serializable {

    //Empreendimento Vars
    private List<Empreendimento> listaEes;
    private EmpreendimentoBean eesBean;
    private Empreendimento eesSelecionado;

    //Plano de ação Vars
    private PlanoAcaoBean planoBean;
    private PlanoAcao planoSelecionado;
    private List<PlanoAcao> listaPlanos;

    //Gráfico vars
    private CartesianChartModel combinedModel;

    //Construtor
    public RelatorioPlanoAcaoView() {
        try {
            //Empreendimento Vars
            eesBean = new EmpreendimentoBean();
            listaEes = eesBean.listarBean();
            eesSelecionado = null;//É assim mesmo

            //Plano de ação Vars
            planoSelecionado = new PlanoAcao();
            listaPlanos = new ArrayList<>();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //METODOS    
    public void filtrarPlanos() {
        if (eesSelecionado != null) {
            listaPlanos = eesSelecionado.getPlanoAcaoList();
        }
    }

    public void gerarRelatorio(String tipo) {//O tipo se refere a "download" ou na "tela"
        try {
            if (!planoSelecionado.getMetaList().isEmpty()) {
                Map<String, Object> listaParametros = new HashMap<String, Object>();

                listaParametros.put("param", 1);

                RelatoriosManager m = new RelatoriosManager<Meta>();
                m.gerarRelatorioGenerico(planoSelecionado.getMetaList(), listaParametros, "/iReport/relatorioExecucaoPlanoAcao.jasper",
                        "Relatorio-de-Atividades.pdf", tipo);
            } else {
                msgGrowlErroCustomizavel(null, "Não ha empreendimentos para listar");
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //SETS E GETS
    public List<Empreendimento> getListaEes() {
        return listaEes;
    }

    public void setListaEes(List<Empreendimento> listaEes) {
        this.listaEes = listaEes;
    }

    public CartesianChartModel getCombinedModel() {
        return combinedModel;
    }

    public void setCombinedModel(CartesianChartModel combinedModel) {
        this.combinedModel = combinedModel;
    }

    public Empreendimento getEesSelecionado() {
        return eesSelecionado;
    }

    public void setEesSelecionado(Empreendimento eesSelecionado) {
        this.eesSelecionado = eesSelecionado;
    }

    public PlanoAcao getPlanoSelecionado() {
        return planoSelecionado;
    }

    public void setPlanoSelecionado(PlanoAcao planoSelecionado) {
        this.planoSelecionado = planoSelecionado;
    }

    public List<PlanoAcao> getListaPlanos() {
        return listaPlanos;
    }

    public void setListaPlanos(List<PlanoAcao> listaPlanos) {
        this.listaPlanos = listaPlanos;
    }
}
