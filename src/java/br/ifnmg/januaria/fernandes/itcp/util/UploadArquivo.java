package br.ifnmg.januaria.fernandes.itcp.util;

import java.io.File;
import java.io.FileOutputStream;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.FileUploadEvent;

public class UploadArquivo {

    private String diretorio;
    private String caminho;
    private byte[] arquivo;
    private String nome;

    public UploadArquivo() {
    }

    public String getNome() {
        return nome;
    }

    public String getRealPath() {
        ExternalContext externalContext
                = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletResponse response
                = (HttpServletResponse) externalContext.getResponse();

        FacesContext aFacesContext = FacesContext.getCurrentInstance();
        ServletContext context
                = (ServletContext) aFacesContext.getExternalContext().getContext();

        return context.getRealPath("/");
    }

    public void fileUpload(byte byteArray[], String nome, String diretorio) {//Para fotos do sistema
        try {this.nome = nome;//Deve conter o tipo
            this.caminho = getRealPath() + diretorio + getNome();
            this.arquivo = byteArray;

            File file = new File(getRealPath() + diretorio);
            file.mkdirs();
        } catch (Exception ex) {
            System.out.println("Erro no upload do arquivo" + ex);
        }
    }

    public void gravar() {

        try {
            FileOutputStream fos;
            fos = new FileOutputStream(this.caminho);
            fos.write(this.arquivo);
            fos.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}