package TrabajoProgramacionJuanCarlos;

import java.sql.*;
import java.util.ArrayList;

public class MetodosInterfaz implements Consultas {
    // URL de conexión a la base de datos con el nombre de la base de datos, el puerto y el parámetro de recuperación de clave pública
    private static final String URL = "jdbc:mysql://localhost:3309/basedatostrabajofinal?allowPublicKeyRetrieval=true";
    private static final String USUARIO = "root";
    private static final String CLAVE = "1234";

    @Override
    public void crearUsuario(Usuario usuario) throws SQLException {

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
             PreparedStatement ps = conexion.prepareStatement("INSERT INTO USUARIOS (dni, nombre, pais) VALUES (?, ?, ?)")) {
            // Configuración de los parámetros de la consulta preparada
            ps.setString(1, usuario.getDni());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getPais());
            // Ejecución de la consulta de inserción
            ps.executeUpdate();
        }
    }

    @Override
    public void modificarUsuario(String dni, Usuario usuario) throws SQLException {

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
             PreparedStatement ps = conexion.prepareStatement("UPDATE USUARIOS SET nombre=?, pais=? WHERE dni=?")) {
            // Configuración de los parámetros de la consulta preparada
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getPais());
            ps.setString(3, dni);
            // Ejecución de la consulta de actualización
            ps.executeUpdate();
        }
    }

    @Override
    public void borrarUsuario(String dni) throws SQLException {

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
             PreparedStatement ps = conexion.prepareStatement("DELETE FROM USUARIOS WHERE dni=?")) {
            // Configuración del parámetro de la consulta preparada
            ps.setString(1, dni);
            // Ejecución de la consulta de eliminación
            ps.executeUpdate();
        }
    }

    @Override
    public ArrayList<Usuario> obtenerUsuariosPorPais(String pais) throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
             PreparedStatement ps = conexion.prepareStatement("SELECT * FROM USUARIOS WHERE pais=?")) {
            // Configuración del parámetro de la consulta preparada
            ps.setString(1, pais);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String dni = rs.getString("dni");
                    String nombre = rs.getString("nombre");
                    usuarios.add(new Usuario(dni, nombre, pais));
                }
            }
        }
        return usuarios;
    }
}
