package ejercicios;

import java.sql.*;

public class Ejercicio03 {

    public static void main(String[] args) {

        final String DRIVER = "com.mysql.jdbc.Driver";
        final String DB_USER = "root";
        final String DB_PASSWORD = "root";
        final String DB_URL = "jdbc:mysql://localhost:3306";

        String cod_oe = "FPB";
        String nombre_oe = "FP Básica Informática y comunicaciones";
        String descripcion_oe = "La formación profesional básica de informática y comunicaciones tiene" +
                "una duración de 2000 horas repartidas entre dos cursos académicos incluyendo 240 horas" +
                "de Formación en centros de trabajo (FCT) en empresas del sector";
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sql = "INSERT INTO ofertaeducativa (codOe, nombre, descripcion) VALUES ("
                    + cod_oe + ","
                    + nombre_oe + ","
                    + descripcion_oe + ")";
            Statement sentencia = connection.createStatement();

            int rows = sentencia.executeUpdate(sql);
            System.out.println("Filas afectadas: " + rows);

            sentencia.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
