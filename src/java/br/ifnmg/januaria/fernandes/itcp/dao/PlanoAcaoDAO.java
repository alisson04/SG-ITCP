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
    
    public PlanoAcao salvarPlano(PlanoAcao planoAcao){
        return salvarGenerico(planoAcao);
    }
    
    public void excluirPlanoDao(PlanoAcao planoAcao){
        excluirGenerico(planoAcao);
    }
    
    public List<PlanoAcao> listarTodosPlanos() {
        return listarObjsGenerico("PlanoAcao");
    }
}
