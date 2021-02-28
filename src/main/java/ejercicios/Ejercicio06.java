package ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio06 {

    public static void main(String[] args) {

        final String DRIVER = "com.mysql.jdbc.Driver";
        final String DB_USER = "root";
        final String DB_PASSWORD = "root";
        final String DB_URL = "jdbc:mysql://localhost:3306";

        String sqlReparto = "DELETE FROM reparto WHERE codOe='FPB';";
        String sqlAsig = "DELETE FROM asignatura WHERE codAsig in " +
                "(SELECT a.codAsig FROM asignatura a JOIN horario h ON a.codAsig = h.codAsig WHERE h.codCurso = '3A');";
        String sqlCurso = "DELETE FROM curso WHERE codCurso='3A';";
        String sqlOe = "DELETE FROM ofertaeducativa WHERE codOe='FPB';";

        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            int rows;
            Statement sentencia = connection.createStatement();

            rows= sentencia.executeUpdate(sqlReparto);
            System.out.printf("Borrado de la tabla reparto con éxito - Filas afectadas: %d\n", rows);
            rows = sentencia.executeUpdate(sqlAsig);
            System.out.printf("Borrado de asignaturas con éxito - Filas afectadas: %d\n", rows);
            rows = sentencia.executeUpdate(sqlCurso);
            System.out.printf("Borrado de curso con éxito - Filas afectadas: %d\n", rows);
            rows = sentencia.executeUpdate(sqlOe);
            System.out.printf("Borrado de oferta educativa con éxito - Filas afectadas: %d\n", rows);

            sentencia.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
