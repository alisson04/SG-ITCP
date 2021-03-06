/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifnmg.januaria.fernandes.itcp.util;

import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alisson
 */
public class SessionUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    public static HttpSession getSession() {
        System.out.println("GetSession=============");
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession sessao = (HttpSession) ctx.getExternalContext().getSession(false);
        return sessao;
    }

    public List<String> addUsr(int size) {
        System.out.println("AddUser============");
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            list.add("a");
        }
        return list;
    }

    public static void setParam(String key, Usuario value) {
        getSession().setAttribute(key, value);
    }

    public static Object getParam(String key) {
        return getSession().getAttribute(key);
    }

    public static void remove(String key) {
        System.out.println("Remove================");
        getSession().removeAttribute(key);
    }

    public static void invalidate() {
        getSession().invalidate();
    }

}
