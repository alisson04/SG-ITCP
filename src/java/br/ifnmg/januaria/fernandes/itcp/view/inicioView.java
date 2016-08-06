package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.AtividadePlanejadaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MetaBean;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.Meta;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.timeline.TimelineSelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.timeline.TimelineEvent;
import org.primefaces.model.timeline.TimelineModel;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "inicioView")
@ViewScoped
public class inicioView implements Serializable {

    //VARIAVEIS DA TIMELIME
    private TimelineModel model;
    private boolean selectable = true;
    private boolean zoomable = true;
    private boolean moveable = true;
    private boolean stackEvents = true;
    private String eventStyle = "box";
    private boolean axisOnTop;
    private boolean showCurrentTime = true;
    private boolean showNavigation = false;

    private List<Meta> listaMetas;
    private MetaBean metaBean = new MetaBean();

    //VARIAVEIS
    private ScheduleModel eventModel = new DefaultScheduleModel();
    private ScheduleEvent event = new DefaultScheduleEvent();
    private List<AtividadePlanejada> listaObjs;
    private AtividadePlanejadaBean bean = new AtividadePlanejadaBean();

    //CONSTRUTOR
    @PostConstruct
    public void init() {
        listaObjs = bean.listarBean();

        for (int i = 0; i < listaObjs.size(); i++) {
            eventModel.addEvent(new DefaultScheduleEvent(listaObjs.get(i).getNome(), listaObjs.get(i).getDataInicio(), listaObjs.get(i).getDataFim()));
        }

        //TIMELIME
        listaMetas = metaBean.listarBean();
        model = new TimelineModel();
        Calendar cal = Calendar.getInstance();

        for (int i = 0; i < listaMetas.size(); i++) {
            model.add(new TimelineEvent(listaMetas.get(i).getNome(), listaMetas.get(i).getPrazo()));
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

    //METODOS TIMELINE
    public void onSelect(TimelineSelectEvent e) {
        TimelineEvent timelineEvent = e.getTimelineEvent();

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected event:", timelineEvent.getData().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
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

    //SETS GETS TIMELINE
    public TimelineModel getModel() {
        return model;
    }

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    public boolean isZoomable() {
        return zoomable;
    }

    public void setZoomable(boolean zoomable) {
        this.zoomable = zoomable;
    }

    public boolean isMoveable() {
        return moveable;
    }

    public void setMoveable(boolean moveable) {
        this.moveable = moveable;
    }

    public boolean isStackEvents() {
        return stackEvents;
    }

    public void setStackEvents(boolean stackEvents) {
        this.stackEvents = stackEvents;
    }

    public String getEventStyle() {
        return eventStyle;
    }

    public void setEventStyle(String eventStyle) {
        this.eventStyle = eventStyle;
    }

    public boolean isAxisOnTop() {
        return axisOnTop;
    }

    public void setAxisOnTop(boolean axisOnTop) {
        this.axisOnTop = axisOnTop;
    }

    public boolean isShowCurrentTime() {
        return showCurrentTime;
    }

    public void setShowCurrentTime(boolean showCurrentTime) {
        this.showCurrentTime = showCurrentTime;
    }

    public boolean isShowNavigation() {
        return showNavigation;
    }

    public void setShowNavigation(boolean showNavigation) {
        this.showNavigation = showNavigation;
    }
}
