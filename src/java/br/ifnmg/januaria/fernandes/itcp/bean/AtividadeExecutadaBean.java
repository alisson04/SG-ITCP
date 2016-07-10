package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.AtividadeExecutadaDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadeExecutada;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author alisson
 */
@ManagedBean
@SessionScoped
public class AtividadeExecutadaBean implements Serializable{
    private AtividadeExecutadaDAO dao = new AtividadeExecutadaDAO();
    
    public AtividadeExecutadaBean(){
    }
    
    public void salvarBean(AtividadeExecutada obj) {
        dao.salvarDao(obj);
    }
    
    
    
    public List<AtividadeExecutada> listarBean() {
        return dao.listarDao();
    }
    
    public void excluirBean(AtividadeExecutada obj){
        dao.excluirDao(obj);
    }
}
