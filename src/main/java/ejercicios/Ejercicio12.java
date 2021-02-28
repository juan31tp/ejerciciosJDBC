package ejercicios;

import java.sql.*;

public class Ejercicio12 {

    public static void main(String[] args) {

        final String DRIVER = "com.mysql.jdbc.Driver";
        final String DB_USER = "root";
        final String DB_PASSWORD = "root";
        final String DB_URL = "jdbc:mysql://localhost:3306";

        String sqlAsignaturasProfesor = "SELECT asignatura.nombre FROM `asignatura` JOIN `reparto` ON reparto.codAsig = asignatura.codAsig JOIN `profesor` ON reparto.codProf = profesor.codProf WHERE profesor.codProf = \"ARR\";";

        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            Statement sentencia = connection.createStatement();

            boolean valor = sentencia.execute(sqlAsignaturasProfesor);

            if(valor){
                ResultSet resultSet = sentencia.getResultSet();
                while (resultSet.next()){
                    System.out.printf(resultSet.getString(1) + "\n");
                }
                resultSet.close();
            }else{
                int rows = sentencia.getUpdateCount();
                System.out.printf("\nContenido recibido con éxito - Filas afectadas: %d\n", rows);
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
