package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoIndicadorBean;
import br.ifnmg.januaria.fernandes.itcp.bean.NotaMaturidadeBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicador;
import br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicadorPK;
import br.ifnmg.januaria.fernandes.itcp.domain.Indicador;
import br.ifnmg.januaria.fernandes.itcp.domain.NotaMaturidade;
import br.ifnmg.januaria.fernandes.itcp.util.GerenciadorIndicadores;
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
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "RelatorioIndicadoresMaturidadeView")
@ViewScoped
public class RelatorioIndicadoresMaturidadeView extends MensagensGenericas implements Serializable {

    //Tamanho dos gráficos Var
    private int tamanhoGraficos;

    //Empreendimento Vars
    private Empreendimento empreendimentoSelecionado;
    private List<Empreendimento> listaEmpreendimentos;
    private EmpreendimentoBean eptBean;

    //EmpreendimentoIndicador Vars
    private EmpreendimentoIndicadorBean bean;

    //Indicador Vars
    private GerenciadorIndicadores gerenIndicadores;//Para gerar os indicadores
    private List<Indicador> listaIndicadores;//Para guardar os indicadores que serão usados na tela
    private List<LineChartModel> listaGraficoInds;//Lista de gráficos dos inds

    //Gráfico Inds
    List<Indicador> listaTodosInds;
    List<Indicador> listaIndsSelecionados;//Lista de inds que terão gráficos

    //Gráfico Vars
    private String categoriaSelecionada;//Para receber o valor que representa a categoria selecionada

    //Variáveis nota de maturidade
    private NotaMaturidadeBean notaBean;
    private List<NotaMaturidade> listaNotas;

    //CheckBox Indicadores
    private boolean selecionaTodosInds;//Variavel que define que todos os indicadores do check box serão selecionados

    private List<EmpreendimentoIndicador> listaEptIndGrafico;

    //CONSTRUTOR
    public RelatorioIndicadoresMaturidadeView() {
        try {
            bean = new EmpreendimentoIndicadorBean();

            selecionaTodosInds = false;

            //Tamanho dos gráficos Var
            tamanhoGraficos = 2;//2 significa que é tamanho médio o inicial

            //Indicador
            gerenIndicadores = new GerenciadorIndicadores();
            listaIndicadores = gerenIndicadores.listarIndicadores();

            //Gráfico Inds
            listaIndsSelecionados = new ArrayList();
            listaTodosInds = gerenIndicadores.listarIndicadores();//Para ser filtrada na tela - precisa ser exclusiva
            
            //Gráfico
            listaEptIndGrafico = new ArrayList();
            categoriaSelecionada = "";//Necessário, não retirar

            eptBean = new EmpreendimentoBean();
            listaEmpreendimentos = eptBean.listarBean();

            //Notas de maturidade
            notaBean = new NotaMaturidadeBean();
            listaNotas = notaBean.listarUltimasNotasBean(listaEmpreendimentos);
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //METODOS
    public void selecionaEptTela() {
        selecionaTodosInds = false;//Diz que nenhum ind estaselecionados
    }

    //Metodos tamanho dos gráficos
    public void configuraTamanhoGrafico(int tamanho) {
        if (tamanho == 1) {
            tamanhoGraficos = 1;
        } else if (tamanho == 2) {
            tamanhoGraficos = 2;
        } else {
            tamanhoGraficos = 3;
        }
    }

    public void selecionaTodosIndsView() {
        listaIndsSelecionados = listaTodosInds;
        criaGraficoInd();
    }

    // Metodos de tela
    public void selecionaCategoriaTela() {
        //Limpa vars
        listaGraficoInds = new ArrayList();//Limpa a lista de gráficos
        listaIndicadores = gerenIndicadores.listarIndicadores();//Pega todos os inds
        listaIndsSelecionados = new ArrayList();//Limpa os inds selecionados
        selecionaTodosInds=false;//Diz que todos os inds não estão selecionados
        
        //Filtra
        filtraIndsPorCategoria();
    }
    public void filtraIndsPorCategoria() {
        listaTodosInds = gerenIndicadores.listarIndicadoresPorCategoria(categoriaSelecionada);
    }

    //Cria uma lista com os gráficos dos inds selecionados
    public void criaGraficoInd() {
        try {
            if (listaTodosInds.size() == listaIndsSelecionados.size()) {
                selecionaTodosInds = true;
            } else {
                selecionaTodosInds = false;
            }
            Calendar calendar = Calendar.getInstance();//Pega a data atual
            calendar.add(Calendar.DAY_OF_MONTH, 3);//Adiciona 3 dias - P ficar + bonito no gráfico
            Date dataAtual = calendar.getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");//Cria o formato q data sera mostrada

            listaGraficoInds = preencheGraficoInd();//Preenche a lista com os gráficos dos inds

            for (int i = 0; i < listaGraficoInds.size(); i++) {
                LineChartModel graficoAux = listaGraficoInds.get(i);
                graficoAux.setLegendPosition("e");
                graficoAux.setAnimate(true);

                graficoAux.getAxis(AxisType.Y).setLabel("Nota");//Texto do eixo Y do gráfico
                DateAxis axis = new DateAxis("");//Texto do eixo X do gráfico
                axis.setTickAngle(-50);
                System.out.println("DATA ATUAL: " + dateFormat.format(dataAtual));
                axis.setMax(dateFormat.format(dataAtual));//Seta a data máxima para ser mostrada no gráfico
                axis.setTickFormat("%#d, %m, 20%y");//Define a ordem dia, mes, ano q é mostrada na parte baixo do gráfico

                graficoAux.getAxes().put(AxisType.X, axis);

                listaGraficoInds.set(i, graficoAux);
                System.out.println("QUANTI: " + listaIndsSelecionados.size());
            }

        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //Preenche uma lista com os gráficos dos inds selecionados
    private List<LineChartModel> preencheGraficoInd() {//Preenche as informações do gráfico - É chamado pelo método "Cria Gráfico"
        try {
            System.out.println("QUANTI: " + listaIndsSelecionados.size());
            listaEptIndGrafico = bean.listarEesIndisPorcategoriaBean(empreendimentoSelecionado, categoriaSelecionada);
            return bean.preencheGraficoIndBean(listaIndsSelecionados, listaEptIndGrafico);
        } catch (Exception ex) {
            throw new FacesException(ex);
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

    public String getCategoriaSelecionada() {
        return categoriaSelecionada;
    }

    public void setCategoriaSelecionada(String categoriaSelecionada) {
        this.categoriaSelecionada = categoriaSelecionada;
    }

    public List<Indicador> getListaIndicadores() {
        return listaIndicadores;
    }

    public void setListaIndicadores(List<Indicador> listaIndicadores) {
        this.listaIndicadores = listaIndicadores;
    }

    public List<Indicador> getListaIndsSelecionados() {
        return listaIndsSelecionados;
    }

    public void setListaIndsSelecionados(List<Indicador> listaIndsSelecionados) {
        this.listaIndsSelecionados = listaIndsSelecionados;
    }

    public List<Indicador> getListaTodosInds() {
        return listaTodosInds;
    }

    public void setListaTodosInds(List<Indicador> listaTodosInds) {
        this.listaTodosInds = listaTodosInds;
    }

    public List<NotaMaturidade> getListaNotas() {
        return listaNotas;
    }

    public void setListaNotas(List<NotaMaturidade> listaNotas) {
        this.listaNotas = listaNotas;
    }

    public List<LineChartModel> getListaGraficoInds() {
        return listaGraficoInds;
    }

    public void setListaGraficoInds(List<LineChartModel> listaGraficoInds) {
        this.listaGraficoInds = listaGraficoInds;
    }

    public int getTamanhoGraficos() {
        return tamanhoGraficos;
    }

    public void setTamanhoGraficos(int tamanhoGraficos) {
        this.tamanhoGraficos = tamanhoGraficos;
    }

    public boolean isSelecionaTodosInds() {
        return selecionaTodosInds;
    }

    public void setSelecionaTodosInds(boolean selecionaTodosInds) {
        this.selecionaTodosInds = selecionaTodosInds;
    }

    public List<EmpreendimentoIndicador> getListaEptIndGrafico() {
        return listaEptIndGrafico;
    }

    public void setListaEptIndGrafico(List<EmpreendimentoIndicador> listaEptIndGrafico) {
        this.listaEptIndGrafico = listaEptIndGrafico;
    }
}
