package ejercicios;

import javax.xml.transform.Result;
import java.sql.*;

public class Ejercicio02 {

    public static void main(String[] args) {

        final String DB_USER = "root";
        final String DB_PASSWORD = "root";
        final String DB_URL = "jdbc:mysql://localhost:3306";
        final String CATALOG = "horario";
        final String SCHEMA_PATTERN = "horario";
        final String TABLE_NAME_PATTERN = "profesor";

        //APERTURA DE CONEXIÓN
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            DatabaseMetaData dbmd = connection.getMetaData();
            ResultSet result = null;

            //Obtención de meta datos
            String nombre = dbmd.getDatabaseProductName();
            String driver = dbmd.getDriverName();
            String url = dbmd.getURL();
            String usuario = dbmd.getUserName();

            //Mostramos meta datos
            System.out.printf("\nINFORMACIÓN SOBRE LA BASE DE DATOS:\n=========================" +
                    "\n· Nombre: %s\n· Driver: %s\n· URL: %s\n· Usuario: %s\n=========================\n",
                    nombre, driver, url, usuario);


            //Obtenemos información de las tablas y vistas
            result = dbmd.getTables(CATALOG, SCHEMA_PATTERN, TABLE_NAME_PATTERN, null);

            while(result.next()){
                String catalogo = result.getString(1);
                String esquema = result.getString(2);
                String tabla = result.getString(3);
                String tipo = result.getString(4);

                System.out.printf("\nINFORMACIÓN LAS TABLAS:\n=========================" +
                        "\n· Catalogo: %s\n· Esquema: %s\n· Tabla: %s\n· Tipo: %s\n=========================\n",
                        catalogo, esquema, tabla, tipo);
            }


            //OBTENEMOS INFORMACIÓN SOBRE LAS TABLAS
            System.out.printf("\nINFORMACIÓN SOBRE TABLA PROFESOR:\n=========================");
            ResultSet columnas = dbmd.getColumns(CATALOG, SCHEMA_PATTERN, TABLE_NAME_PATTERN, null);
            while (columnas.next()){
                String nombcol = columnas.getString("COLUMN_NAME");
                String tipoCol = columnas.getString("TYPE_NAME");
                String tamCol = columnas.getString("COLUMN_SIZE");
                String nula = columnas.getString("IS_NULLABLE");

                System.out.printf("\n· nColumna: %s · Tipo: %s · Tamaño: %s · ¿Puede ser nula?: %s",
                        nombcol, tipoCol, tamCol, nula);
            }
            System.out.printf("\n=========================\n");

            //OBTENCIÓN DE LA CLAVE PRIMARIA
            ResultSet primaryKey = dbmd.getPrimaryKeys(CATALOG, SCHEMA_PATTERN,TABLE_NAME_PATTERN);
            String primaryKeyProfesor = " ", separador=" ";
            while (primaryKey.next()){
                primaryKeyProfesor = primaryKeyProfesor + separador + primaryKey.getString("COLUMN_NAME");
                separador="+";
            }
            System.out.println("Clave primaria: " + primaryKeyProfesor);



            //CIERRE DE CONEXIÓN
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }



    }
}
