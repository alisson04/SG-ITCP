package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.AcompanhamentoEptDAO;
import br.ifnmg.januaria.fernandes.itcp.dao.EventoLogDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.AcompanhamentoEpt;
import br.ifnmg.januaria.fernandes.itcp.domain.EventoLog;
import br.ifnmg.januaria.fernandes.itcp.util.RelatoriosManager;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author alisson
 */
@ManagedBean
@SessionScoped
public class EventoLogBean implements Serializable{
    private EventoLogDAO dao = new EventoLogDAO();
    
    public EventoLogBean(){
    }
    
    public EventoLog salvarBean(EventoLog obj) {
        return dao.salvarDao(obj);
    }
    
    //lista as ativiades por um intervalo de datas
    public List<EventoLog> listarPorIntervaloBean(Date dataIni, Date dataFim) {
        return dao.listarPorIntervaloDao(dataIni, dataFim);
    }
    
    public void excluirBean(EventoLog obj){
        dao.excluirDao(obj);
    }
    
    public void gerarRelatorio(Date dataIni, Date dataFim, List<String> listaDatas, String tipo) throws Exception {
        Map<String, Object> listaParametros = new HashMap<String, Object>();

        //Filtro de datas
        listaParametros.put("paramDataIni", dataIni);
        listaParametros.put("paramDataFim", dataFim);
        
        //Lista de datas
        listaParametros.put("listaDatas", listaDatas);

        RelatoriosManager m = new RelatoriosManager<AcompanhamentoEpt>();
        m.gerarRelatorioGenericoSemDataSource(listaParametros, "/iReport/relatorioEventosLog.jasper",
                "Relatorio-de-acompanhamentos.pdf", tipo);
    }
}
