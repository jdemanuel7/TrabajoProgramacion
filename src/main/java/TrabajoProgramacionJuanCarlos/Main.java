package TrabajoProgramacionJuanCarlos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConexionBaseDatos conexion = new ConexionBaseDatos();
        Connection cn = null;
        Statement stm = null;
        ResultSet rs = null;
        MetodosInterfaz metodos = new MetodosInterfaz(); // Objeto para manejar operaciones de usuario
        Scanner scanner = new Scanner(System.in);

        try {
            // Establecer conexión y crear declaración
            cn = conexion.conectarBaseDatos();
            stm = cn.createStatement();

            // Verificar si la tabla 'USUARIOS' ya existe
            rs = stm.executeQuery("SHOW TABLES LIKE 'USUARIOS'");
            if (!rs.next()) {
                // Si la tabla no existe, crearla
                String crearSQL = "CREATE TABLE USUARIOS ("
                        + "DNI VARCHAR(12) NOT NULL, "
                        + "nombre VARCHAR(45), "
                        + "pais VARCHAR(45), "
                        + "PRIMARY KEY (DNI)"
                        + ")";
                stm.executeUpdate(crearSQL);
                System.out.println("Tabla 'USUARIOS' creada exitosamente.");
            } else {
                System.out.println("La tabla 'USUARIOS' ya existe.");
            }

            // Bucle principal del menú
            while (true) {
                // Mostrar el menú de opciones
                System.out.println("Menú:");
                System.out.println("1. Crear usuario");
                System.out.println("2. Modificar usuario");
                System.out.println("3. Borrar usuario");
                System.out.println("4. Obtener usuarios por país");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();

                try {
                    switch (opcion) {
                        case 1:
                            // Crear un nuevo usuario
                            System.out.print("Ingrese DNI: ");
                            String dniCrear = scanner.nextLine();
                            System.out.print("Ingrese nombre: ");
                            String nombreCrear = scanner.nextLine();
                            System.out.print("Ingrese país: ");
                            String paisCrear = scanner.nextLine();
                            Usuario nuevoUsuario = new Usuario(dniCrear, nombreCrear, paisCrear);
                            metodos.crearUsuario(nuevoUsuario);
                            System.out.println("Usuario creado exitosamente.");
                            break;

                        case 2:
                            // Modificar un usuario existente
                            System.out.print("Ingrese DNI del usuario a modificar: ");
                            String dniModificar = scanner.nextLine();
                            System.out.print("Ingrese nuevo nombre: ");
                            String nombreModificar = scanner.nextLine();
                            System.out.print("Ingrese nuevo país: ");
                            String paisModificar = scanner.nextLine();
                            Usuario usuarioModificado = new Usuario(dniModificar, nombreModificar, paisModificar);
                            metodos.modificarUsuario(dniModificar, usuarioModificado);
                            System.out.println("Usuario modificado exitosamente.");
                            break;

                        case 3:
                            // Borrar un usuario
                            System.out.print("Ingrese DNI del usuario a borrar: ");
                            String dniBorrar = scanner.nextLine();
                            metodos.borrarUsuario(dniBorrar);
                            System.out.println("Usuario borrado exitosamente.");
                            break;

                        case 4:
                            // Obtener usuarios por país
                            System.out.print("Ingrese país: ");
                            String paisObtener = scanner.nextLine();
                            ArrayList<Usuario> usuarios = metodos.obtenerUsuariosPorPais(paisObtener);
                            System.out.println("Usuarios del país " + paisObtener + ":");
                            for (Usuario usuario : usuarios) {
                                System.out.println("DNI: " + usuario.getDni() + ", Nombre: " + usuario.getNombre());
                            }
                            break;

                        case 5:
                            // Salir del programa
                            System.out.println("Saliendo del programa...");
                            scanner.close();
                            return;

                        default:
                            // Opción no válida
                            System.out.println("Opción no válida. Intente nuevamente.");
                            break;
                    }
                } catch (SQLException e) {
                    // Manejo de errores SQL
                    System.err.println("Error en la operación: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            // Manejo de errores al conectar a la base de datos
            e.printStackTrace();
        } finally {

            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
