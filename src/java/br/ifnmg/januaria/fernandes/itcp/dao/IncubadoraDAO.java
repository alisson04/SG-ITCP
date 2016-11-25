package br.ifnmg.januaria.fernandes.itcp.dao;
import br.ifnmg.januaria.fernandes.itcp.domain.Incubadora;
import java.util.List;


/**
 *
 * @author alisson
 */
public class IncubadoraDAO extends DaoGenerico<Incubadora>{
    public Incubadora salvarDao(Incubadora obj){
        return salvarGenerico(obj);
    }
    
    public List<Incubadora> listarDao() {
        return listarObjsGenerico("Incubadora");
    }
    
    public void excluirDao(Incubadora obj){
        excluirGenerico(obj);
    }
    
    public long contarLinhasDAO(){
        return contarLinhasGenerico("Incubadora");
    }
}