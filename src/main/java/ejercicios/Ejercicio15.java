package ejercicios;

import java.sql.*;

public class Ejercicio15 {

    public static void main(String[] args) {

        final String DRIVER = "com.mysql.jdbc.Driver";
        final String DB_USER = "root";
        final String DB_PASSWORD = "root";
        final String DB_URL = "jdbc:mysql://localhost:3306";

        String sqlAsignaturas = "Select P.nombre, a.nombre, H.codTramo from Asignatura a " +
                "join Horario H on a.codAsig = H.codAsig " +
                "join Reparto R on a.codAsig = R.codAsig " +
                "join Profesor P on P.codProf = R.codProf " +
                "join TramoHorario TH on TH.codTramo = H.codTramo " +
                "where P.codProf = 'CVC' and TH.dia = ELT(" +
                "DAYOFWEEK(CURRENT_DATE),'DOMINGO','LUNES','MARTES','MIERCOLES','JUEVES','VIERNES','SABADO')" +
                "and current_time between TH.horaInicio and TH.horaFin";

        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            Statement sentencia = connection.createStatement();

            boolean valor = sentencia.execute(sqlAsignaturas);

            if (valor) {
                ResultSet resultSet = sentencia.getResultSet();

                while(resultSet.next()) {
                    System.out.printf("\n- Profesor: " + resultSet.getString(1) +
                            "\n- Asignatura: " + resultSet.getString(2) +
                            "\n- Tramo horario: " + resultSet.getString(3) + "\n");
                }
            } else {
                System.out.println("El profesor en cuestión no imparte clases");
            }

            sentencia.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
