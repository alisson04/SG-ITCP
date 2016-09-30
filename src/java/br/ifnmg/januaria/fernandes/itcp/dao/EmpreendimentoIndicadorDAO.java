package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicador;
import java.util.List;

/**
 *
 * @author alisson
 */
public class EmpreendimentoIndicadorDAO extends DaoGenerico<EmpreendimentoIndicador> {
    
    public EmpreendimentoIndicadorDAO() {
    }
    
    public void salvarListaDAO(List<EmpreendimentoIndicador> listaEptInd){//Savar lista de objetos
        salvarListaGenerico(listaEptInd);
    }
    
    public void excluirDao(EmpreendimentoIndicador ip){//Excluir objeto
        excluirGenerico(ip);
    }
    
    public List<EmpreendimentoIndicador> listarTodosDAO() {//Listar todos os objetos
        return listarObjsGenerico("EmpreendimentoIndicador");
    }
    
    public List<EmpreendimentoIndicador> buscarListaPorCodigo(Empreendimento obj) {//Busca uma lista por código
        return listarObjsFiltradosIntGenerico("EmpreendimentoIndicador", "empreendimento.id", obj.getId());
        
    }
    
    public long contarLinhasDAO(){
        return contarLinhasGenerico("EmpreendimentoIndicador");
    }
}
