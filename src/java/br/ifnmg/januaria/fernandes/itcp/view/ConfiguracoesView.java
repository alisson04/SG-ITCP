package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.IncubadoraBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Incubadora;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import br.ifnmg.januaria.fernandes.itcp.util.UploadArquivo;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "ConfiguracoesView")
@ViewScoped
public class ConfiguracoesView extends MensagensGenericas implements Serializable {

    //Incubadora VARS
    private Incubadora inc;
    private IncubadoraBean incBean;
    private UploadArquivo arquivo;

    //CONSTRUTOR
    @PostConstruct
    public void init() {
        try {
            //Incubadora CONSTRU
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

            arquivo = new UploadArquivo();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //METODOS
    public String geraMsgGenericaCampoObrigatorioView() {
        try {
            return msgGenericaCampoObrigatorio();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //METODOS INC
    public void salvarInc() {
        try {
            inc = incBean.salvarBean(inc);
            msgGrowSaveGeneric();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void setaImagemLogin(FileUploadEvent event) {
        try {
            arquivo.fileUpload(event, ".jpg", "/image/");
            inc.setFotoFundoLogin(arquivo.getNome());
            arquivo.gravar();
            salvarInc();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void setaImagemGeral(FileUploadEvent event) {
        try {
            System.out.println("METD setaImagemGeral");
            arquivo.fileUpload(event, ".jpg", "/image/");
            inc.setFotoTelaGeral(arquivo.getNome());
            arquivo.gravar();
            salvarInc();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //SETS E GETS
    public Incubadora getInc() {
        return inc;
    }

    public void setInc(Incubadora inc) {
        this.inc = inc;
    }
}
