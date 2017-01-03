package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.NotaMaturidadeDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.NotaMaturidade;
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
public class NotaMaturidadeBean implements Serializable {

    private NotaMaturidadeDAO dao;
    
    public NotaMaturidadeBean() {
        dao = new NotaMaturidadeDAO();
    }

    public NotaMaturidade salvarBean(NotaMaturidade obj) {
        return dao.salvarEpt(obj);
    }
    
    public List<NotaMaturidade> listarUltimasNotasBean(List<Empreendimento> listaEpt) {
        return dao.listarUltimasNotasDao(listaEpt);
    }
    
    public List<NotaMaturidade> listarNotasPorEesBean(Empreendimento ees) {
        return dao.listarNotasPorEesDao(ees);
    }
}
