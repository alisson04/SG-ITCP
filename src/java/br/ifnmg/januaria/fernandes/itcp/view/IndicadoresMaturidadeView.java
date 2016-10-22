package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoIndicadorBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicador;
import br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicadorPK;
import br.ifnmg.januaria.fernandes.itcp.domain.Indicador;
import br.ifnmg.januaria.fernandes.itcp.util.GerenciadorIndicadores;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "IndicadoresMaturidadeView")
@ViewScoped
public class IndicadoresMaturidadeView extends MensagensGenericas implements Serializable {

    //Empreendimento Vars
    private Empreendimento empreendimentoSelecionado;
    private List<Empreendimento> listaEmpreendimentos;
    private List<Empreendimento> listaEmpreendimentosFiltrados;
    private EmpreendimentoBean eptBean;

    //EmpreendimentoIndicador Vars
    private List<EmpreendimentoIndicador> listaEptIndSalvar;
    private EmpreendimentoIndicadorBean bean;

    //Indicador Vars
    private GerenciadorIndicadores gerenIndicadores;//Para gerar os indicadores
    private List<Indicador> listaIndicadores;//Para guardar os indicadores que serão usados na tela

    //Gráfico Vars
    private LineChartModel lineModel1;

    //Construtor
    public IndicadoresMaturidadeView() {
        listaEptIndSalvar = new ArrayList();
        eptBean = new EmpreendimentoBean();
        bean = new EmpreendimentoIndicadorBean();
        listaEmpreendimentos = eptBean.listarBean();

        //Indicador
        gerenIndicadores = new GerenciadorIndicadores();
        listaIndicadores = gerenIndicadores.listarIndicadores();

        //Gráfico
    }

    //METODOS
    public LineChartModel getLineModel1() {
        return lineModel1;
    }

    private void createLineModels() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");//Cria o formato q data sera mostrada
        Date dataAtual = new Date();//Cria uma data atual referente a do sistema

        lineModel1 = initLinearModel();
        lineModel1.setTitle("Evolução dos indicadores por categoria");//Titulo do gráfico
        lineModel1.setLegendPosition("e");

        lineModel1.getAxis(AxisType.Y).setLabel("Nota");//Texto do eixo Y do gráfico
        DateAxis axis = new DateAxis("Data de modificação do indicador");//Texto do eixo X do gráfico
        axis.setTickAngle(-50);
        System.out.println("DATA ATUAL: " + dateFormat.format(dataAtual));
        axis.setMax(dateFormat.format(dataAtual));//Seta a data máxima para ser mostrada no gráfico
        axis.setTickFormat("%#d %b, %y");//Define a ordem dia, mes, ano q é mostrada na parte baixo do gráfico

        lineModel1.getAxes().put(AxisType.X, axis);

    }

    private LineChartModel initLinearModel() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");//Cria o formato q data sera mostrada
        Date dataAtual = new Date();//Cria uma data atual referente a do sistema
        System.out.println("DATA ATUAL: " + dateFormat.format(dataAtual));
        LineChartModel model = new LineChartModel();

        //MES DIA ANO é a sequencia que as datas devem ser setadas
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");
        series1.set("01-10-2016", 51);
        series1.set("10-11-2016", 32);

        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");
        series2.set("10-15-2016", 51);
        series2.set("10-16-2016", 32);

        model.addSeries(series1);
        model.addSeries(series2);

        return model;
    }

    public void liberaPainelIndicadores() {//Acontece ao selecionar um empreendimento na lista
        Date x = new Date();
        listaEptIndSalvar = bean.buscarListaPorCodigoBean(empreendimentoSelecionado);//Pega os inds para o ESS selecionado

        System.out.println("===============================");
        System.out.println("Tamanho é " + listaEptIndSalvar.size());
        for (int i = listaEptIndSalvar.size(); i < listaIndicadores.size(); i++) {//Roda se ouver algum ind não preenchido
            EmpreendimentoIndicador indAux = new EmpreendimentoIndicador();
            EmpreendimentoIndicadorPK EptIndPK = new EmpreendimentoIndicadorPK();

            //indAux.setNota(0);
            EptIndPK.setDataNota(x);//SETA a data
            EptIndPK.setIdEmpreendimentoFk(empreendimentoSelecionado.getId());//SETA o empreendimento
            EptIndPK.setIdIndicador(i + 1);//SETA o indicador

            indAux.setEmpreendimentoIndicadorPK(EptIndPK);//SETA as chaves
            listaEptIndSalvar.add(indAux);//Adiciona o obj a lista p ser usado na tela e talvez salvo
        }

        createLineModels();//Chama o método para preencher o gráfico de indicadores
    }

    public void adicionaNota(int posicaoIndi) {
        EmpreendimentoIndicador eptIndAux;//Cria este para guardar a nota
        EmpreendimentoIndicadorPK EptIndPK;//Criar esta para receber a data nova
        Date x = new Date();//Cria a nova data

        EptIndPK = listaEptIndSalvar.get(posicaoIndi).getEmpreendimentoIndicadorPK();//SETA a chave
        eptIndAux = listaEptIndSalvar.get(posicaoIndi);
        EptIndPK.setDataNota(x);//SETA a data atual
        eptIndAux.setEmpreendimentoIndicadorPK(EptIndPK);
        listaEptIndSalvar.set(posicaoIndi, eptIndAux);//Coloca na lista com informações atualizadas
    }

    public void salvarView() {
        try {
            bean.salvarBean(listaEptIndSalvar);
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('wVarEditarDialog').hide()");//fecha o panel de "formulario de indicadores"
            msgGrowSaveGeneric();
        } catch (Exception ex) {
            Logger.getLogger(ListarPlanoAcaoView.class.getName()).log(Level.SEVERE, null, ex);
            msgPanelErroInesperadoGeneric();
        }
    }

    public String conveteData(Date data) {
        if (data != null) {
            SimpleDateFormat forma = new SimpleDateFormat("dd/MM/yyyy");
            return forma.format(data);
        } else {
            return "";
        }
    }

    //SETS GETS
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

    public List<Empreendimento> getListaEmpreendimentosFiltrados() {
        return listaEmpreendimentosFiltrados;
    }

    public void setListaEmpreendimentosFiltrados(List<Empreendimento> listaEmpreendimentosFiltrados) {
        this.listaEmpreendimentosFiltrados = listaEmpreendimentosFiltrados;
    }

    public List<Indicador> getListaIndicadores() {
        return listaIndicadores;
    }

    public void setListaIndicadores(List<Indicador> listaIndicadores) {
        this.listaIndicadores = listaIndicadores;
    }

    public List<EmpreendimentoIndicador> getListaEptIndSalvar() {
        return listaEptIndSalvar;
    }

    public void setListaEptIndSalvar(List<EmpreendimentoIndicador> listaEptIndSalvar) {
        this.listaEptIndSalvar = listaEptIndSalvar;
    }
}
