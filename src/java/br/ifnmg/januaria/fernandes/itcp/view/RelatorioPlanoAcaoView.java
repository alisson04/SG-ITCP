package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import java.io.Serializable;
import java.util.List;
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

    //Gráfico vars
    private CartesianChartModel combinedModel;

    //Construtor
    public RelatorioPlanoAcaoView() {
        try {
            //Empreendimento Vars
            eesBean = new EmpreendimentoBean();
            listaEes = eesBean.listarBean();
            eesSelecionado = null;//É assim mesmo
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //METODOS    
    public void selecionaEes() {
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
}
