/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-03-01 16:09:06 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"html\">\n");
      out.write("<link rel=\"stylesheet\" href=\"style.css\">\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<div id=\"info\" onclick=\"newPosition()\">\n");
      out.write("    <div class=\"container-name\">\n");
      out.write("        <div class=\"div1\">startX</div>\n");
      out.write("        <div class=\"div2\"><INPUT TYPE=\"NUMBER\" Id=\"startX\" onchange=\"newPosition()\" MIN=\"-200\" MAX=\"200\" STEP=\"5\"\n");
      out.write("                                 VALUE=\"0\" SIZE=\"6\"></div>\n");
      out.write("        <div class=\"div1\">startY</div>\n");
      out.write("        <div class=\"div2\"><INPUT TYPE=\"NUMBER\" Id=\"startY\" onchange=\"newPosition()\" MIN=\"1\" MAX=\"200\" STEP=\"5\"\n");
      out.write("                                 VALUE=\"70\" SIZE=\"6\"></div>\n");
      out.write("        <div class=\"div1\">startZ</div>\n");
      out.write("        <div class=\"div2\"><INPUT TYPE=\"NUMBER\" Id=\"startZ\" onchange=\"newPosition()\" MIN=\"-200\" MAX=\"200\" STEP=\"5\"\n");
      out.write("                                 VALUE=\"0\" SIZE=\"6\"></div>\n");
      out.write("        <div>\n");
      out.write("            <button type=\"button\" iD=\"btn\" onclick=\"start()\">Start!</button>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    <div class=\"container-name\">\n");
      out.write("        <div class=\"div1\">startVX</div>\n");
      out.write("        <div class=\"div2\"><INPUT TYPE=\"NUMBER\" Id=\"startVX\" MIN=\"-100\" MAX=\"100\" STEP=\"0.5\" VALUE=\"20\" SIZE=\"6\"></div>\n");
      out.write("        <div class=\"div1\">startVY</div>\n");
      out.write("        <div class=\"div2\"><INPUT TYPE=\"NUMBER\" Id=\"startVY\" MIN=\"1\" MAX=\"100\" STEP=\"0.5\" VALUE=\"20\" SIZE=\"6\"></div>\n");
      out.write("        <div class=\"div1\">startVZ</div>\n");
      out.write("        <div class=\"div2\"><INPUT TYPE=\"NUMBER\" Id=\"startVZ\" MIN=\"-100\" MAX=\"100\" STEP=\"0.5\" VALUE=\"20\" SIZE=\"6\"></div>\n");
      out.write("        <div class=\"div1\">gravity</div>\n");
      out.write("        <div class=\"div2\"><INPUT TYPE=\"NUMBER\" Id=\"gravity\" MIN=\"-50\" MAX=\"50\" STEP=\"0.5\" VALUE=\"10\" SIZE=\"6\"></div>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div id=\"image\"></div>\n");
      out.write("\n");
      out.write("<script src=\"three.js\"></script>\n");
      out.write("<script src=\"controls/OrbitControls.js\"></script>\n");
      out.write("<script src=\"controls/TransformControls.js\"></script>\n");
      out.write("<script src=\"script.js\"></script>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}