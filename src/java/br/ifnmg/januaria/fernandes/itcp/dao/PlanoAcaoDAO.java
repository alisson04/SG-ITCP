package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.PlanoAcao;
import java.util.List;

/**
 *
 * @author alisson
 */
public class PlanoAcaoDAO extends DaoGenerico<PlanoAcao>{
    EntityManagerCriador emc;
    
    public PlanoAcaoDAO() {
    }
    
    public void salvarPlano(PlanoAcao planoAcao){
        salvarGenerico(planoAcao);
    }
    
    public List<PlanoAcao> listarTodosPlanos() {
        return listarObjsGenerico("PlanoAcao");
    }
}
