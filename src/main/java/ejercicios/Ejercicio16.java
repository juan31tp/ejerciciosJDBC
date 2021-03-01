package ejercicios;

import java.sql.*;

public class Ejercicio16 {

    public static void main(String[] args) {

        final String DRIVER = "com.mysql.jdbc.Driver";
        final String DB_USER = "root";
        final String DB_PASSWORD = "root";
        final String DB_URL = "jdbc:mysql://localhost:3306";

        String sqlAsignaturas = "select a.nombre, horasSemanales, count(distinct c.codCurso), count(distinct o.codOe) " +
                "from Asignatura a join Reparto r on a.codAsig = r.codAsig " +
                "join Curso c on c.codOe = r.codOe and c.codCurso = r.codCurso " +
                "join OfertaEducativa o on o.codOe = c.codOe where a.horasSemanales>=3 " +
                "group by a.codAsig";

        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            Statement sentencia = connection.createStatement();

            boolean valor = sentencia.execute(sqlAsignaturas);

            if (valor) {
                ResultSet resultSet = sentencia.getResultSet();

                while(resultSet.next()) {
                    System.out.printf("\n- Nombre asignatura: " + resultSet.getString(1) +
                            "\n- Horas semanales: " + resultSet.getString(2) +
                            "\n- Numero de cursos: " + resultSet.getString(3) +
                            "\n- Numero de ofertas: " + resultSet.getString(4) +
                            "\n");
                }
            } else {
                System.out.println("No existe la asignatura en cuestión");
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
