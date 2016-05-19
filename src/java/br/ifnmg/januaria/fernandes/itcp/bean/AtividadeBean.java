package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.AtividadeDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Atividade;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author alisson
 */
@ManagedBean
@SessionScoped
public class AtividadeBean implements Serializable{
    public void salvarAtividadeBd(Atividade atividade) {
        AtividadeDAO dao = new AtividadeDAO();
        dao.salvarEpt(atividade);
    }
}
