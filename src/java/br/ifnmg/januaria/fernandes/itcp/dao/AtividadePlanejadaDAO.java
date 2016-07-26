package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import java.util.List;


/**
 *
 * @author alisson
 */
public class AtividadePlanejadaDAO extends DaoGenerico<AtividadePlanejada>{
    public AtividadePlanejada salvarDao(AtividadePlanejada obj){
        return salvarGenerico(obj);
    }
    
    public List<AtividadePlanejada> listarDao() {
        return listarObjsGenerico("AtividadePlanejada");
    }
    
    public void excluirDao(AtividadePlanejada obj){
        excluirGenerico(obj);
    }
}
