package TrabajoProgramacionJuanCarlos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {

    // configuración de la conexión a la base de datos.
    private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3309/basedatostrabajofinal?allowPublicKeyRetrieval=true"; // URL de conexión a la base de datos. Note el puerto y el nombre de la base de datos.
    private static final String USUARIO = "root";
    private static final String CLAVE = "1234";

    // Método para establecer una conexión con la base de datos.
    public Connection conectarBaseDatos() {
        Connection conexionBaseDatos = null;
        try {
            // Establece la conexión usando los parámetros definidos.
            conexionBaseDatos = DriverManager.getConnection(URL, USUARIO, CLAVE);
            System.out.println("Conexión OK");
        } catch (SQLException e) {
            // Manejo de la excepción en caso de fallo en la conexión.
            System.out.println("Error en la conexión");
            e.printStackTrace();
        }
        return conexionBaseDatos;
    }
}
