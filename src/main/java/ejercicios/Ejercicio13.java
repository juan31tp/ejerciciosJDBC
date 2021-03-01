package ejercicios;

import java.sql.*;
import java.util.*;

public class Ejercicio13 {

    public static void main(String[] args) {

        final String DRIVER = "com.mysql.jdbc.Driver";
        final String DB_USER = "root";
        final String DB_PASSWORD = "root";
        final String DB_URL = "jdbc:mysql://localhost:3306";
        Scanner teclado = new Scanner(System.in);
        String[][] horario = new String[6][6];

        String sqlHorario = "SELECT horaInicio, horaFin, codAsig from TramoHorario TH " +
                "join Horario H on TH.codTramo = H.codTramo " +
                "where H.codOe = 'DAM' and H.codCurso = '2A' order by dia, horaInicio,horaFin";

        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement sentencia = connection.createStatement();

            List<List<String>> filas = new ArrayList();
            List<String> encabezado = Arrays.asList(" -------- HORA -------- ", " Lunes ", " Martes ", " Miercoles ", " Jueves ", " Viernes ");

            filas.add(encabezado);

            try {
                boolean valor = sentencia.execute(sqlHorario);

                if (valor) {
                    ResultSet resultSet = sentencia.getResultSet();
                    int colum = 1;
                    int row = 0;

                    while(resultSet.next()) {
                        String hora = String.format(resultSet.getString(1) + " - " + resultSet.getString(2));
                        horario[row][0] = hora;

                        String asig = resultSet.getString(3);

                        if (asig.charAt(0) == '@') {
                            horario[row][colum] = "*";
                        } else {
                            horario[row][colum] = resultSet.getString(3);
                        }
                        row++;

                        if (row >= 6) {
                            row = 0;
                            colum++;
                        }
                    }

                    imprimirTabla(horario, encabezado);
                }
            } catch (Throwable var13) {
                throw var13;
            }

            teclado.close();
            sentencia.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void imprimirTabla(String[][] horario, List<String> encabezado) {
        Iterator iterator = encabezado.iterator();

        while(iterator.hasNext()) {
            String header = (String)iterator.next();
            System.out.printf("%10s", header);
        }

        System.out.println();
        String[][] schedule = horario;
        int longitudHorario = horario.length;

        for(int i = 0; i < longitudHorario; ++i) {
            String[] strings = schedule[i];
            int j = 0;

            for(int h = 0; j < horario[1].length; ++h) {
                System.out.printf("%10s", strings[j]);
                if (h == 4) {
                    h = 0;
                }
                j++;
            }

            System.out.print("\n");
        }
    }

}
