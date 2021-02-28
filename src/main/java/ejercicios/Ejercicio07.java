package ejercicios;

import java.sql.*;

public class Ejercicio07 {

    public static void main(String[] args) {

        final String DRIVER = "com.mysql.jdbc.Driver";
        final String DB_USER = "root";
        final String DB_PASSWORD = "root";
        final String DB_URL = "jdbc:mysql://localhost:3306";

        String sqlObtencionProfesorPorApellido = "SELECT * FROM profesor ORDER BY apellidos ASC;";

        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            Statement sentencia = connection.createStatement();

            boolean valor = sentencia.execute(sqlObtencionProfesorPorApellido);

            if(valor){
                ResultSet rs = sentencia.getResultSet();
                while (rs.next()){
                    System.out.printf(rs.getString(1) + ","
                            + rs.getString(2) + ","
                            + rs.getString(3) + ","
                            + rs.getString(4));
                }
                rs.close();
            }else{
                int rows = sentencia.getUpdateCount();
                System.out.printf("\nContenido de la tabla profesor recibido con éxito - Filas afectadas: %d\n", rows);
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
