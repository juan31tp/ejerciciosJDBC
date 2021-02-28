package ejercicios;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio01 {

    public static void main(String[] args) {

        final String SCRIPT_PATH = "script_horario.sql";
        final String DB_USER = "root";
        final String DB_PASSWORD = "root";
        final String DB_URL = "jdbc:mysql://localhost:3306?allowMultiQueries=true";

        File scriptFile = new File(SCRIPT_PATH);
        System.out.println("\nFichero de consulta: " + scriptFile.getName());

        System.out.println("Convirtiendo el fichero a cadena...");
        BufferedReader entrada;

        try {
            entrada = new BufferedReader(new FileReader(scriptFile));

            String linea = null;
            StringBuilder stringBuilder = new StringBuilder();
            String salto = System.getProperty("line.separator");

            while ((linea = entrada.readLine()) != null){
                stringBuilder.append(linea);
                stringBuilder.append(salto);
            }

            String consulta = stringBuilder.toString();
            System.out.println(consulta);

            Class.forName("com.mysql.jdbc.Driver");

            Connection connmysql = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement sents = connmysql.createStatement();
            int res = sents.executeUpdate(consulta);
            System.out.println("Script creado con éxito, res = " + res);
            connmysql.close();
            sents.close();

        } catch (FileNotFoundException e){
            System.out.println("ERROR, NO HAY ARCHIVO: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("ERROR, DE E/S AL OPERAR: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR, DEL DRIVER: " + e.getMessage());
        } catch (SQLException throwables) {
            System.out.println("ERROR, AL EJECUTAR EL SCRIPT: " + throwables.getMessage());
        }

    }
}
