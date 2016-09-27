package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.AtividadePlanejadaBean;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import java.io.Serializable;
import java.text.SimpleDateFormat;
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

    //CONSTRUTOR
    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        event = new DefaultScheduleEvent();
        bean = new AtividadePlanejadaBean();
        listaAtividades = bean.listarBean();

        //Adiciona atividade ao calendário
        for (int i = 0; i < listaAtividades.size(); i++) {
            eventModel.addEvent(new DefaultScheduleEvent(
                    listaAtividades.get(i).getNome(),
                    listaAtividades.get(i).getDataInicio(),
                    listaAtividades.get(i).getDataFim(),
                    "corDialog"));
        }
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
        at.setStatus("Não executada");
        bean.salvarBean(at);
        msgGrowSaveGeneric();
    }

    public void mudaAtvParaEmExecução(AtividadePlanejada at) {//Muda Status da Atividade para "Em execução"
        at.setStatus("Em execução");
        bean.salvarBean(at);
        msgGrowSaveGeneric();
    }

    public void mudaAtvParaExecutada(AtividadePlanejada at) {//Muda Status da Atividade para "Executada"
        at.setStatus("Executada");
        bean.salvarBean(at);
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
