package TrabajoProgramacionJuanCarlos;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Consultas {
    // Método para crear un nuevo usuario en la base de datos.
    public void crearUsuario(Usuario usuario) throws SQLException;

    // Método para modificar los datos de un usuario existente en la base de datos.
    public void modificarUsuario(String dni, Usuario usuario) throws SQLException;

    // Método para borrar un usuario de la base de datos.
    public void borrarUsuario(String dni) throws SQLException;

    // Método para obtener una lista de usuarios de la base de datos que pertenecen a un país específico.
    ArrayList<Usuario> obtenerUsuariosPorPais(String pais) throws SQLException;
}




