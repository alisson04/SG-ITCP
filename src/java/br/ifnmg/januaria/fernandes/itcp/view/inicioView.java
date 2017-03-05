package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.AtividadePlanejadaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.HorasTrabalhadasBean;
import br.ifnmg.januaria.fernandes.itcp.bean.IncubadoraBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MetaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.UsuarioBean;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.HorasTrabalhadas;
import br.ifnmg.januaria.fernandes.itcp.domain.Incubadora;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import br.ifnmg.januaria.fernandes.itcp.util.UploadArquivo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.DualListModel;
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
    private Date dataFiltroInicioAtv;
    private Date dataFiltroFimAtv;
    private AtividadePlanejada atividadeSelecionada;

    //Horas trabalhadas
    private HorasTrabalhadas horasTrabalhadas;//Variáves q recebe a atividade q esta tendo horas lancadas
    private HorasTrabalhadasBean horasBean;
    private List<HorasTrabalhadas> listaHorasTrabalhadas;
    private Date totalHorasAtividade = new Date();//Total de horas trabalhados em uma atividade

    private MetaBean metaBean;

    //Incubadora VARS
    private Incubadora inc;
    private IncubadoraBean incBean;
    private boolean existeInc;
    private UploadArquivo arquivo;

    //PickList Usuario
    private UsuarioBean usuarioBean;
    private List<Usuario> listaUsuarios;
    private List<Usuario> usuariosSelecionados;
    private DualListModel<Usuario> usuariosPickList;

    //CONSTRUTOR
    @PostConstruct
    public void init() {
        try {
            eventModel = new DefaultScheduleModel();
            event = new DefaultScheduleEvent();
            bean = new AtividadePlanejadaBean();
            listaAtividades = bean.listarBean();
            metaBean = new MetaBean();
            atividadeSelecionada = new AtividadePlanejada();

            //Incubadora CONTRUTOR
            inc = new Incubadora();
            incBean = new IncubadoraBean();
            existeInc = true;

            if (incBean.contarLinhasBean() == 0) {
                System.out.println("Não incubadora cadastrada");
                existeInc = false;
            }

            //Filtro de datas na listagem de atividades
            Calendar x = Calendar.getInstance();//Pega a data atual
            x.add((Calendar.DAY_OF_MONTH), -3);//soma 7 a data atual
            dataFiltroInicioAtv = x.getTime();//Seta a data atual
            x.add((Calendar.DAY_OF_MONTH), 7);//soma 7 a data atual
            dataFiltroFimAtv = x.getTime();//Seta a data somada
            filtraAtividadesPorData();//Filtra as atividades

            //Horas trabalhadas
            horasBean = new HorasTrabalhadasBean();
            horasTrabalhadas = new HorasTrabalhadas();
            totalHorasAtividade.setTime(0);

            //PickList Usuario
            usuarioBean = new UsuarioBean();
            listaUsuarios = usuarioBean.listarBean();
            usuariosSelecionados = new ArrayList<Usuario>();
            usuariosPickList = new DualListModel<Usuario>(listaUsuarios, usuariosSelecionados);
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //METODOS USUARIO PICK LIST
    public void configuraPickList(AtividadePlanejada atv) {
        atividadeSelecionada = atv;//copia o obj para ser usado ao salvar a atv
        listaUsuarios = usuarioBean.listarBean();

        if (!atv.getUsuarioList().isEmpty()) {
            System.out.println("Lista cheio");
            usuariosPickList.setTarget(atv.getUsuarioList());
            listaUsuarios.removeAll(atv.getUsuarioList());

            usuariosPickList.setSource(listaUsuarios);
        } else {
            System.out.println("Lista vazia!");
            usuariosSelecionados = new ArrayList<Usuario>();
            usuariosPickList.setTarget(usuariosSelecionados);
            usuariosPickList.setSource(listaUsuarios);
        }
    }

    public void salvarAtribuicaoAtividade() {
        atividadeSelecionada.setUsuarioList(usuariosPickList.getTarget());//Seta usuários na atividade
        bean.salvarBean(atividadeSelecionada);//Salva a atividade no BD
        filtraAtividadesPorData();//Atualiza as atividades filtrando por data estabelecida no construtor    
        listaUsuarios = usuarioBean.listarBean();
        usuariosSelecionados = new ArrayList<Usuario>();
        usuariosPickList = new DualListModel<Usuario>(listaUsuarios, usuariosSelecionados);//Limpa a pickList
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('wVAtribuirAtvDialog').hide()");
        msgGrowSaveGeneric();
    }

    //METODOS LANÇAR HORAS
    public void setaPrimaryKeyHoras(AtividadePlanejada atv, Usuario user) {//Chamdo pelo botão de lancar horas
        horasTrabalhadas.setUsuario(user);
        horasTrabalhadas.setAtividadePlanejada(atv);

        listaHorasTrabalhadas = horasBean.listarPorUserAtividadeBean(user, atv);
    }

    public void salvarLancamentoHoras() {//Chamado pelo botão de salvar horas lancadas
        try {
            if (horasTrabalhadas.getHorasInicio().before(horasTrabalhadas.getHorasFim())) {
                horasBean.salvarBean(horasTrabalhadas);
                horasTrabalhadas = new HorasTrabalhadas();
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('wVLancarHorasDialog').hide()");
                msgGrowSaveGeneric();
            } else {
                msgGrowlErroCustomizavel("Verifique os horários", "A hora de início deve ser antes da hora de fim!");
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void filtraAtividadesPorData() {
        Date inicioAtv;
        Date fimAtv;
        List<AtividadePlanejada> listaAux = new ArrayList();
        listaAtividades = bean.listarBean();

        for (int i = 0; i < listaAtividades.size(); i++) {
            inicioAtv = listaAtividades.get(i).getDataInicio();
            fimAtv = listaAtividades.get(i).getDataFim();

            if ((inicioAtv.after(dataFiltroInicioAtv) || inicioAtv.equals(dataFiltroInicioAtv))
                    && (fimAtv.before(dataFiltroFimAtv) || fimAtv.equals(dataFiltroFimAtv))) {
                listaAux.add(listaAtividades.get(i));
            }
        }
        listaAtividades = listaAux;
    }

    public String geraHorasTotalAtividade() {
        if (listaHorasTrabalhadas != null) {
            HorasTrabalhadas obj;
            int segundos = 0;
            for (int i = 0; i < listaHorasTrabalhadas.size(); i++) {
                obj = listaHorasTrabalhadas.get(i);
                segundos = (int) Math.floor(segundos + (((int) (long) (obj.getHorasFim().getTime() - obj.getHorasInicio().getTime())) / 1000));
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
    }

    public String geraHorasAtividade(HorasTrabalhadas h) {
        try {
            if (h.getId() != null) {
                int segundos = (int) Math.floor(((int) (long) (h.getHorasFim().getTime()
                        - h.getHorasInicio().getTime())) / 1000);
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

    public void excluirHorasTrabalhadasView(HorasTrabalhadas h, Usuario u) {
        try {
            horasBean.excluirBean(h);
            setaPrimaryKeyHoras(h.getAtividadePlanejada(), u);
            msgGrowDeleteGeneric();
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

    public Date getDataFiltroInicioAtv() {
        return dataFiltroInicioAtv;
    }

    public void setDataFiltroInicioAtv(Date dataFiltroInicioAtv) {
        this.dataFiltroInicioAtv = dataFiltroInicioAtv;
    }

    public Date getDataFiltroFimAtv() {
        return dataFiltroFimAtv;
    }

    public void setDataFiltroFimAtv(Date dataFiltroFimAtv) {
        this.dataFiltroFimAtv = dataFiltroFimAtv;
    }

    public HorasTrabalhadas getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(HorasTrabalhadas atividadeLancarHoras) {
        this.horasTrabalhadas = atividadeLancarHoras;
    }

    public List<HorasTrabalhadas> getListaHorasTrabalhadas() {
        return listaHorasTrabalhadas;
    }

    public void setListaHorasTrabalhadas(List<HorasTrabalhadas> listaHorasTrabalhadas) {
        this.listaHorasTrabalhadas = listaHorasTrabalhadas;
    }

    public Date getTotalHorasAtividade() {
        return totalHorasAtividade;
    }

    public void setTotalHorasAtividade(Date totalHorasAtividade) {
        this.totalHorasAtividade = totalHorasAtividade;
    }

    public DualListModel<Usuario> getUsuariosPickList() {
        return usuariosPickList;
    }

    public void setUsuariosPickList(DualListModel<Usuario> usuariosPickList) {
        this.usuariosPickList = usuariosPickList;
    }

    public AtividadePlanejada getAtividadeSelecionada() {
        return atividadeSelecionada;
    }

    public void setAtividadeSelecionada(AtividadePlanejada atividadeSelecionada) {
        this.atividadeSelecionada = atividadeSelecionada;
    }
}
