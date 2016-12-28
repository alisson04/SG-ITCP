package br.ifnmg.januaria.fernandes.itcp.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author alisson
 * @param <TipoClasse>
 */
public class RelatoriosManager<TipoClasse> {

    private HttpServletResponse response;
    private FacesContext context;
    private ByteArrayOutputStream baos;
    private InputStream stream;

    //Construtor
    public RelatoriosManager() {
        context = FacesContext.getCurrentInstance();
        response = (HttpServletResponse) context.getExternalContext().getResponse();
    }
    
    public void gerarRelatorioGenerico(List<TipoClasse> lista, String caminhoRelatorio, String nomeRelatorio) throws Exception {
            stream = this.getClass().getResourceAsStream(caminhoRelatorio);
            Map<String, Object> params = new HashMap<String, Object>();
            baos = new ByteArrayOutputStream();

            JasperReport report = (JasperReport) JRLoader.loadObject(stream);
            JasperPrint print = JasperFillManager.fillReport(report, params, new JRBeanCollectionDataSource(lista));
            JasperExportManager.exportReportToPdfStream(print, baos);

            response.reset();
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());
            response.setHeader("Content-disposition", "inline; filename="+nomeRelatorio);//attachment para download
            response.getOutputStream().write(baos.toByteArray());
            response.getOutputStream().flush();
            response.getOutputStream().close();
            context .responseComplete();
    }
}
