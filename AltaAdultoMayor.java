import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.Vector;


import javax.servlet.annotation.WebServlet;

@WebServlet("/AltaAdultoMayor")
public class AltaAdultoMayor extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response){

		try{
			System.out.println("HOLA");
			String base = getServletContext().getInitParameter("base");
			String contraseñaBase=getServletContext().getInitParameter("contraseña");
			String usuarioBase=getServletContext().getInitParameter("usuario");

			Class.forName("com.mysql.jdbc.Driver");

			//Despues de localhost va el nombre de la base

			String url = "jdbc:mysql://localhost/"+base+"?useSSL=false&allowPublicKeyRetrieval=true";
			
			//System.out.println(url);

			//Parametros son direccion de la base, usuario y constraseña de mysql
			Connection con = DriverManager.getConnection(url,usuarioBase,contraseñaBase);
			String nombre = request.getParameter("Nombre");
			String apellido = request.getParameter("Apellidos");
			String usuario = request.getParameter("Usuario");
			String contraseña= request.getParameter("password");
			String email = request.getParameter("email");
			int telefono = Integer.parseInt(request.getParameter("telefono"));
			String sexo = request.getParameter("sexo");
			String fechaDeNacimiento = request.getParameter("fechaDeNacimiento");
			String socioEconomico = request.getParameter("socioEconomico");
			String escolaridad = request.getParameter("escolaridad");
				

			Statement stat = con.createStatement();

			String sql = "INSERT INTO AdultoMayor (nombre, apellido , contraseña, usuario, telefono, sexo, socioEconomico, escolaridad, email,fechaDeNacimiento) VALUES ( '"+nombre+"','"+apellido+"','"+contraseña+"','"+usuario+"','"+telefono+"','"+sexo+"','"+socioEconomico+"','"+escolaridad+"','"+email+"', '"+fechaDeNacimiento+"')";
			
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
