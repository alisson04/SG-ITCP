package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.VisitaEptDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.VisitaEpt;
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
public class VisitaEptBean implements Serializable{
    private VisitaEptDAO dao = new VisitaEptDAO();
    
    public VisitaEptBean(){
    }
    
    public VisitaEpt salvarBean(VisitaEpt obj) {
        return dao.salvarDao(obj);
    }
    
    public List<VisitaEpt> listarBean() {
        return dao.listarDao();
    }
    
    public void excluirBean(VisitaEpt obj){
        dao.excluirDao(obj);
    }
}
