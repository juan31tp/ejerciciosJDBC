package ejercicios;

import java.sql.*;

public class Ejercicio09 {

    public static void main(String[] args) {

        final String DRIVER = "com.mysql.jdbc.Driver";
        final String DB_USER = "root";
        final String DB_PASSWORD = "root";
        final String DB_URL = "jdbc:mysql://localhost:3306";

        String sqlObtencionTutoresCursos = "SELECT profesor.*, curso.* FROM `profesor` LEFT JOIN `curso` ON profesor.codProf = curso.codTutor;";

        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            Statement sentencia = connection.createStatement();
            String nullable;
            ResultSetMetaData resultSetMetaData = sentencia.getResultSet().getMetaData();

            System.out.printf("\nINFORMACIÓN DE LA TABLA:\n" + "-------------------------\n");
            int columnas = resultSetMetaData.getColumnCount();

            for(int i = 1; i < columnas; i++){
                System.out.printf("Columna: %d\n", i);
                System.out.printf(" - Nombre: %s\n", resultSetMetaData.getColumnName(i));
                System.out.printf(" - Nombre: %s\n", resultSetMetaData.getColumnType(i));

                if(resultSetMetaData.isNullable(i) == 0){
                    nullable = "NO";
                }else{
                    nullable = "SI";
                }

                System.out.printf(" - Puede ser nula?: %s\n", nullable);
                System.out.printf(" - Capacidad: %s\n", resultSetMetaData.getColumnDisplaySize(i));

            }
            System.out.printf("-------------------------\n");

            sentencia.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
