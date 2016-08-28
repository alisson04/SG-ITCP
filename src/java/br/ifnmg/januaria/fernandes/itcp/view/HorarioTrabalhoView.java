package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.HorarioTrabalhoBean;
import br.ifnmg.januaria.fernandes.itcp.domain.HorarioTrabalho;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "HorarioTrabalhoView")
@ViewScoped
public class HorarioTrabalhoView extends MensagensGenericas implements Serializable {

    //VARIAVEIS
    private HorarioTrabalho obj;
    private Usuario usrLogado;
    private HorarioTrabalhoBean bean = new HorarioTrabalhoBean();
    private List<HorarioTrabalho> listaHorarios;
    private List<HorarioTrabalho> listaHorariosFiltrados;
    private Date totalHoras = new Date();

    //CONSTRUTOR
    public HorarioTrabalhoView() {
        listaHorarios = bean.listarBean();

        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = (HttpSession) request.getSession();
        usrLogado = (Usuario) session.getAttribute("USUARIOLogado");

        totalHoras.setTime(0);

        obj = bean.buscaHorarioBean(usrLogado);
        System.out.println("ID LOGADO: " + usrLogado.getId());
        if (obj == null) {
            System.out.println("OBJ é NULL");
            obj = new HorarioTrabalho();
            obj.setUsuario(usrLogado);
            obj.setIdUsuarioFk(usrLogado.getId());
        } else {
            verificaHorario();
        }
    }

    //METODOS
    public void salvar() {
        if (verificaHorario()) {
            if (!(totalHoras.getTime() > 201600000)) {
                obj.setHorasSemana((int) (long) totalHoras.getTime());
                obj = bean.salvarBean(obj);
                listaHorarios = bean.listarBean();
                msgGrowSaveGeneric();
            } else {
                msgPanelErro("Erro", "A jornada de trabalho não pode ser maior que 56 horas semanais!");
            }
        } else {
            msgPanelErro("Erro", "Verifique a coerência dos horários e tente novamente!");
        }
    }

    public String geraHoras(HorarioTrabalho ht) {
        if (ht.getHorasSemana() != null) {
            int segundos = (int) Math.floor(ht.getHorasSemana() / 1000);
            int minutos = (segundos / 60);
            int horas = minutos / 60;
            int minutosRestantes = minutos % 60;

            if (minutosRestantes == 0
                    || minutosRestantes == 1
                    || minutosRestantes == 2
                    || minutosRestantes == 3
                    || minutosRestantes == 4
                    || minutosRestantes == 5
                    || minutosRestantes == 6
                    || minutosRestantes == 7
                    || minutosRestantes == 8
                    || minutosRestantes == 9) {
                return (horas + ":0" + minutosRestantes);
            }else{
                return (horas + ":" + minutosRestantes);
            }

        } else {
            return "0";
        }
    }

    public boolean verificaHorario() {
        boolean aux = true;

        //COERENCIA DOS HORÁRIOS
        if (obj.getDomIni() != null && obj.getDomFim() != null) {//AMBOS PREENCHIDOS
            totalHoras.setTime(totalHoras.getTime() + (obj.getDomFim().getTime() - obj.getDomIni().getTime()));
            if (obj.getDomIni().after(obj.getDomFim())) {
                aux = false;
            }
            totalHoras.setTime(obj.getDomFim().getTime() - obj.getDomIni().getTime());
        } else if (obj.getDomIni() == null && obj.getDomFim() == null) {//AMBOS VAZIOS

        } else {
            aux = false;
        }

        if (obj.getSegIni() != null && obj.getSegFim() != null) {
            if (obj.getSegIni().after(obj.getSegFim())) {
                aux = false;
            }
            totalHoras.setTime(totalHoras.getTime() + (obj.getSegFim().getTime() - obj.getSegIni().getTime()));
        } else if (obj.getSegIni() == null && obj.getSegFim() == null) {//AMBOS VAZIOS

        } else {
            aux = false;
        }

        if (obj.getTerIni() != null && obj.getTerFim() != null) {
            if (obj.getTerIni().after(obj.getTerFim())) {
                aux = false;
            }
            totalHoras.setTime(totalHoras.getTime() + (obj.getTerFim().getTime() - obj.getTerIni().getTime()));
        } else if (obj.getTerIni() == null && obj.getTerFim() == null) {//AMBOS VAZIOS

        } else {
            aux = false;
        }

        if (obj.getQuaIni() != null && obj.getQuaFim() != null) {
            if (obj.getQuaIni().after(obj.getQuaFim())) {
                aux = false;
            }
            totalHoras.setTime(totalHoras.getTime() + (obj.getQuaFim().getTime() - obj.getQuaIni().getTime()));
        } else if (obj.getQuaIni() == null && obj.getQuaFim() == null) {//AMBOS VAZIOS

        } else {
            aux = false;
        }

        if (obj.getQuiIni() != null && obj.getQuiFim() != null) {
            if (obj.getQuiIni().after(obj.getQuiFim())) {
                aux = false;
            }
            totalHoras.setTime(totalHoras.getTime() + (obj.getQuiFim().getTime() - obj.getQuiIni().getTime()));
        } else if (obj.getQuiIni() == null && obj.getQuiFim() == null) {//AMBOS VAZIOS

        } else {
            aux = false;
        }

        if (obj.getSexIni() != null && obj.getSexFim() != null) {
            if (obj.getSexIni().after(obj.getSexFim())) {
                aux = false;
            }
            totalHoras.setTime(totalHoras.getTime() + (obj.getSexFim().getTime() - obj.getSexIni().getTime()));
        } else if (obj.getSexIni() == null && obj.getSexFim() == null) {//AMBOS VAZIOS

        } else {
            aux = false;
        }

        if (obj.getSabIni() != null && obj.getSabFim() != null) {
            if (obj.getSabIni().after(obj.getSabFim())) {
                aux = false;
            }
            totalHoras.setTime(totalHoras.getTime() + (obj.getSabFim().getTime() - obj.getSabIni().getTime()));
        } else if (obj.getSabIni() == null && obj.getSabFim() == null) {//AMBOS VAZIOS

        } else {
            aux = false;
        }

        return aux;
    }

    public String conveteData(Date data) {
        if (data != null) {
            SimpleDateFormat forma = new SimpleDateFormat("HH:mm");
            return forma.format(data);
        } else {
            return "";
        }
    }

    /*public String geraHorario(int aux) {
        String auxString = String.valueOf(aux);
        String horarios = "";
        int pos;

        if (auxString != null) {
            pos = auxString.indexOf("1");
            if (pos != -1) {
                horarios = "Manhã";
            }

            pos = auxString.indexOf("2");
            if (pos != -1) {
                horarios += " Tarde";
            }

            pos = auxString.indexOf("3");
            if (pos != -1) {
                horarios += " Noite";
            }
        }

        return horarios;
    }**/
    //SETS E GETS
    public HorarioTrabalho getObj() {
        return obj;
    }

    public void setObj(HorarioTrabalho obj) {
        this.obj = obj;
    }

    public List<HorarioTrabalho> getListaHorarios() {
        return listaHorarios;
    }

    public void setListaHorarios(List<HorarioTrabalho> listaHorarios) {
        this.listaHorarios = listaHorarios;
    }

    public List<HorarioTrabalho> getListaHorariosFiltrados() {
        return listaHorariosFiltrados;
    }

    public void setListaHorariosFiltrados(List<HorarioTrabalho> listaHorariosFiltrados) {
        this.listaHorariosFiltrados = listaHorariosFiltrados;
    }

    public Date getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(Date totalHoras) {
        this.totalHoras = totalHoras;
    }
}
