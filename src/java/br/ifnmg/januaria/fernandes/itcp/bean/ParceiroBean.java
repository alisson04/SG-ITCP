package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.ParceiroDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Parceiro;
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
public class ParceiroBean implements Serializable{
    
    private ParceiroDAO parceiroDAO;
    
    public ParceiroBean(){
        parceiroDAO = new ParceiroDAO();
    }
    
    public void salvarParceiroBd(Parceiro parceiroSalvar) {
        parceiroDAO.salvarEpt(parceiroSalvar);
    }
    
    public List<Parceiro> listarTodosParceiros() {
        return parceiroDAO.listarTodosParceiros();
    }
}