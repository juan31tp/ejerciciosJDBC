package ejercicios;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Prueba {

    public static void main(String[] args) {
        executeScript();
    }


    public static void executeScript() {
        File scriptFile = new File("./script_tablas.sql");
        System.out.println("\n\nFichero de consulta :" +
                scriptFile.getName());
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(scriptFile))) {
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            String salto = System.getProperty("line.separator");

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(salto);
            }
            String consulta = stringBuilder.toString();
            System.out.println(consulta);

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306", "root", "root");
            Statement sents = connection.createStatement();
            int res = sents.executeUpdate(consulta);
            System.out.println("Script creado con éxito.");
            connection.close();
        } catch (IOException e) {
            System.out.println("Fichero no encontrado");
        } catch (SQLException sqlException) {
            System.out.println("Error al ejecutar el script" + sqlException.getMessage());
        }
    }
}

