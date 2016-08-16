package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.VisitaEpt;
import java.util.List;


/**
 *
 * @author alisson
 */
public class VisitaEptDAO extends DaoGenerico<VisitaEpt>{
    public VisitaEpt salvarDao(VisitaEpt obj){
        return salvarGenerico(obj);
    }
    
    public List<VisitaEpt> listarDao() {
        return listarObjsGenerico("AtividadePlanejada");
    }
    
    public void excluirDao(VisitaEpt obj){
        excluirGenerico(obj);
    }
}