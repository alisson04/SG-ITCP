package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.IncubadoraBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Incubadora;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import br.ifnmg.januaria.fernandes.itcp.util.UploadArquivo;
import java.io.File;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
            //Não a necessidade de verificar a existencia de INC, pois essa pagina só pode ser ascessado quando exite uma
            inc = incBean.listarBean().get(0);

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

    public void setaImagemFundo(FileUploadEvent event) {
        try {
            limpaPasta("/image/imagenFundo");
            arquivo.fileUpload(event.getFile().getContents(), new java.util.Date().getTime() + ".jpg", "/image/imagenFundo/");
            inc.setFotoFundoLogin(arquivo.getNome());
            arquivo.gravar();
            salvarInc();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void setaImagemTopo(FileUploadEvent event) {
        try {
            limpaPasta("/image/imagenTopo");
            System.out.println("METD setaImagemGeral");
            arquivo.fileUpload(event.getFile().getContents(), new java.util.Date().getTime() + ".jpg", "/image/imagenTopo/");
            inc.setFotoTelaGeral(arquivo.getNome());
            arquivo.gravar();
            salvarInc();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void setaImagemTopoRelatorio(FileUploadEvent event) {
        try {
            limpaPasta("/image/imagemTopoRelatorio");
            System.out.println("METD setaImagemGeral");
            arquivo.fileUpload(event.getFile().getContents(), new java.util.Date().getTime() + ".jpg", "/image/imagemTopoRelatorio/");
            inc.setFotoTopoRelatorio(arquivo.getNome());
            arquivo.gravar();
            salvarInc();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void limpaPasta(String caminhoPasta) {//Apaga todos os arquivos de uma pasta dentro do projeto
        try {
            File folder = new File(arquivo.getRealPath() + caminhoPasta);
            if (folder.isDirectory()) {
                System.out.println("É diretorio");
                File[] sun = folder.listFiles();
                for (File toDelete : sun) {
                    System.out.println("RDOU");
                    toDelete.delete();
                }
            } else {
                System.out.println("Não é diretorio");
            }
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
