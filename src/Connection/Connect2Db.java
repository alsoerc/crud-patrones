package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alsorc
 */
public class Connect2Db {

    //Instancia de la clase
    private static Connect2Db connectInstance;
    //Variable para realizar la conexión
    private Connection connection;
    //Dirección y nombre de la bd conectarnos
    private final String URL = "jdbc:postgresql://localhost:5442/seleccion";
    //Nombre de usuario autorizado para conectarse
    private final String USER = "postgres";
    //Contraseña para conectarnos
    private final String PASSWORD = "12334";
    
    private Connect2Db() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conectado!");
        } catch (SQLException ex) {
            Logger.getLogger(Connect2Db.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            System.out.println("OK ");
        }
    }
    
    //Método para acceder a la instancia de la clase
    public static Connect2Db getInstance(){
        if (connectInstance == null ){
            connectInstance = new Connect2Db();
        }
        return connectInstance;
    }
       
    public Connection getConnection(){
        return connection;
    }

}
