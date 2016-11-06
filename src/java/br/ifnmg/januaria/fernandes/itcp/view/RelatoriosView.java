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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

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
    private LineChartModel lineModel1;
    private String categoriaSelecionada;//Para receber o valor que representa a categoria selecionada
    private List<EmpreendimentoIndicador> listaEptIndGrafico;

    //EmpreendimentoIndicador Vars
    private EmpreendimentoIndicadorBean EesIndBean;

    //Indicador Vars
    private GerenciadorIndicadores gerenIndicadores;//Para gerar os indicadores
    private List<Indicador> listaIndicadores;//Para guardar os indicadores que serão usados na tela

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
    }

    //METODOS
    public void criaGrafico() {//Configurações do gráfico - Acontece ao selecionar uma categoria
        Calendar calendar = Calendar.getInstance();//Pega a data atual
        calendar.add(Calendar.DAY_OF_MONTH, 3);//Adiciona 3 dias - P ficar + bonito no gráfico
        Date dataAtual = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");//Cria o formato q data sera mostrada

        lineModel1 = preencheGrafico();
        lineModel1.setTitle("Evolução dos indicadores por categoria");//Titulo do gráfico
        lineModel1.setLegendPosition("e");
        lineModel1.setAnimate(true);

        lineModel1.getAxis(AxisType.Y).setLabel("Nota");//Texto do eixo Y do gráfico
        DateAxis axis = new DateAxis("Data de modificação do indicador");//Texto do eixo X do gráfico
        axis.setTickAngle(-50);
        System.out.println("DATA ATUAL: " + dateFormat.format(dataAtual));
        axis.setMax(dateFormat.format(dataAtual));//Seta a data máxima para ser mostrada no gráfico
        axis.setTickFormat("%#d %b, %y");//Define a ordem dia, mes, ano q é mostrada na parte baixo do gráfico

        lineModel1.getAxes().put(AxisType.X, axis);

        if (listaEptIndGrafico.isEmpty()) {
            msgGrowlErroCustomizavel("", "Não há indicadores preenchidos para essa categoria.");
        } else {
            RequestContext.getCurrentInstance().update("frmMsgGenerico");//Atualiza para retirar a msg anterior
        }
    }

    private LineChartModel preencheGrafico() {//Preenche as informações do gráfico - É chamado pelo método "Cria Gráfico"
        listaEptIndGrafico = EesIndBean.listarEesIndisPorcategoriaBean(empreendimentoSelecionado, categoriaSelecionada);
        return EesIndBean.preencheGraficoBean(categoriaSelecionada, listaEptIndGrafico);
    }

    public void liberaPainelIndicadores() {//Acontece ao selecionar um empreendimento na lista
        categoriaSelecionada = "";//Limpa a categoria
        msgGrowlInfoCustomizavel("", "Agora selecione uma categoria de indicadores.");
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

    public LineChartModel getLineModel1() {
        return lineModel1;
    }

    public List<Indicador> getListaIndicadores() {
        return listaIndicadores;
    }

    public void setListaIndicadores(List<Indicador> listaIndicadores) {
        this.listaIndicadores = listaIndicadores;
    }
}
