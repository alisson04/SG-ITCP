package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.AtividadePlanejadaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoIndicadorBean;
import br.ifnmg.januaria.fernandes.itcp.bean.PlanoAcaoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.UsuarioBean;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadeUsuario;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicador;
import br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicadorPK;
import br.ifnmg.januaria.fernandes.itcp.domain.Indicador;
import br.ifnmg.januaria.fernandes.itcp.domain.PlanoAcao;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.GerenciadorIndicadores;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "RelatoriosView")
@ViewScoped
public class RelatoriosView extends MensagensGenericas implements Serializable {

    //Empreendimento Vars
    private Empreendimento empreendimentoSelecionado;
    private List<Empreendimento> listaEmpreendimentos;
    private EmpreendimentoBean eptBean;

    //Gráfico Vars
    private LineChartModel graficoAtividades;
    private String categoriaSelecionada;//Para receber o valor que representa a categoria selecionada
    private List<EmpreendimentoIndicador> listaEptIndGrafico;

    //EmpreendimentoIndicador Vars
    private EmpreendimentoIndicadorBean EesIndBean;

    //Indicador Vars
    private GerenciadorIndicadores gerenIndicadores;//Para gerar os indicadores
    private List<Indicador> listaIndicadores;//Para guardar os indicadores que serão usados na tela

    //Atividade Vars
    private List<AtividadePlanejada> listaAtividades;
    private List<AtividadePlanejada> listaAtividadesFiltradas;
    private AtividadePlanejadaBean atividadeBean;

    //Plano de ação
    private List<PlanoAcao> listaPlanos;
    private PlanoAcaoBean planoBean;
    private PlanoAcao planoSelecionado;

    //Usuários
    private List<Usuario> listaUser;
    private UsuarioBean userlBean;
    private Usuario userSelecionado;

    private BarChartModel barModel;

    private PieChartModel graficoAtividadesPlano;

    //Construtor
    public RelatoriosView() {
        eptBean = new EmpreendimentoBean();
        EesIndBean = new EmpreendimentoIndicadorBean();
        listaEmpreendimentos = eptBean.listarBean();

        //Gráfico
        listaEptIndGrafico = new ArrayList();
        categoriaSelecionada = "";//Necessário, não retirar

        //Indicador
        gerenIndicadores = new GerenciadorIndicadores();
        listaIndicadores = gerenIndicadores.listarIndicadores();

        //Atividade Vars
        atividadeBean = new AtividadePlanejadaBean();
        listaAtividades = atividadeBean.listarBean();

        //Plano de ação
        planoBean = new PlanoAcaoBean();
        listaPlanos = planoBean.listarBean();

        //User
        userlBean = new UsuarioBean();
        listaUser = userlBean.listarBean();
    }

    //Metodos gráfico /barra
    public void criaGraficoBarras() {
        Calendar calendar = Calendar.getInstance();//Pega a data atual
        calendar.add(Calendar.DAY_OF_MONTH, 3);//Adiciona 3 dias - P ficar + bonito no gráfico

        barModel = preencheGraficoBarras();
        barModel.setTitle("Evolução dos indicadores da categoria " + categoriaSelecionada);
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Datas");
        //xAxis.setMax(dateFormat.format(dataAtual));//Seta a data máxima para ser mostrada no gráfico
        //xAxis.setTickFormat("%#d %b, %y");//Define a ordem dia, mes, ano q é mostrada na parte baixo do gráfico

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Notas");
        yAxis.setMin(0);
    }

    private BarChartModel preencheGraficoBarras() {//Preenche as informações do gráfico - 
        listaEptIndGrafico = EesIndBean.listarEesIndisPorcategoriaBean(empreendimentoSelecionado, categoriaSelecionada);
        return EesIndBean.addSeriesBarraBean(categoriaSelecionada, listaEptIndGrafico);
    }

    //METODOS de filtragem de atividadesAtividades
    public void filtraAtividadesPorUser() {
        listaAtividades = atividadeBean.listarBean();
        List<AtividadePlanejada> listaAux = new ArrayList();

        if (userSelecionado != null) {
            for (int i = 0; i < listaAtividades.size(); i++) {//Roda todas as atividades
                boolean userIgual = false;
                for (int x = 0; x < listaAtividades.get(i).getUsuarioList().size(); x++) {//Roda users de cada atividade
                    if (listaAtividades.get(i).getUsuarioList().get(x).equals(userSelecionado)) {
                        userIgual = true;
                    }
                }
                if (userIgual == true) {
                    listaAux.add(listaAtividades.get(i));
                }
            }
            listaAtividades = listaAux;
        }
    }

    public void filtraAtividadesPorEes() {
        //Filtra os planos
        List<PlanoAcao> listaPlanoAux = new ArrayList();
        listaPlanos = planoBean.listarBean();
        for (int i = 0; i < listaPlanos.size(); i++) {
            if (listaPlanos.get(i).getEmpreendimento().equals(empreendimentoSelecionado)) {
                listaPlanoAux.add(listaPlanos.get(i));
            }
        }
        listaPlanos = listaPlanoAux;

        //Filtra as atividades
        List<AtividadePlanejada> listaAux = new ArrayList();
        if (empreendimentoSelecionado != null) {
            listaAtividades = atividadeBean.listarBean();
            for (int i = 0; i < listaAtividades.size(); i++) {
                if (listaAtividades.get(i).getMeta().getPlanoAcao().getEmpreendimento().equals(empreendimentoSelecionado)) {
                    listaAux.add(listaAtividades.get(i));
                }
            }
            listaAtividades = listaAux;
        } else {
            listaAtividades = atividadeBean.listarBean();
        }
    }

    public void filtraAtividadesPorPlano() {
        List<AtividadePlanejada> listaAux = new ArrayList();

        if (planoSelecionado != null) {
            for (int i = 0; i < listaAtividades.size(); i++) {
                if (listaAtividades.get(i).getMeta().getPlanoAcao().equals(planoSelecionado)) {
                    listaAux.add(listaAtividades.get(i));
                }
            }
            listaAtividades = listaAux;
        } else {
            listaAtividades = atividadeBean.listarBean();
            filtraAtividadesPorEes();
        }
    }

    //METODOS Indicadores
    public void criaGrafico() {//Configurações do gráfico - Acontece ao selecionar uma categoria da aba de indicadores
        criaGraficoBarras();
        Calendar calendar = Calendar.getInstance();//Pega a data atual
        calendar.add(Calendar.DAY_OF_MONTH, 3);//Adiciona 3 dias - P ficar + bonito no gráfico
        Date dataAtual = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");//Cria o formato q data sera mostrada

        graficoAtividades = preencheGraficoLinhas();
        graficoAtividades.setTitle("Evolução dos indicadores da categoria " + categoriaSelecionada);//Titulo do gráfico
        graficoAtividades.setLegendPosition("e");
        graficoAtividades.setAnimate(true);
        graficoAtividades.getAxis(AxisType.Y).setLabel("Nota");//Texto do eixo Y do gráfico
        DateAxis axis = new DateAxis("Data de modificação do indicador");//Texto do eixo X do gráfico
        axis.setTickAngle(-50);
        System.out.println("DATA ATUAL: " + dateFormat.format(dataAtual));
        axis.setMax(dateFormat.format(dataAtual));//Seta a data máxima para ser mostrada no gráfico
        axis.setTickFormat("%#d, %m, 20%y");//Define a ordem dia, mes, ano q é mostrada na parte baixo do gráfico

        graficoAtividades.getAxes().put(AxisType.X, axis);

        if (listaEptIndGrafico.isEmpty()) {
            msgGrowlErroCustomizavel("", "Não há indicadores preenchidos para essa categoria.");
        } else {
            RequestContext.getCurrentInstance().update("frmMsgGenerico");//Atualiza para retirar a msg anterior
        }
    }

    private LineChartModel preencheGraficoLinhas() {//Preenche as informações do gráfico - É chamado pelo método "Cria Gráfico"
        listaEptIndGrafico = EesIndBean.listarEesIndisPorcategoriaBean(empreendimentoSelecionado, categoriaSelecionada);
        return EesIndBean.preencheGraficoBean(categoriaSelecionada, listaEptIndGrafico);
    }

    public void limpaPainelIndicadores() {//Acontece ao selecionar um empreendimento na lista
        categoriaSelecionada = "";//Limpa a categoria
        if (empreendimentoSelecionado != null) {
            msgGrowlInfoCustomizavel("", "Agora selecione uma categoria de indicadores.");
        } else {
            msgGrowlInfoCustomizavel("", "Selecione um empreendimento.");
        }
    }

    //METODOS PIECHART
    public void criaGraficoPieChatAtiviMetas() {
        graficoAtividadesPlano = new PieChartModel();

        for (int i = 0; i < listaAtividades.size(); i++) {
            graficoAtividadesPlano.set("Brand 1", 10);
        }

        graficoAtividadesPlano.setTitle("Custom Pie");
        graficoAtividadesPlano.setLegendPosition("e");
        graficoAtividadesPlano.setFill(false);
        graficoAtividadesPlano.setShowDataLabels(true);
        graficoAtividadesPlano.setDiameter(150);
    }

    //SETS E GETS
    public Empreendimento getEmpreendimentoSelecionado() {
        return empreendimentoSelecionado;
    }

    public void setEmpreendimentoSelecionado(Empreendimento empreendimentoSelecionado) {
        this.empreendimentoSelecionado = empreendimentoSelecionado;
    }

    public List<Empreendimento> getListaEmpreendimentos() {
        return listaEmpreendimentos;
    }

    public void setListaEmpreendimentos(List<Empreendimento> listaEmpreendimentos) {
        this.listaEmpreendimentos = listaEmpreendimentos;
    }

    public String getCategoriaSelecionada() {
        return categoriaSelecionada;
    }

    public void setCategoriaSelecionada(String categoriaSelecionada) {
        this.categoriaSelecionada = categoriaSelecionada;
    }

    public LineChartModel getGraficoAtividades() {
        return graficoAtividades;
    }

    public List<Indicador> getListaIndicadores() {
        return listaIndicadores;
    }

    public void setListaIndicadores(List<Indicador> listaIndicadores) {
        this.listaIndicadores = listaIndicadores;
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

    public List<Usuario> getListaUser() {
        return listaUser;
    }

    public void setListaUser(List<Usuario> listaUser) {
        this.listaUser = listaUser;
    }

    public Usuario getUserSelecionado() {
        return userSelecionado;
    }

    public void setUserSelecionado(Usuario userSelecionado) {
        this.userSelecionado = userSelecionado;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public PieChartModel getGraficoAtividadesPlano() {
        return graficoAtividadesPlano;
    }
}
