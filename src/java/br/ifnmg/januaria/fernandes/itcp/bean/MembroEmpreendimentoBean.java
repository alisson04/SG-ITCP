package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.MembroEmpreendimentoDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.MembroEmpreendimento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author alisson
 */
@ManagedBean
@SessionScoped
public class MembroEmpreendimentoBean implements Serializable{

    MembroEmpreendimentoDAO membroEmpreendimentoDAO= new MembroEmpreendimentoDAO();
    List<MembroEmpreendimento> listaMembroEmpreendimentos;
    
    
    public MembroEmpreendimentoBean() {
    }
    
    public void salvarMembroEpt(MembroEmpreendimento membroEpt){
        membroEmpreendimentoDAO.salvarMembroEpt(membroEpt);
    }
    
    public List<MembroEmpreendimento> listarTodosMembrosEpts() {
        System.out.println("BEAN(listarTodosMembrosEmpreendimentos): listarTodosMembrosEmpreendimentos: ");
        listaMembroEmpreendimentos = membroEmpreendimentoDAO.listarTodosMembrosEmpreendimentos();
        return listaMembroEmpreendimentos;
    }
}
