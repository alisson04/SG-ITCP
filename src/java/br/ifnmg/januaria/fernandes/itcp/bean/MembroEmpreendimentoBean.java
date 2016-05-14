package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.MembroEmpreendimentoDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.MembroEmpreendimento;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author alisson
 */
@ManagedBean
@SessionScoped
public class MembroEmpreendimentoBean implements Serializable{

    public MembroEmpreendimentoBean() {
        
    }
    
    public void salvarMembroEpt(MembroEmpreendimento membroEpt){
        MembroEmpreendimentoDAO membroEmpreendimentoDAO= new MembroEmpreendimentoDAO();
        membroEmpreendimentoDAO.salvarMembroEpt(membroEpt);
    }
}
