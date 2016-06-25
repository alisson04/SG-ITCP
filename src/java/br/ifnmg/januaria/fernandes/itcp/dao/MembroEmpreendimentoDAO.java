package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.MembroEmpreendimento;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author alisson
 */
public class MembroEmpreendimentoDAO extends DaoGenerico<MembroEmpreendimento>{
        
    public void salvarMembroEpt(MembroEmpreendimento membroEpt){
        salvarGenerico(membroEpt);
    } 
    
    public List<MembroEmpreendimento> listarTodosMembrosEmpreendimentos() {
        return listarObjsGenerico("MembroEmpreendimento");
    }
    
    public void excluirMembroDao(MembroEmpreendimento membro){
        excluirGenerico(membro);
    }
}
