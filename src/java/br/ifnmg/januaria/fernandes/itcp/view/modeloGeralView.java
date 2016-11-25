package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.IncubadoraBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Incubadora;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "modeloGeralView")
@ViewScoped
public class modeloGeralView extends MensagensGenericas implements Serializable {
    //Incubadora VARS
    private Incubadora inc;
    private IncubadoraBean incBean;

    //CONSTRUTOR
    @PostConstruct
    public void init() {
        //Incubadora CONTRU
        incBean = new IncubadoraBean();

        if (incBean.contarLinhasBean() != 0) {//Caso existe INC cadastrada
            inc = incBean.listarBean().get(0);
            System.out.println("cadastrada: " + inc.getId());
            if (inc.getFotoTelaGeral() == null) {
                inc.setFotoTelaGeral("fotoTopoGeral.jpg");
            }
        } else {
            inc = new Incubadora();
            inc.setFotoTelaGeral("fotoTopoGeral.jpg");
            System.out.println("Não incubadora cadastrada");
        }
    }

    //METODOS INC
    //SETS E GETS
    public Incubadora getInc() {
        return inc;
    }

    public void setInc(Incubadora inc) {
        this.inc = inc;
    }
}
