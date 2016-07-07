package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.Meta;
import java.util.List;

/**
 *
 * @author alisson
 */
public class MetaDAO extends DaoGenerico<Meta> {
    EntityManagerCriador emc;
    
    public MetaDAO() {
    }
    
    public void salvarDao(Meta obj){
        salvarGenerico(obj);
    }
    
    public List<Meta> listarDao() {
        return listarObjsGenerico("Meta");
    }
    
    public void excluirDao(Meta obj){
        excluirGenerico(obj);
    }
}
