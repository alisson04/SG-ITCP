package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.AcompanhamentoEptBean;
import br.ifnmg.januaria.fernandes.itcp.bean.AtividadePlanejadaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoIndicadorBean;
import br.ifnmg.januaria.fernandes.itcp.bean.HorasTrabalhadasBean;
import br.ifnmg.januaria.fernandes.itcp.bean.NotaMaturidadeBean;
import br.ifnmg.januaria.fernandes.itcp.bean.UsuarioBean;
import br.ifnmg.januaria.fernandes.itcp.domain.AcompanhamentoEpt;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicador;
import br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicadorPK;
import br.ifnmg.januaria.fernandes.itcp.domain.HorasTrabalhadas;
import br.ifnmg.januaria.fernandes.itcp.domain.Indicador;
import br.ifnmg.januaria.fernandes.itcp.domain.NotaMaturidade;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import br.ifnmg.januaria.fernandes.itcp.util.RelatoriosManager;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.commons.codec.binary.Base64;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "RelatoriosGeraisView")
@ViewScoped
public class RelatoriosGeraisView extends MensagensGenericas implements Serializable {

    //Datas de filtragem
    private Date dataFiltroInicio;
    private Date dataFiltroFim;

    //CONSTRUTOR
    public RelatoriosGeraisView() {
        try {
            //Filtro de datas na listagem de atividades
            Calendar x = Calendar.getInstance();//Pega a data atual
            x.add((Calendar.DAY_OF_MONTH), -15);//subtrai 15 dias  a data atual
            dataFiltroInicio = x.getTime();//Seta a data atual
            x.add((Calendar.DAY_OF_MONTH), 30);//soma 15 dias a data atual
            dataFiltroFim = x.getTime();//Seta a data somada
            filtrarAcompaPorData();//Filtra as atividades
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //METODOS
    public void filtrarAcompaPorData() {//Filtra as atividades
        AcompanhamentoEptBean bean = new AcompanhamentoEptBean();
        List<AcompanhamentoEpt> listaEes = bean.listarPorIntervaloBean(dataFiltroInicio, dataFiltroFim);
        
        
    }
    
    public void gerarRelatorioAcompa(String caminho, String nome, String tipo) throws Exception {
        Map<String, Object> listaParametros = new HashMap<String, Object>();
        
        listaParametros.put("dataInicio", dataFiltroInicio);
        listaParametros.put("dataFim", dataFiltroFim);

        RelatoriosManager m = new RelatoriosManager<Empreendimento>();
        m.gerarRelatorioGenericoSemDataSource(listaParametros, caminho,
                nome, tipo);
    }

    public void gerarRelatorioGenerico(String caminho, String nome, String tipo) throws Exception {
        Map<String, Object> listaParametros = new HashMap<String, Object>();

        RelatoriosManager m = new RelatoriosManager<Empreendimento>();
        m.gerarRelatorioGenericoSemDataSource(listaParametros, caminho,
                nome, tipo);
    }

    //SETS GETS
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
}
