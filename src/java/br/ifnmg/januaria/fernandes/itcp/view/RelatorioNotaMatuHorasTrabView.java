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
import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "RelatorioNotaMatuHorasTrabView")
@ViewScoped
public class RelatorioNotaMatuHorasTrabView extends MensagensGenericas implements Serializable {

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
    private List<CartesianChartModel> listaGraficoCombinado;

    //Relatorio PDF vars
    private List<String> listaHorasPdf;
    private List<String> listaUsuariosPdf;
    private List<String> listaImagensBase64;
    private List<String> listaWidgetVars;

    //Construtor
    public RelatorioNotaMatuHorasTrabView() {
        try {
            //Gráfico vars
            listaGraficoCombinado = new ArrayList<>();

            //Empreendimento Vars
            eesBean = new EmpreendimentoBean();
            listaEes = eesBean.listarBean();
            eesSelecionado = null;//É assim mesmo

            //Usuário Vars
            userlBean = new UsuarioBean();
            listaUser = userlBean.listarBean();

            //HorasTrabalhadas Vars
            horasTrabBean = new HorasTrabalhadasBean();

            //Relatorio PDF vars
            listaHorasPdf = new ArrayList<>();
            listaUsuariosPdf = new ArrayList<>();
            listaImagensBase64 = new ArrayList<>();
            listaWidgetVars = new ArrayList<>();

            //Filtro de datas na listagem de atividades
            Calendar x = Calendar.getInstance();//Pega a data atual
            x.add((Calendar.DAY_OF_MONTH), -15);//soma 7 a data atual
            dataFiltroInicio = x.getTime();//Seta a data atual
            x.add((Calendar.DAY_OF_MONTH), 30);//soma 7 a data atual
            dataFiltroFim = x.getTime();//Seta a data somada
            filtraHorasTrabalhoPorData();//Filtra as atividades

        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //METODOS
    public void teste() {
        RequestContext.getCurrentInstance().update("frmrelatorios");
    }

    public String corrigeWidgetVar(String widgetVar) {
        int i = widgetVar.indexOf("r");//Utiliza a letra r pq é a ultima do nome que é dado a widgetVar dos gráficos
        System.out.println("CORRIGIU: " + widgetVar.substring(i + 1));
        return widgetVar.substring(i + 1);
    }

    public void gerarRelatorio(String tipo) {
        System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" + tipo);
        try {
            if (!listaEes.isEmpty()) {
                if (!listaImagensBase64.isEmpty()) {
                    modeloGeralView m = new modeloGeralView();
                    List<InputStream> listaTeste = new ArrayList<>();

                    boolean aux;
                    String base64Aux;
                    for (int i = 0; i < listaEes.size(); i++) {//Corrige as bases64 e mantem o objetos vazios
                        aux = false;

                        for (int x = 0; x < listaWidgetVars.size(); x++) {
                            if (corrigeWidgetVar(listaWidgetVars.get(x)).equals(i + "")) {//EES com gráfico preenchido
                                base64Aux = m.corrigeImagemBase64(listaImagensBase64.get(i));//Recebe a base corrigida
                                System.out.println("CORRIGIDO: " + base64Aux);
                                listaImagensBase64.set(i, base64Aux);//Seta a base corrigida
                                listaTeste.add(new ByteArrayInputStream(Base64.decodeBase64(listaImagensBase64.get(i).getBytes())));
                                aux = true;
                            } else {
                                System.out.println("=====================================================EITA");
                            }
                        }

                        if (aux) {
                            InputStream my = null;
                            listaTeste.add(my);
                            System.out.println("Não roda");
                        }
                    }

                    System.out.println("=======================EES: " + listaEes.size());
                    System.out.println("=======================GRA: " + listaTeste.size());

                    //InputStream my = new ByteArrayInputStream(Base64.decodeBase64(listaImagensBase64.get(1).getBytes()));
                    //InputStream is = new BufferedInputStream(
                    //new FileInputStream("/home/alisson/MEGA/Sigitec/NetBeansProjects/sigitec/build/web/image/imagensRelatorios/1484408243761.png"));
                    horasTrabBean.gerarRelatorio(listaEes, listaHorasPdf, listaUsuariosPdf, listaTeste, tipo);

                } else {
                    msgGrowlErroCustomizavel(null, "Esta nulo=Não imprime");
                }
            } else {
                msgGrowlErroCustomizavel(null, "Não ha empreendimentos para listar");
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void geraImagemBase64() {
        //Gera e seta imagem na variavel
        for (int i = 0; i < listaWidgetVars.size(); i++) {
            //1º parametro é o gráfico - 2º é o receptor da base64
            RequestContext.getCurrentInstance().execute("exportImageToPdfGeneric('" + listaWidgetVars.get(i)
                    + "', '#frmrelatorios\\\\:gridIn\\\\:" + corrigeWidgetVar(listaWidgetVars.get(i)) + "\\\\:in');");
        }
    }

    public void selecionaEes() {//Quando um EES é selecionado no "selectOneMenu"
        geraImagemBase64();
    }

    private void criaGraficoForcaTrabalho() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");//Cria o formato q data sera mostrada
        float eixoY = 0;//Guarda o maior valor para o eixo Y

        List<NotaMaturidade> listaNotas;//Cria lista de notas
        NotaMaturidadeBean notaBean = new NotaMaturidadeBean();//Cria o Bean das notas

        for (int x = 0; x < listaEes.size(); x++) {
            CartesianChartModel combinedModel = new LineChartModel();

            //Notas maturidade
            listaNotas = notaBean.listarNotasPorEesBean(listaEes.get(x));

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
            horasSeries.setLabel("Horas trabalhadas");
            HorasTrabalhadas obj;
            int segundos = 0;
            int minutos;
            int horas;
            int minutosRestantes;
            String aux;

            int quantiHoras = 0;//Salva a quantidade de objetos de "horas" encontrados para tal EES
            for (int i = 0; i < listaHorasTrab.size(); i++) {
                System.out.println("RODA");
                if (listaHorasTrab.get(i).getAtividadePlanejada().getMeta().getPlanoAcao().getEmpreendimento().equals(eesSelecionado)) {
                    quantiHoras++;
                    obj = listaHorasTrab.get(i);
                    segundos += (int) Math.floor((((int) (long) (obj.getHorasFim().getTime() - obj.getHorasInicio().getTime())) / 1000));
                    minutos = (segundos / 60);
                    horas = minutos / 60;
                    minutosRestantes = minutos % 60;
                    System.out.println("HORAS: " + horas + ":" + minutosRestantes);
                    aux = (String.valueOf(horas) + "." + String.valueOf(minutosRestantes));
                    System.out.println("AUX: " + aux);
                    horasSeries.set(dateFormat.format(obj.getDataTrabalho()), Float.parseFloat(aux));

                    if (Float.parseFloat(aux) > eixoY) {
                        eixoY = Float.parseFloat(aux);
                    }
                    System.out.println("Adicionou a data " + dateFormat.format(listaHorasTrab.get(i).getDataTrabalho())
                            + " e horas " + (((listaHorasTrab.get(i).getHorasFim().getTime() - listaHorasTrab.get(i).getHorasInicio().getTime())) / 1000));
                }
            }

            if (!(listaNotas.isEmpty() && quantiHoras == 0)) {
                //Geral
                combinedModel.addSeries(notasSeries);
                combinedModel.addSeries(horasSeries);

                combinedModel.setTitle(listaEes.get(x).getSigla());
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
                yAxis.setMax(eixoY + (eixoY / 5));

                Calendar calendar = Calendar.getInstance();//Pega a data atual
                calendar.add(Calendar.DAY_OF_MONTH, 3);//Adiciona 3 dias - P ficar + bonito no gráfico
                Date dataAtual = calendar.getTime();
                DateAxis axis = new DateAxis("");//Texto do eixo X do gráfico
                axis.setTickAngle(-40);
                System.out.println("DATA ATUAL: " + dateFormat.format(dataAtual));
                axis.setMin(dateFormat.format(dataFiltroInicio));
                axis.setMax(dateFormat.format(dataFiltroFim));//Seta a data máxima para ser mostrada no gráfico
                axis.setTickFormat("%#d, %m, %y");//Define a ordem dia, mes, ano q é mostrada na parte baixo do gráfico

                combinedModel.getAxes().put(AxisType.X, axis);

                listaGraficoCombinado.add(combinedModel);

                listaWidgetVars.add("WidgetVar" + x);
                listaImagensBase64.add("");
            } else {
                listaImagensBase64.add("");
                System.out.println("Não adicionou p o EES: " + x);
            }
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
        listaUsuariosPdf.add(quantidade + "");//Adiciona para ser exportado como PDF
        return quantidade;
    }

    public String geraHorasTrabAtividades(Empreendimento ees) {//Gera a quantidade de horas gastas em atividades de um ees
        try {
            if (!listaHorasTrab.isEmpty()) {//Se não esta vazio
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
                    listaHorasPdf.add(horas + ":0" + minutosRestantes);//Adiciona para ser exportado como PDF
                    return (horas + ":0" + minutosRestantes);
                } else {
                    listaHorasPdf.add(horas + ":" + minutosRestantes);//Adiciona para ser exportado como PDF
                    return (horas + ":" + minutosRestantes);
                }
            } else {
                listaHorasPdf.add("0");//Adiciona para ser exportado como PDF
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

    public List<CartesianChartModel> getListaGraficoCombinado() {
        return listaGraficoCombinado;
    }

    public void setListaGraficoCombinado(List<CartesianChartModel> listaGraficoCombinado) {
        this.listaGraficoCombinado = listaGraficoCombinado;
    }

    public Empreendimento getEesSelecionado() {
        return eesSelecionado;
    }

    public void setEesSelecionado(Empreendimento eesSelecionado) {
        this.eesSelecionado = eesSelecionado;
    }

    public List<String> getListaWidgetVars() {
        return listaWidgetVars;
    }

    public void setListaWidgetVars(List<String> listaWidgetVars) {
        this.listaWidgetVars = listaWidgetVars;
    }

    public List<String> getListaImagensBase64() {
        return listaImagensBase64;
    }

    public void setListaImagensBase64(List<String> listaImagensBase64) {
        this.listaImagensBase64 = listaImagensBase64;
    }
}
