package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.IncubadoraDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Incubadora;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author alisson
 */
@SessionScoped
@Named
public class IncubadoraBean implements Serializable{
    private IncubadoraDAO dao;
    
    public IncubadoraBean(){
        dao = new IncubadoraDAO();
    }
    
    public Incubadora salvarBean(Incubadora obj){
        return dao.salvarDao(obj);
    }
    
    public void excluirBean(Incubadora obj){
        dao.excluirDao(obj);
    }
    
    public List<Incubadora> listarBean() {
        return dao.listarDao();
    }
    
    public long contarLinhasBean(){
        return dao.contarLinhasDAO();
    }
}
