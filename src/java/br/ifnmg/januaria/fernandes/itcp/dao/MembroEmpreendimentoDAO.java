package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.MembroEmpreendimento;
import java.util.List;

/**
 *
 * @author alisson
 */
public class MembroEmpreendimentoDAO extends DaoGenerico<MembroEmpreendimento>{
        
    public void salvarDao(MembroEmpreendimento membroEpt){
        salvarGenerico(membroEpt);
    } 
    
    public List<MembroEmpreendimento> listarDao() {
        return listarObjsGenerico("MembroEmpreendimento");
    }
    
    public void excluirDao(MembroEmpreendimento membro){
        excluirGenerico(membro);
    }
}
