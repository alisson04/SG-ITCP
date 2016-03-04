/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifnmg.januaria.fernandes.itcp.util;

import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author alisson
 */
public class FacesUtil {

    public static String getParam(String nome) {
        FacesContext facesContext = FacesContext.getCurrentInstance();//Captura o contexto da aplicaço

        ExternalContext externalContext = facesContext.getExternalContext();//Captura o contexto do navegador

        Map<String, String> parametros = externalContext.getRequestParameterMap();//Captura os paramentros
        //Primera String é o nome e o segundo é o valor
        //o mapa pega todos os prametros que forem mandados

        String valor = parametros.get(nome);

        return valor;
    }
}
