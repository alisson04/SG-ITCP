package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.AcompanhamentoEptDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.AcompanhamentoEpt;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.RelatoriosManager;
import java.io.Serializable;
import java.util.ArrayList;
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
public class AcompanhamentoEptBean implements Serializable{
    private AcompanhamentoEptDAO dao = new AcompanhamentoEptDAO();
    
    public AcompanhamentoEptBean(){
    }
    
    public AcompanhamentoEpt salvarBean(AcompanhamentoEpt obj) {
        return dao.salvarDao(obj);
    }
    
    //lista as ativiades por um intervalo de datas
    public List<AcompanhamentoEpt> listarPorIntervaloBean(Date dataIni, Date dataFim) {
        return dao.listarPorIntervaloDao(dataIni, dataFim);
    }
    
    public List<AcompanhamentoEpt> listarBean() {
        return dao.listarDao();
    }
    
    public void excluirBean(AcompanhamentoEpt obj){
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
        m.gerarRelatorioGenericoSemDataSource(listaParametros, "/iReport/relatorioAcompanhamentos.jasper",
                "Relatorio-de-acompanhamentos.pdf", tipo);
    }
}
