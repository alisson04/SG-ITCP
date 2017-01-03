package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.HorasTrabalhadasBean;
import br.ifnmg.januaria.fernandes.itcp.bean.NotaMaturidadeBean;
import br.ifnmg.januaria.fernandes.itcp.bean.UsuarioBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.HorasTrabalhadas;
import br.ifnmg.januaria.fernandes.itcp.domain.NotaMaturidade;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "RelatorioForcaTrabalhoView")
@ViewScoped
public class RelatorioForcaTrabalhoView extends MensagensGenericas implements Serializable {

    //Empreendimento Vars
    private List<Empreendimento> listaEes;
    private EmpreendimentoBean eesBean;
    private Empreendimento eesSelecionado;

    //Usuários Vars
    private List<Usuario> listaUser;
    private UsuarioBean userlBean;

    //HorasTrabalhadas Vars
    private List<HorasTrabalhadas> listaHorasTrab;
    private HorasTrabalhadasBean horasTrabBean;

    //Datas de filtragem de lancamentos de horas trabalhadas
    private Date dataFiltroInicio;
    private Date dataFiltroFim;

    //Gráfico vars
    private CartesianChartModel combinedModel;

    //Construtor
    public RelatorioForcaTrabalhoView() {
        try {
            //Empreendimento Vars
            eesBean = new EmpreendimentoBean();
            listaEes = eesBean.listarBean();
            eesSelecionado = null;//É assim mesmo

            //Usuário Vars
            userlBean = new UsuarioBean();
            listaUser = userlBean.listarBean();

            //HorasTrabalhadas Vars
            horasTrabBean = new HorasTrabalhadasBean();

            //Filtro de datas na listagem de atividades
            Calendar x = Calendar.getInstance();//Pega a data atual
            x.add((Calendar.DAY_OF_MONTH), -3);//soma 7 a data atual
            dataFiltroInicio = x.getTime();//Seta a data atual
            x.add((Calendar.DAY_OF_MONTH), 7);//soma 7 a data atual
            dataFiltroFim = x.getTime();//Seta a data somada
            filtraHorasTrabalhoPorData();//Filtra as atividades
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //METODOS    
    public void selecionaEes() {
        criaGraficoForcaTrabalho();
    }

    private void criaGraficoForcaTrabalho() {
        if (eesSelecionado != null) {//Verificação necessário pois o user pode n ter selecionado o ees ainda
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");//Cria o formato q data sera mostrada
            float eixoY = 0;//Guarda o maior valor para o eixo Y

            combinedModel = new LineChartModel();

            //Notas maturidade
            List<NotaMaturidade> listaNotas;
            NotaMaturidadeBean notaBean = new NotaMaturidadeBean();
            listaNotas = notaBean.listarNotasPorEesBean(eesSelecionado);

            BarChartSeries notasSeries = new BarChartSeries();
            notasSeries.setLabel("Notas de maturidade");

            for (int i = 0; i < listaNotas.size(); i++) {
                notasSeries.set(dateFormat.format(listaNotas.get(i).getDataNota()), listaNotas.get(i).getNota());

                if (listaNotas.get(i).getNota() > eixoY) {
                    eixoY = listaNotas.get(i).getNota();
                }
                System.out.println("Adicionou a data " + dateFormat.format(listaNotas.get(i).getDataNota())
                        + " e nota " + listaNotas.get(i).getNota());
            }

            //Horas trabalhadas        
            LineChartSeries horasSeries = new LineChartSeries();
            horasSeries.setLabel("Horas");
            HorasTrabalhadas obj;
            int segundos = 0;
            int minutos;
            int horas;
            int minutosRestantes;
            String aux;

            for (int i = 0; i < listaHorasTrab.size(); i++) {
                System.out.println("RODA");
                if (listaHorasTrab.get(i).getAtividadePlanejada().getMeta().getPlanoAcao().getEmpreendimento().equals(eesSelecionado)) {
                    obj = listaHorasTrab.get(i);
                    segundos += (int) Math.floor((((int) (long) (obj.getHorasFim().getTime() - obj.getHorasInicio().getTime())) / 1000));
                    minutos = (segundos / 60);
                    horas = minutos / 60;
                    minutosRestantes = minutos % 60;
                    aux = (String.valueOf(horas) + "." + String.valueOf(minutosRestantes));
                    horasSeries.set(dateFormat.format(obj.getDataTrabalho()), Float.parseFloat(aux));

                    if (Float.parseFloat(aux) > eixoY) {
                        eixoY = Float.parseFloat(aux);
                    }
                    System.out.println("Adicionou a data " + dateFormat.format(listaHorasTrab.get(i).getDataTrabalho())
                            + " e horas " + (((listaHorasTrab.get(i).getHorasFim().getTime() - listaHorasTrab.get(i).getHorasInicio().getTime())) / 1000));
                }
            }

            horasSeries.setSmoothLine(false);

            //Geral
            combinedModel.addSeries(notasSeries);
            combinedModel.addSeries(horasSeries);

            combinedModel.setTitle("Bar and Line");
            combinedModel.setLegendPosition("ne");
            combinedModel.setMouseoverHighlight(false);
            combinedModel.setShowDatatip(false);
            combinedModel.setShowPointLabels(false);
            combinedModel.setAnimate(true);
            combinedModel.setShadow(true);
            combinedModel.setMouseoverHighlight(true);
            combinedModel.setShowPointLabels(true);
            Axis yAxis = combinedModel.getAxis(AxisType.Y);
            yAxis.setMin(0);
            yAxis.setMax(eixoY);

            Calendar calendar = Calendar.getInstance();//Pega a data atual
            calendar.add(Calendar.DAY_OF_MONTH, 3);//Adiciona 3 dias - P ficar + bonito no gráfico
            Date dataAtual = calendar.getTime();
            DateAxis axis = new DateAxis("");//Texto do eixo X do gráfico
            axis.setTickAngle(-50);
            System.out.println("DATA ATUAL: " + dateFormat.format(dataAtual));
            axis.setMin(dateFormat.format(dataFiltroInicio));
            axis.setMax(dateFormat.format(dataFiltroFim));//Seta a data máxima para ser mostrada no gráfico
            axis.setTickFormat("%#d, %m, %y");//Define a ordem dia, mes, ano q é mostrada na parte baixo do gráfico

            combinedModel.getAxes().put(AxisType.X, axis);
        }
    }

    public void filtraHorasTrabalhoPorData() {
        try {
            Date dataTrabalho;
            List<HorasTrabalhadas> listaAux = new ArrayList();
            listaHorasTrab = horasTrabBean.listarBean();

            for (int i = 0; i < listaHorasTrab.size(); i++) {
                dataTrabalho = listaHorasTrab.get(i).getDataTrabalho();

                if ((dataTrabalho.after(dataFiltroInicio) || dataTrabalho.equals(dataFiltroInicio))
                        && (dataTrabalho.before(dataFiltroFim) || dataTrabalho.equals(dataFiltroFim))) {
                    listaAux.add(listaHorasTrab.get(i));
                }
            }
            listaHorasTrab = listaAux;

            criaGraficoForcaTrabalho();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public int geraQuantiUser(Empreendimento ees) {//Gera a quantidade de users envolvidos em atividades de um ees
        int quantidade = 0;

        for (int i = 0; i < listaHorasTrab.size(); i++) {//Conta quantos users lançaram horas em atividades p o ees
            if (listaHorasTrab.get(i).getAtividadePlanejada().getMeta().getPlanoAcao().getEmpreendimento().equals(ees)) {
                listaUser.remove(listaHorasTrab.get(i).getUsuario());
                quantidade++;
            }
        }

        listaUser = userlBean.listarBean();//Atualiza a lista 
        return quantidade;
    }

    public String geraHorasTrabAtividades(Empreendimento ees) {//Gera a quantidade de horas gastas em atividades de um ees
        try {
            if (!listaHorasTrab.isEmpty()) {
                int segundos = 0;

                HorasTrabalhadas obj;
                for (int i = 0; i < listaHorasTrab.size(); i++) {
                    if (listaHorasTrab.get(i).getAtividadePlanejada().getMeta().getPlanoAcao().getEmpreendimento().equals(ees)) {
                        obj = listaHorasTrab.get(i);
                        segundos += (int) Math.floor(segundos + (((int) (long) (obj.getHorasFim().getTime() - obj.getHorasInicio().getTime())) / 1000));
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
    public List<HorasTrabalhadas> getListaHorasTrab() {
        return listaHorasTrab;
    }

    public void setListaHorasTrab(List<HorasTrabalhadas> listaHorasTrab) {
        this.listaHorasTrab = listaHorasTrab;
    }

    public Date getDataFiltroInicio() {
        return dataFiltroInicio;
    }

    public void setDataFiltroInicio(Date dataFiltroInicio) {
        this.dataFiltroInicio = dataFiltroInicio;
    }

    public Date getDataFiltroFim() {
        return dataFiltroFim;
    }

    public void setDataFiltroFim(Date dataFiltroFim) {
        this.dataFiltroFim = dataFiltroFim;
    }

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
