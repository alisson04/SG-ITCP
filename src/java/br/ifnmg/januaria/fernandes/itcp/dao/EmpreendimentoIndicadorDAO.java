package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicador;
import java.util.List;

/**
 *
 * @author alisson
 */
public class EmpreendimentoIndicadorDAO extends DaoGenerico<EmpreendimentoIndicador> {
    
    public EmpreendimentoIndicadorDAO() {
    }
    
    public EmpreendimentoIndicador salvarDAO(EmpreendimentoIndicador ip){
        return salvarGenerico(ip);
    }
    
    public void excluirDao(EmpreendimentoIndicador ip){
        excluirGenerico(ip);
    }
    
    public List<EmpreendimentoIndicador> listarTodosDAO() {
        return listarObjsGenerico("EmpreendimentoIndicador");
    }
    
    public long contarLinhasDAO(){
        return contarLinhasGenerico("EmpreendimentoIndicador");
    }
}
