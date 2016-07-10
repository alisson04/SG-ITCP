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

    MembroEmpreendimentoDAO dao = new MembroEmpreendimentoDAO();
    List<MembroEmpreendimento> listaMembroEmpreendimentos;
    
    
    public MembroEmpreendimentoBean() {
    }
    
    public void salvarBean(MembroEmpreendimento membroEpt){
        dao.salvarDao(membroEpt);
    }
    
    public void excluirBean(MembroEmpreendimento membroEpt){
        dao.excluirDao(membroEpt);
    }
    
    public List<MembroEmpreendimento> listarBean() {
        System.out.println("BEAN(listarTodosMembrosEmpreendimentos): listarTodosMembrosEmpreendimentos: ");
        listaMembroEmpreendimentos = dao.listarDao();
        return listaMembroEmpreendimentos;
    }
}
