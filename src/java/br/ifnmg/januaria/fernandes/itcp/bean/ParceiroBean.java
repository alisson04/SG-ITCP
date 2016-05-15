package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.ParceiroDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Parceiro;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author alisson
 */
@ManagedBean
@SessionScoped
public class ParceiroBean implements Serializable{
    public void salvarParceiroBd(Parceiro parceiroSalvar) {
        ParceiroDAO parceiroDAO = new ParceiroDAO();
        parceiroDAO.salvarEpt(parceiroSalvar);
    }
}
