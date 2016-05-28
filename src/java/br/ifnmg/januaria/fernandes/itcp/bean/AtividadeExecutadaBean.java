package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.AtividadeExecutadaDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadeExecutada;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author alisson
 */
@ManagedBean
@SessionScoped
public class AtividadeExecutadaBean implements Serializable{
    public void salvarAtividadeBd(AtividadeExecutada atividade) {
        AtividadeExecutadaDAO dao = new AtividadeExecutadaDAO();
        dao.salvarEpt(atividade);
    }
}
