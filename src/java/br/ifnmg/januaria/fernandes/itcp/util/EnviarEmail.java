package br.ifnmg.januaria.fernandes.itcp.util;

/**
 *
 * @author alisson
 */
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EnviarEmail {

    public EnviarEmail() {

    }

    public void enviarEmail(String enviarPara, String assunto, String mensagem) {
        try {
            Email email = new SimpleEmail();
            email.setDebug(true);
            
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("sistemaincubadora@gmail.com", "sistemaincubadora122"));
            email.setSSLOnConnect(true);
            email.setFrom("sistemaincubadora@gmail.com");
            email.setSubject(assunto);
            email.setMsg(mensagem);
            email.addTo(enviarPara);
            
            System.out.println("Quase");
            email.send();
            
            System.out.println("Enviou");
        } catch (EmailException e) {
            System.out.println("ERRO" + e);
        }
    }
}
