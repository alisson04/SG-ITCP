package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import java.util.List;

/**
 *
 * @author alisson
 */
public class EmpreendimentoDAO extends DaoGenerico<Empreendimento> {

    EntityManagerCriador emc;
    
    public EmpreendimentoDAO() {
    }
    
    public void salvarEpt(Empreendimento ept){
        salvarGenerico(ept);
    }
    
    public void excluirEptDao(Empreendimento ept){
        excluirGenerico(ept);
    }
    
    public List<Empreendimento> listarTodosEptsDao() {
        return listarObjsGenerico("Empreendimento");
    }
}
