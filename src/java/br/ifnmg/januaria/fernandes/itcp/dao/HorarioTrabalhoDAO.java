package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.HorarioTrabalho;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import java.util.List;

/**
 *
 * @author alisson
 */
public class HorarioTrabalhoDAO extends DaoGenerico<HorarioTrabalho> {
    EntityManagerCriador emc;
    
    public HorarioTrabalhoDAO() {
    }
    
    public HorarioTrabalho salvarHorario(HorarioTrabalho obj){
        return salvarGenerico(obj);
    }
    
    public List<HorarioTrabalho> listarTodosHorarios() {
        return listarObjsGenerico("HorarioTrabalho");
    }
    
    public HorarioTrabalho buscaHorarioDao(Usuario usr){
        List<HorarioTrabalho> listaHorarios;
        HorarioTrabalho horarioAux;
        
        listaHorarios = listarObjsFiltradosIntGenerico("HorarioTrabalho", "usuario.id", usr.getId());
        if (listaHorarios.size() > 0) {
            horarioAux = listaHorarios.get(0);
            System.out.println("__________HorarioTrabalhoDAO: número de horarios para este user: " + listaHorarios.size());
        } else {
            horarioAux = null;
            System.out.println("__________HorarioTrabalhoDAO: Nenhum horário cadastrado para esse user");
        }
        
        return horarioAux;
    }
}
