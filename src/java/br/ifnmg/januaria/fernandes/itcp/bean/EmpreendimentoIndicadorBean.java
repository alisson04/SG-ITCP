package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.EmpreendimentoIndicadorDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicador;
import br.ifnmg.januaria.fernandes.itcp.domain.Indicador;
import br.ifnmg.januaria.fernandes.itcp.util.GerenciadorIndicadores;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    //Adiciona as séries de um gráfico de barra e o retorna
    public BarChartModel addSeriesBarraBean(String categoriaSelecionada, List<EmpreendimentoIndicador> listaEptIndGrafico) {
        //MES DIA ANO é a sequencia que as datas devem ser setadas
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");//Cria o formato q data sera mostrada

        List<Indicador> listaIndicadoresCategoria;
        listaIndicadoresCategoria = listarIndicadoresPorCategoriaBean(categoriaSelecionada);

        BarChartModel model = new BarChartModel();
        /*ChartSeries series1;
        
        for (int i = 0; i < listaIndicadoresCategoria.size(); i++) {//Roda a quantidade de indicadores da categoria
            series1 = new ChartSeries();
            series1.setLabel(listaIndicadoresCategoria.get(i).getNome());//Seta o nome da série como o nome do indicador
            int verificador = 0;
            for (int x = 0; x < listaEptIndGrafico.size(); x++) {
                if (listaIndicadoresCategoria.get(i).getId().equals(
                        listaEptIndGrafico.get(x).getEmpreendimentoIndicadorPK().getIdIndicador())) {
                    
                    System.out.println("DATA: " + dateFormat.format(listaEptIndGrafico.get(x).getEmpreendimentoIndicadorPK().getDataNota()));
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
        }*/
        

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("11/13/2004", 12);
        boys.set("11/13/2005", 10);

        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("11/13/2004", 5);
        girls.set("11/13/2005", 6);
        girls.set("11/13/2006", 10);
        girls.set("11/13/2007", 15);
        girls.set("11/13/2008", 10);
        model.addSeries(boys);
        model.addSeries(girls);

        return model;
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

    //Retorna os EmprendimentoIndicadores por uma categoria
    public List<EmpreendimentoIndicador> listarEesIndisPorcategoriaBean(Empreendimento ees, String categoria) {
        return dao.listarEesIndisPorcategoriaDAO(ees, listarIndicadoresPorCategoriaBean(categoria));
    }

    public List<Indicador> listarIndicadoresPorCategoriaBean(String categoria) {//Retorna os indicadores por uma categoria
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

    public List<EmpreendimentoIndicador> buscarListaPorCodigoBean(Empreendimento obj) {
        return dao.buscarListaPorCodigo(obj);
    }
}
