package ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio05 {

    public static void main(String[] args) {

        final String DRIVER = "com.mysql.jdbc.Driver";
        final String DB_USER = "root";
        final String DB_PASSWORD = "root";
        final String DB_URL = "jdbc:mysql://localhost:3306";

        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sql = "UPDATE asignatura SET horasSemanales = horasSemanales*1.1 , horasTotales = horasTotales*1.1 WHERE codAsig = " +
                    "(SELECT horario.codAsig FROM horario JOIN asignatura ON asignatura.codAsig = horario.codAsig WHERE horario.codCurso = '3A' AND asignatura.nombre LIKE 'M%');";
            Statement sentencia = connection.createStatement();

            int filas = sentencia.executeUpdate(sql);
            System.out.printf("Actualizacion realizada - Filas afectadas: %d\n", filas);

            sentencia.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
