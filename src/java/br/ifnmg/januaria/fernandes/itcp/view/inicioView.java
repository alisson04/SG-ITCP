package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.AtividadePlanejadaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.IncubadoraBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MetaBean;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.Incubadora;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import br.ifnmg.januaria.fernandes.itcp.util.UploadArquivo;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "inicioView")
@ViewScoped
public class inicioView extends MensagensGenericas implements Serializable {

    //VARIAVEIS
    private ScheduleModel eventModel;
    private ScheduleEvent event;
    private List<AtividadePlanejada> listaAtividades;
    private List<AtividadePlanejada> listaAtividadesFiltradas;
    private AtividadePlanejadaBean bean;
    private MetaBean metaBean;

    //Incubadora VARS
    private Incubadora inc;
    private IncubadoraBean incBean;
    private boolean existeInc;
    private UploadArquivo arquivo;

    private LineChartModel lineModel1;

    //CONSTRUTOR
    @PostConstruct
    public void init() {
        try {
            eventModel = new DefaultScheduleModel();
            event = new DefaultScheduleEvent();
            bean = new AtividadePlanejadaBean();
            listaAtividades = bean.listarBean();
            metaBean = new MetaBean();

            //Adiciona atividade ao calendário
            for (int i = 0; i < listaAtividades.size(); i++) {
                eventModel.addEvent(new DefaultScheduleEvent(
                        listaAtividades.get(i).getNome(),
                        listaAtividades.get(i).getDataInicio(),
                        listaAtividades.get(i).getDataFim(),
                        "corDialog"));
            }

            createLineModels();

            //Incubadora CONTRUTOR
            inc = new Incubadora();
            incBean = new IncubadoraBean();
            existeInc = true;

            if (incBean.contarLinhasBean() == 0) {
                System.out.println("Não incubadora cadastrada");
                existeInc = false;
            }
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

    public void salvarInc() {
        try {
            incBean.salvarBean(inc);
            msgGrowSaveGeneric();
            existeInc = true;
        } catch (Exception ex) {
            throw new FacesException(ex);
        } finally {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('blockUiGeral').hide()");
        }
    }

    public LineChartModel getLineModel1() {
        try {
            return lineModel1;
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    private void createLineModels() {
        try {
            lineModel1 = initLinearModel();
            lineModel1.setTitle("Linear Chart");
            lineModel1.setLegendPosition("e");
            Axis yAxis = lineModel1.getAxis(AxisType.Y);
            yAxis.setMin(0);
            yAxis.setMax(10);
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    private LineChartModel initLinearModel() {
        try {
            LineChartModel model = new LineChartModel();

            LineChartSeries series1 = new LineChartSeries();
            series1.setLabel("Series 1");

            series1.set(1, 2);
            series1.set(2, 1);
            series1.set(3, 3);
            series1.set(4, 6);
            series1.set(5, 8);

            LineChartSeries series2 = new LineChartSeries();
            series2.setLabel("Series 2");

            series2.set(1, 6);
            series2.set(2, 3);
            series2.set(3, 2);
            series2.set(4, 7);
            series2.set(5, 9);

            model.addSeries(series1);
            model.addSeries(series2);

            return model;
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //METODOS
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

    public void mudaAtvParaNaoExecutada(AtividadePlanejada at) {//Muda Status da Atividade para "Não executada"
        try {
            at.setStatus("Não iniciada");
            bean.salvarBean(at);//Salva a Atividade
            metaBean.atualizaStatusMeta(at.getMeta());//Atualiza o status da meta
            msgGrowSaveGeneric();
        } catch (Exception ex) {
            throw new FacesException(ex);
        } finally {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('blockUiGeral').hide()");
        }
    }

    public void mudaAtvParaEmExecução(AtividadePlanejada at) {//Muda Status da Atividade para "Em execução"
        try {
            at.setStatus("Iniciada");
            bean.salvarBean(at);
            metaBean.atualizaStatusMeta(at.getMeta());//Atualiza o status da meta
            msgGrowSaveGeneric();
        } catch (Exception ex) {
            throw new FacesException(ex);
        } finally {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('blockUiGeral').hide()");
        }
    }

    public void mudaAtvParaExecutada(AtividadePlanejada at) {//Muda Status da Atividade para "Executada"
        try {
            at.setStatus("Finalizada");
            bean.salvarBean(at);
            metaBean.atualizaStatusMeta(at.getMeta());//Atualiza o status da meta
            msgGrowSaveGeneric();
        } catch (Exception ex) {
            throw new FacesException(ex);
        } finally {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('blockUiGeral').hide()");
        }
    }

    public void onEventSelect(SelectEvent selectEvent) {
        try {
            event = (ScheduleEvent) selectEvent.getObject();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //SETS E GETS
    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public List<AtividadePlanejada> getListaAtividades() {
        return listaAtividades;
    }

    public void setListaAtividades(List<AtividadePlanejada> listaAtividades) {
        this.listaAtividades = listaAtividades;
    }

    public List<AtividadePlanejada> getListaAtividadesFiltradas() {
        return listaAtividadesFiltradas;
    }

    public void setListaAtividadesFiltradas(List<AtividadePlanejada> listaAtividadesFiltradas) {
        this.listaAtividadesFiltradas = listaAtividadesFiltradas;
    }

    public Incubadora getInc() {
        return inc;
    }

    public void setInc(Incubadora inc) {
        this.inc = inc;
    }

    public boolean isExisteInc() {
        return existeInc;
    }

    public void setExisteInc(boolean existeInc) {
        this.existeInc = existeInc;
    }
}
