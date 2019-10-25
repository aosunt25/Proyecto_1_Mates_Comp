import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.Vector;

import javax.servlet.annotation.WebServlet;

@WebServlet("/AltaNutriologo")
public class AltaNutriologo extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response){

		try{
			String base = getServletContext().getInitParameter("base");
			String contraseñaBase=getServletContext().getInitParameter("contraseña");
			String usuarioBase=getServletContext().getInitParameter("usuario");

			Class.forName("com.mysql.jdbc.Driver");

			//Despues de localhost va el nombre de la base

			String url = "jdbc:mysql://localhost/"+base+"?useSSL=false&allowPublicKeyRetrieval=true";
			
			//System.out.println(url);

			//Parametros son direccion de la base, usuario y constraseña de mysql
			Connection con = DriverManager.getConnection(url,usuarioBase,contraseñaBase);
			String nombre = request.getParameter("nombre"); 
			String apellido = request.getParameter("apellidos");
			String usuario = request.getParameter("usuario");
			String contraseña= request.getParameter("password");
			String email = request.getParameter("email");
			int telefono = Integer.parseInt(request.getParameter("telefono"));
			String domicilioCons = request.getParameter("domicilioCons");
			String domicilioPos = request.getParameter("domicilioPos");
			String cedula = request.getParameter("cedula");
			

			Statement stat = con.createStatement();
			String sql = "INSERT INTO Nutriologo (nombre, apellido , contraseña, usuario, telefono, cedula, domicilioCons, domicilioPos, email) VALUES ( '"+nombre+"','"+apellido+"','"+contraseña+"','"+usuario+"','"+telefono+"','"+cedula+"','"+domicilioCons+"','"+domicilioPos+"', '"+email+"')";
			

			stat.executeUpdate(sql);

			stat.close();
			con.close();

			//preguntare
			//request.setAttribute("alumnos",alumnos);

			RequestDispatcher disp =  getServletContext().getRequestDispatcher("/index.html");

			if(disp!=null){
				disp.forward(request,response);
			}


		}
		catch(Exception e){
			e.printStackTrace();
		}



	}

}
