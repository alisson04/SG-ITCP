/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifnmg.januaria.fernandes.itcp.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author alisson
 */
@ManagedBean
@ViewScoped
public class MensagensBean {

    public void mensagemInformacaoBarra(String titulo, String texo) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, texo));
    }

    public void mensagemAdvertenciaBarra(String titulo, String texo) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, texo));
    }

    public void mensagemErroBarra(String titulo, String texo) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, texo));
    }

    public void mensagemMortalBarra(String titulo, String texo) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, texo));
    }

    public void msgGrowlInfo(String titulo, String texo) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, texo);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void messagemCaixa(String tipo, String titulo, String msg) {
        FacesMessage message;
        if (tipo.equals("INFO")) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, msg);
            //RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else if (tipo.equals("ERROR")) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, msg);
            //RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else if (tipo.equals("FATAL")) {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, msg);
            //RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else if (tipo.equals("WARN")) {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, msg);
            //RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro na exbição de mensagens", "Contate o Administrador do sistema");
        }
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
}