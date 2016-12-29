package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.EmpreendimentoIndicadorDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicador;
import br.ifnmg.januaria.fernandes.itcp.domain.Indicador;
import br.ifnmg.januaria.fernandes.itcp.domain.NotaMaturidade;
import br.ifnmg.januaria.fernandes.itcp.util.GerenciadorIndicadores;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author alisson
 */
@ManagedBean
@SessionScoped
public class EmpreendimentoIndicadorBean implements Serializable {

    private EmpreendimentoIndicadorDAO dao;
    private GerenciadorIndicadores gerenIndicadores;

    public EmpreendimentoIndicadorBean() {
        dao = new EmpreendimentoIndicadorDAO();
        gerenIndicadores = new GerenciadorIndicadores();
    }

    public List<LineChartModel> preencheGraficoIndBean(List<Indicador> listaIndsSelecionados,
            List<EmpreendimentoIndicador> listaEptIndGrafico) {
        //MES DIA ANO é a sequencia que as datas devem ser setadas
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");//Cria o formato q data sera mostrada

        List<LineChartModel> modelList = new ArrayList();
        LineChartSeries series1;

        for (int i = 0; i < listaIndsSelecionados.size(); i++) {//Roda a quantidade de indicadores da categoria
            LineChartModel model = new LineChartModel();
            model.setTitle("Evolução do indicador: " + listaIndsSelecionados.get(i).getNome());//Titulo do gráfico
            series1 = new LineChartSeries();
            series1.setLabel(listaIndsSelecionados.get(i).getNome());//Seta o nome da série como o nome do indicador
            int verificador = 0;
            for (int x = 0; x < listaEptIndGrafico.size(); x++) {
                if (listaIndsSelecionados.get(i).getId().equals(
                        listaEptIndGrafico.get(x).getEmpreendimentoIndicadorPK().getIdIndicador())) {
                    series1.set(dateFormat.format(listaEptIndGrafico.get(x).getEmpreendimentoIndicadorPK().getDataNota()),
                            listaEptIndGrafico.get(x).getNota());
                    verificador = 1;
                }
            }

            if (verificador != 0) {
                model.addSeries(series1);
                modelList.add(model);
                System.out.println("Adicionou o indicador " + listaIndsSelecionados.get(i).getNome() + " ao gráfico!");
            } else {
                System.out.println("Não adicionou o indicador " + listaIndsSelecionados.get(i).getNome() + " ao gráfico!");
            }
        }
        System.out.println("QUANTI: " + listaIndsSelecionados.size());
        return modelList;
    }

    //Adiciona as séries de um gráfico de linhas e o retorna
    public LineChartModel preencheGraficoBean(String categoriaSelecionada, List<EmpreendimentoIndicador> listaEptIndGrafico) {
        //MES DIA ANO é a sequencia que as datas devem ser setadas
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");//Cria o formato q data sera mostrada

        List<Indicador> listaIndicadoresCategoria;
        listaIndicadoresCategoria = listarIndicadoresPorCategoriaBean(categoriaSelecionada);

        LineChartModel model = new LineChartModel();
        LineChartSeries series1;

        for (int i = 0; i < listaIndicadoresCategoria.size(); i++) {//Roda a quantidade de indicadores da categoria
            series1 = new LineChartSeries();
            series1.setLabel(listaIndicadoresCategoria.get(i).getNome());//Seta o nome da série como o nome do indicador
            int verificador = 0;
            for (int x = 0; x < listaEptIndGrafico.size(); x++) {
                if (listaIndicadoresCategoria.get(i).getId().equals(
                        listaEptIndGrafico.get(x).getEmpreendimentoIndicadorPK().getIdIndicador())) {
                    series1.set(dateFormat.format(listaEptIndGrafico.get(x).getEmpreendimentoIndicadorPK().getDataNota()),
                            listaEptIndGrafico.get(x).getNota());
                    verificador = 1;
                }
            }

            if (verificador != 0) {
                model.addSeries(series1);
                System.out.println("Adicionou o indicador " + listaIndicadoresCategoria.get(i).getNome() + " ao gráfico!");
            } else {
                System.out.println("Não adicionou o indicador " + listaIndicadoresCategoria.get(i).getNome() + " ao gráfico!");
            }
        }

        return model;
    }

    public void salvarBean(List<EmpreendimentoIndicador> listaEptInd) {
        List<EmpreendimentoIndicador> listaRetiradoNulls;
        listaRetiradoNulls = new ArrayList();

        //Lembresse q uma nova lista é necessária pois utilizar a lista q será salva como parametro p o FOR acarretará erro
        for (int i = 0; i < listaEptInd.size(); i++) {
            if (listaEptInd.get(i).getNota() != null) {
                listaRetiradoNulls.add(listaEptInd.get(i));
            }
        }

        if (!listaRetiradoNulls.isEmpty()) {
            dao.salvarListaDAO(listaRetiradoNulls);
        } else {
            System.out.println("Não salvou nada");
        }
    }

    //Gera e salva a nota de maturidade
    public void gerarNotaMaturidade(Empreendimento ept) {
        List<EmpreendimentoIndicador> listaInds = listaLastIndsPorEptBean(ept);
        for (int i = 0; i < listaInds.size(); i++) {//
            System.out.println("VIEW: " + listaInds.get(i).getEmpreendimentoIndicadorPK().getIdIndicador());
        }
        
        NotaMaturidade notaSalvar = new NotaMaturidade();

        Calendar calendar = Calendar.getInstance();//Pega a data atual
        Date dataAtual = calendar.getTime();

        int notaMaxima = 0;
        int nota = 0;

        for (int i = 0; i < listaInds.size(); i++) {
            if (listaInds.get(i) != null) {
                notaMaxima = notaMaxima + (
                        (gerenIndicadores.geraIndPorId
                        (listaInds.get(i).getEmpreendimentoIndicadorPK().getIdIndicador()))
                                .getNotaMax());

                nota += listaInds.get(i).getNota();
                System.out.println("MAX: " + notaMaxima + " Nota: " + nota);
            } else {
                System.out.println("RRO");
            }

        }
        notaSalvar.setEmpreendimento(ept);
        notaSalvar.setNota(nota);
        notaSalvar.setNotaMaxima(notaMaxima);
        notaSalvar.setDataNota(dataAtual);

        NotaMaturidadeBean notaBean = new NotaMaturidadeBean();
        notaBean.salvarBean(notaSalvar);
    }

    public List<EmpreendimentoIndicador> listaLastIndsPorEptBean(Empreendimento obj) {
        return dao.listaLastIndsPorEptDAO(obj);
    }

    //Retorna os EmprendimentoIndicadores por uma categoria
    public List<EmpreendimentoIndicador> listarEesIndisPorcategoriaBean(Empreendimento ees, String categoria) {
        return dao.listarEesIndisPorcategoriaDAO(ees, listarIndicadoresPorCategoriaBean(categoria));
    }

    //Retorna os indicadores por uma categoria
    public List<Indicador> listarIndicadoresPorCategoriaBean(String categoria) {
        return gerenIndicadores.listarIndicadoresPorCategoria(categoria);
    }

    public void excluirBean(EmpreendimentoIndicador ept) {
        dao.excluirDao(ept);
    }

    public List<EmpreendimentoIndicador> listarBean() {
        return dao.listarTodosDAO();
    }

    public long contarLinhasBean() {
        return dao.contarLinhasDAO();
    }
}
