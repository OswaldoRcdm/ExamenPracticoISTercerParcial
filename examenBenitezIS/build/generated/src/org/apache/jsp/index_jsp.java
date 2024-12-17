package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import crud.logica;
import java.util.List;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"es\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <title>Registro de Equipos Electrónicos</title>\n");
      out.write("    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <div class=\"container mt-5\">\n");
      out.write("        <h1 class=\"text-center mb-4\">Registro de Equipos Electrónicos</h1>\n");
      out.write("        \n");
      out.write("        ");

        logica Logic = new logica();
        
        // Procesar formulario si hay datos
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            String accion = request.getParameter("accion");
            
            if ("agregar".equals(accion)) {
                String nombre = request.getParameter("nombre");
                String marca = request.getParameter("marca");
                double precio = Double.parseDouble(request.getParameter("precio"));
                
                boolean resultado = Logic.agregarEquipo(nombre, marca, precio);
                
                if (resultado) {
                    out.println("<div class='alert alert-success'>Producto agregado exitosamente</div>");
                } else {
                    out.println("<div class='alert alert-danger'>Error al agregar el producto</div>");
                }
            } else if ("eliminar".equals(accion)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Logic.eliminarEquipo(id);
            }
        }
        
        // Obtener lista de equipos
        List<logica.Equipo> equipos = Logic.obtenerEquipos();
        
      out.write("\n");
      out.write("        \n");
      out.write("        <div class=\"row\">\n");
      out.write("            <div class=\"col-md-4\">\n");
      out.write("                <div class=\"card\">\n");
      out.write("                    <div class=\"card-body\">\n");
      out.write("                        <h5 class=\"card-title\">Agregar Equipo</h5>\n");
      out.write("                        <form method=\"post\">\n");
      out.write("                            <input type=\"hidden\" name=\"accion\" value=\"agregar\">\n");
      out.write("                            <div class=\"mb-3\">\n");
      out.write("                                <label class=\"form-label\">Nombre</label>\n");
      out.write("                                <input type=\"text\" class=\"form-control\" name=\"nombre\" required>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"mb-3\">\n");
      out.write("                                <label class=\"form-label\">Marca</label>\n");
      out.write("                                <input type=\"text\" class=\"form-control\" name=\"marca\" required>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"mb-3\">\n");
      out.write("                                <label class=\"form-label\">Precio</label>\n");
      out.write("                                <input type=\"number\" class=\"form-control\" name=\"precio\" step=\"0.01\" required>\n");
      out.write("                            </div>\n");
      out.write("                            <button type=\"submit\" class=\"btn btn-primary\">Agregar</button>\n");
      out.write("                        </form>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <div class=\"col-md-8\">\n");
      out.write("                <table class=\"table table-striped\">\n");
      out.write("                    <thead>\n");
      out.write("                        <tr>\n");
      out.write("                            <th>ID</th>\n");
      out.write("                            <th>Nombre</th>\n");
      out.write("                            <th>Marca</th>\n");
      out.write("                            <th>Precio</th>\n");
      out.write("                            <th>Acciones</th>\n");
      out.write("                        </tr>\n");
      out.write("                    </thead>\n");
      out.write("                    <tbody>\n");
      out.write("                        ");
 for (logica.Equipo equipo : equipos) { 
      out.write("\n");
      out.write("                        <tr>\n");
      out.write("                            <td>");
      out.print( equipo.getId() );
      out.write("</td>\n");
      out.write("                            <td>");
      out.print( equipo.getNombre() );
      out.write("</td>\n");
      out.write("                            <td>");
      out.print( equipo.getMarca() );
      out.write("</td>\n");
      out.write("                            <td>$");
      out.print( String.format("%.2f", equipo.getPrecio()) );
      out.write("</td>\n");
      out.write("                            <td>\n");
      out.write("                                <form method=\"post\" class=\"d-inline\">\n");
      out.write("                                    <input type=\"hidden\" name=\"accion\" value=\"eliminar\">\n");
      out.write("                                    <input type=\"hidden\" name=\"id\" value=\"");
      out.print( equipo.getId() );
      out.write("\">\n");
      out.write("                                    <button type=\"submit\" class=\"btn btn-danger btn-sm\">Eliminar</button>\n");
      out.write("                                </form>\n");
      out.write("                            </td>\n");
      out.write("                        </tr>\n");
      out.write("                        ");
 } 
      out.write("\n");
      out.write("                    </tbody>\n");
      out.write("                </table>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js\"></script>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
