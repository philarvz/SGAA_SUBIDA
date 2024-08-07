package mx.edu.utez.sgaa.servlet.Docente;

import mx.edu.utez.sgaa.dao.DaoEstudianteAsesoria;
import mx.edu.utez.sgaa.model.EstudiantesAsesoria;
import mx.edu.utez.sgaa.database.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "AgregarAsesoriaS", value = "/AgregarAsesoriaS")
public class AgregarAsesoriaS extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Aquí puedes redirigir a una página de error o una vista específica si es necesario.
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method is not supported.");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idEstudiante = Integer.parseInt(request.getParameter("idEstudiante"));
        int idAsesoria = Integer.parseInt(request.getParameter("idAsesoria"));

        EstudiantesAsesoria estudiantesAsesoria = new EstudiantesAsesoria(idEstudiante, idAsesoria);

        try (Connection connection = DatabaseConnection.getConnection()) {
            DaoEstudianteAsesoria daoEstudianteAsesoria = new DaoEstudianteAsesoria(connection);
            daoEstudianteAsesoria.agregarAsesoriaEstudiante(estudiantesAsesoria);
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write("Asesoría agregada con éxito");
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error al agregar la asesoría");
        }
    }
}
