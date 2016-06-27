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
    
    public void salvarMeta(Meta metaSalvar){
        salvarGenerico(metaSalvar);
    }
    
    public List<Meta> listarTodasMetas() {
        return listarObjsGenerico("Meta");
    }
    
    public void excluirMetaDao(Meta meta){
        excluirGenerico(meta);
    }
}
