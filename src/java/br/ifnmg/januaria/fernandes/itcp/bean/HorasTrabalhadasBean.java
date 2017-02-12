package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.HorasTrabalhadasDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.AcompanhamentoEpt;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.HorasTrabalhadas;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.RelatoriosManager;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
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
public class HorasTrabalhadasBean implements Serializable{
    private HorasTrabalhadasDAO dao = new HorasTrabalhadasDAO();
    
    public HorasTrabalhadasBean(){
    }
    
    public HorasTrabalhadas salvarBean(HorasTrabalhadas obj) {
        return dao.salvarDao(obj);
    }
    
    public List<HorasTrabalhadas> listarBean() {
        return dao.listarDao();
    }
    
    public void excluirBean(HorasTrabalhadas obj){
        dao.excluirDao(obj);
    }
    
    public List<HorasTrabalhadas> listarPorUserAtividadeBean(Usuario user, AtividadePlanejada atv){
        return dao.listarPorUserAtividadeDao(user, atv);
    }
    
    public List<HorasTrabalhadas> listarPorAtividadeBean(AtividadePlanejada atv){
        return dao.listarPorAtividadeDao(atv);
    }
    
    public List<HorasTrabalhadas> listarPorEesBean(Empreendimento ees){//O ees é encontrado pela atividade
        return dao.listarPorEesDao(ees);
    }
    
    public void gerarRelatorio(List<Empreendimento> lista, List<String> listaHoras, List<String> listaUsers,
            List<InputStream> listaTeste, String tipo) throws Exception {
        Map<String, Object> listaParametros = new HashMap<String, Object>();
        
        //List<InputStream> listaTeste = new ArrayList<>();
        //for(int i=0; i<lista.size(); i++){
        //    listaTeste.add(asd);
        //    System.out.println("ADD: " + i);
        //}
        listaParametros.put("listaTeste", listaTeste);
        //listaParametros.put("teste", asd);
        listaParametros.put("listaHoras", listaHoras);
        listaParametros.put("listaUsers", listaUsers);
        
        RelatoriosManager m = new RelatoriosManager<Empreendimento>();
        m.gerarRelatorioGenerico(lista, listaParametros, "/iReport/relatorioNotaMaturidadeHorasTrabalhadas.jasper", 
                "Relatorio-de-Evolucao-da-nota-de-maturidade-e-acumulo-de-horas-trabalhadas.pdf", tipo);
    }
}
