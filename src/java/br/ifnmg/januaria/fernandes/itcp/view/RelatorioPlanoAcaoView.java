package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.HorasTrabalhadasBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MetaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.PlanoAcaoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.UsuarioBean;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.HorasTrabalhadas;
import br.ifnmg.januaria.fernandes.itcp.domain.Meta;
import br.ifnmg.januaria.fernandes.itcp.domain.PlanoAcao;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import br.ifnmg.januaria.fernandes.itcp.util.RelatoriosManager;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    //Meta Vars
    private MetaBean metaBean;
    private List<Meta> listaMetas;

    //Gráfico vars
    private CartesianChartModel combinedModel;

    //HorasTrabalhadas Vars
    private List<HorasTrabalhadas> listaHorasTrab;
    private HorasTrabalhadasBean horasTrabBean;

    //Construtor
    public RelatorioPlanoAcaoView() {
        try {
            //Empreendimento Vars
            eesBean = new EmpreendimentoBean();
            listaEes = eesBean.listarBean();
            eesSelecionado = null;//É assim mesmo

            //Plano de ação Vars
            planoSelecionado = null;
            listaPlanos = new ArrayList<>();

            //Meta Vars
            metaBean = new MetaBean();
            listaMetas = new ArrayList<>();

            //HorasTrabalhadas Vars
            horasTrabBean = new HorasTrabalhadasBean();
            listaHorasTrab = horasTrabBean.listarBean();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //METODOS
    public String converteData(Date data) {//Necessário pq não tem como usar o paterrn no field
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

    public void filtrarPlanos() {
        if (eesSelecionado != null) {
            listaPlanos = eesSelecionado.getPlanoAcaoList();
        } else {
            listaPlanos = new ArrayList<>();
        }
    }

    public void gerarMetas() {
        if (planoSelecionado != null) {
            listaMetas = metaBean.buscarMetasPorPlanoBean(planoSelecionado);
        } else {
            listaMetas = new ArrayList<>();
        }
    }

    public void gerarRelatorio(String tipo) {//O tipo se refere a "download" ou na "tela"
        try {
            if (!planoSelecionado.getMetaList().isEmpty()) {
                Map<String, Object> listaParametros = new HashMap<String, Object>();

                listaParametros.put("paramIdPlano", planoSelecionado.getId());
                listaParametros.put("paramPlanoSelecionado", planoSelecionado.getNome());
                listaParametros.put("paramEesSelecionado", eesSelecionado.getNome());

                listaParametros.put("SUBREPORT_DIR", "/home/alisson/MEGA/Sigitec/NetBeansProjects/sigitec/src/java/iReport/");

                RelatoriosManager m = new RelatoriosManager<Meta>();
                m.gerarRelatorioGenericoSemDataSource(listaParametros, "/iReport/relatorioExecucaoPlanoAcao.jasper",
                        "Relatorio-de-execucao-do-Plano-de-Acao.pdf", tipo);
            } else {
                msgGrowlErroCustomizavel(null, "Não ha empreendimentos para listar");
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public String geraHorasTrabAtividades(AtividadePlanejada atv) {//Gera a quanti de horas gastas em atividades de um ees. Chamado poela dataTable na tela
        try {
            List<HorasTrabalhadas> listaHorasTrabAux = listaHorasTrab;//Feito para evitar q a cada chamada o BD seja consultado

            if (!listaHorasTrabAux.isEmpty()) {//Se não esta vazio
                int segundos = 0;
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

                HorasTrabalhadas obj;
                for (int i = 0; i < listaHorasTrabAux.size(); i++) {
                    if (listaHorasTrabAux.get(i).getAtividadePlanejada().equals(atv)) {
                        obj = listaHorasTrab.get(i);
                        segundos = (int) Math.floor(segundos + (((int) (long) (obj.getHorasFim().getTime() - obj.getHorasInicio().getTime())) / 1000));
                    }
                }

                int minutos = (segundos / 60);
                int horas = minutos / 60;
                int minutosRestantes = minutos % 60;

                if (minutosRestantes == 0
                        || minutosRestantes == 1
                        || minutosRestantes == 2
                        || minutosRestantes == 3
                        || minutosRestantes == 4
                        || minutosRestantes == 5
                        || minutosRestantes == 6
                        || minutosRestantes == 7
                        || minutosRestantes == 8
                        || minutosRestantes == 9) {
                    return (horas + ":0" + minutosRestantes);
                } else {
                    return (horas + ":" + minutosRestantes);
                }
            } else {
                return "0";
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

    public List<Meta> getListaMetas() {
        return listaMetas;
    }

    public void setListaMetas(List<Meta> listaMetas) {
        this.listaMetas = listaMetas;
    }
}
