package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.AtividadePlanejadaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MetaBean;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.Meta;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
    
    private LineChartModel lineModel1;

    //CONSTRUTOR
    @PostConstruct
    public void init() {
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
    }
    
    public LineChartModel getLineModel1() {
        return lineModel1;
    }
     
    private void createLineModels() {
        lineModel1 = initLinearModel();
        lineModel1.setTitle("Linear Chart");
        lineModel1.setLegendPosition("e");
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
    }
     
    private LineChartModel initLinearModel() {
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
    }

    //METODOS
    public String conveteData(Date data) {
        if (data != null) {
            SimpleDateFormat forma = new SimpleDateFormat("dd/MM/yyyy");
            return forma.format(data);
        } else {
            return "";
        }
    }
    
    public void mudaAtvParaNaoExecutada(AtividadePlanejada at) {//Muda Status da Atividade para "Não executada"
        at.setStatus("Não iniciada");
        bean.salvarBean(at);//Salva a Atividade
        metaBean.atualizaStatusMeta(at.getMeta());//Atualiza o status da meta
        msgGrowSaveGeneric();
    }

    public void mudaAtvParaEmExecução(AtividadePlanejada at) {//Muda Status da Atividade para "Em execução"
        at.setStatus("Iniciada");
        bean.salvarBean(at);
        metaBean.atualizaStatusMeta(at.getMeta());//Atualiza o status da meta
        msgGrowSaveGeneric();
    }

    public void mudaAtvParaExecutada(AtividadePlanejada at) {//Muda Status da Atividade para "Executada"
        at.setStatus("Finalizada");
        bean.salvarBean(at);
        metaBean.atualizaStatusMeta(at.getMeta());//Atualiza o status da meta
        msgGrowSaveGeneric();
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
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
}
