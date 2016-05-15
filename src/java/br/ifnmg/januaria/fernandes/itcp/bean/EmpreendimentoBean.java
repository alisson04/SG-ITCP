package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.EmpreendimentoDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author alisson
 */
@ManagedBean
@SessionScoped
public class EmpreendimentoBean implements Serializable {

    public EmpreendimentoBean() {
    }

    public void salvarEptBd(Empreendimento ept) {
        EmpreendimentoDAO empreendimentoDAO = new EmpreendimentoDAO();
        empreendimentoDAO.salvarEpt(ept);
    }
}
