package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.AcompanhamentoEpt;
import java.util.List;


/**
 *
 * @author alisson
 */
public class AcompanhamentoEptDAO extends DaoGenerico<AcompanhamentoEpt>{
    public AcompanhamentoEpt salvarDao(AcompanhamentoEpt obj){
        return salvarGenerico(obj);
    }
    
    public List<AcompanhamentoEpt> listarDao() {
        return listarObjsGenerico("AcompanhamentoEpt");
    }
    
    public void excluirDao(AcompanhamentoEpt obj){
        excluirGenerico(obj);
    }
}