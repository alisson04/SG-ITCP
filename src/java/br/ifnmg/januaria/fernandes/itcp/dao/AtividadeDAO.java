package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.Atividade;
import java.util.List;


/**
 *
 * @author alisson
 */
public class AtividadeDAO extends DaoGenerico<Atividade>{
    public void TestaAtividade(){
        Atividade at = new Atividade();
        salvarGenerico(at);
    }
    
    public void salvarEpt(Atividade atividadeSalvar){
        salvarGenerico(atividadeSalvar);
    }
    
    public List<Atividade> listarTodosParceiros() {
        return listarObjsGenerico("Atividade");
    }
}
