package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.Meta;
import br.ifnmg.januaria.fernandes.itcp.domain.PlanoAcao;
import java.util.List;

/**
 *
 * @author alisson
 */
public class MetaDAO extends DaoGenerico<Meta> {
    EntityManagerCriador emc;
    
    public MetaDAO() {
    }
    
    public Meta salvarDao(Meta obj){
        return salvarGenerico(obj);
    }
    
    public List<Meta> listarDao() {
        return listarObjsGenerico("Meta");
    }
    
    public void excluirDao(Meta obj){
        excluirGenerico(obj);
    }
    
    public List<Meta> buscarMetasPorPlanoDao(PlanoAcao obj) {
        return listarObjsFiltradosIntGenerico("Meta", "planoAcao.id", obj.getId());
    }
}
