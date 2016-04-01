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
        System.out.println(parceiroSalvar.getNomeParceiro());
        System.out.println(parceiroSalvar.getEmailParceiro());
        System.out.println(parceiroSalvar.getEnderecoParceiro());
        System.out.println(parceiroSalvar.getTelefoneAlternativoParceiro());
        System.out.println(parceiroSalvar.getTelefoneParceiro());
        System.out.println(parceiroSalvar.getTipoParceiro());
        
        System.out.println("__________BEAN(salvarEptBd): INÍCIO");
        ParceiroDAO parceiroDAO = new ParceiroDAO();
        parceiroDAO.salvarEpt(parceiroSalvar);
    }
}
