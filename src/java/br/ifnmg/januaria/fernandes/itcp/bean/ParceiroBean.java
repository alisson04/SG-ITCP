package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.ParceiroDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Parceiro;
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
public class ParceiroBean implements Serializable{
    
    private ParceiroDAO dao;
    
    public ParceiroBean(){
        dao = new ParceiroDAO();
    }
    
    public void salvarBean(Parceiro parceiroSalvar) {
        dao.salvarEpt(parceiroSalvar);
    }
    
    public List<Parceiro> listarBean() {
        return dao.listarTodosParceiros();
    }
    
    public void excluirBean(Parceiro parceiro){
        dao.excluirParceiroDao(parceiro);
    }
}