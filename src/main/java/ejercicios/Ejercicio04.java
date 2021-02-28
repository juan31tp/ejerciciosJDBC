package ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio04 {

    public static void main(String[] args) {

        final String DRIVER = "com.mysql.jdbc.Driver";
        final String DB_USER = "root";
        final String DB_PASSWORD = "root";
        final String DB_URL = "jdbc:mysql://localhost:3306";

        String sqlInsercionProfesor = String.format("INSERT INTO profesor (codProf, nombre, apellidos, fechaAlta) VALUES " +
                "('%s','%s','%s','%s')",
                "DAS",
                "Daniel",
                "Ayala Soriano",
                "1999-09-01 00:00:00");
        String sqlInsercionCurso = String.format("INSERT INTO curso (codOe, codCurso, codTutor) VALUES " +
                "('%s','%s','%s')",
                "FPB",
                "3A",
                "DAS");
        String sqlInsercionAsignatura1 = String.format("INSERT INTO asignatura (codAsig, nombre, horasSemanales, horasTotales) VALUES " +
                "('%s','%s','%s','%s')",
                "@3OACE",
                "Operaciones auxiliares para la configuración y la explotación",
                7,
                245);
        String sqlInsercionAsignatura2 = String.format("INSERT INTO asignatura (codAsig, nombre, horasSemanales, horasTotales) VALUES " +
                "('%s','%s','%s','%s')",
                "@3MMSC",
                "Montaje y mantenmineot de sistemas y componentes informáticos",
                9,
                315);
        String sqlInsercionHorario1 = String.format("INSERT INTO horario (codOe, codCurso, codAsig, codTramo) VALUES " +
                "('%s','%s','%s','%s')",
                "FPB",
                "3A",
                "@3OACE",
                "X3");
        String sqlInsercionHorario2 = String.format("INSERT INTO horario (codOe, codCurso, codAsig, codTramo) VALUES " +
                "('%s','%s','%s','%s')",
                "FPB",
                "3A",
                "@3MMSC",
                "J3");
        String sqlInsercionReparto1 = String.format("INSERT INTO reparto (codOe, codCurso, codAsig, codProf) VALUES " +
                "('%s','%s','%s','%s')",
                "FPB",
                "3A",
                "@3OACE",
                "DAS");
        String sqlInsercionReparto2 = String.format("INSERT INTO reparto (codOe, codCurso, codAsig, codProf) VALUES " +
                "('%s','%s','%s','%s')",
                "FPB",
                "3A",
                "@3MMSC",
                "MGD");

        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            Statement sentencia = connection.createStatement();
            int rows;

            rows = sentencia.executeUpdate(sqlInsercionProfesor);
            System.out.printf("Profesor insertado - Filas afectadas: %d\n", rows);
            rows = sentencia.executeUpdate(sqlInsercionCurso);
            System.out.printf("Curso insertado - Filas afectadas: %d\n", rows);
            rows = sentencia.executeUpdate(sqlInsercionAsignatura1);
            System.out.printf("Primera asignatura insertada - Filas afectadas: %d\n", rows);
            rows = sentencia.executeUpdate(sqlInsercionAsignatura2);
            System.out.printf("Segunda asignatura insertada - Filas afectadas: %d\n", rows);
            rows = sentencia.executeUpdate(sqlInsercionHorario1);
            System.out.printf("Primer horario insertado - Filas afectadas: %d\n", rows);
            rows = sentencia.executeUpdate(sqlInsercionHorario2);
            System.out.printf("SEgundo horario insertado - Filas afectadas: %d\n", rows);
            rows = sentencia.executeUpdate(sqlInsercionReparto1);
            System.out.printf("Primer reparto insertado - Filas afectadas: %d\n", rows);
            rows = sentencia.executeUpdate(sqlInsercionReparto2);
            System.out.printf("Segundo reparto insertado - Filas afectadas: %d\n", rows);
            sentencia.close();

            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
