package Daos;

import Connection.Connect2Db;
import Interface.IDao;
import Models.Player;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alsorc
 * 
 * 
 */
public class PlayerDao implements IDao<Player, Integer> {

    //Declaración de variable para preparar las consultas
    private Connection connection;
    //Declaración de variable para insertar valores a consultas
    private PreparedStatement preQuery;
    //Devuelve true si se ejecutó correctamente y false si algo falló
    private boolean isSuccesfully = false;
    //Objeto que servirá para almacenar información al buscar un registro
    private Player player;
    //Lista de registros
    private List<Player> players;

    //Arreglo con las consultas que se usarán para la tabla jugadores
    private final String[] QUERIES = {
        "INSERT INTO jugadores (nombre, posicion, edad) VALUES ( ?, ? , ? )",
        "SELECT * FROM jugadores WHERE id = ?;",
        "UPDATE jugadores SET nombre = ? , posicion = ?, edad = ? WHERE (id = ?);",
        "DELETE FROM jugadores WHERE (id = ?);",
        "SELECT * FROM jugadores;"
    };

    //Constructor de la clase en donde se inicializarán las variables previamente declaradas
    public PlayerDao() {
        connection = Connect2Db.getInstance().getConnection();
        player = new Player();
        players = new ArrayList();
    }

    @Override
    public boolean createRecord(Player model){
        try {
            preQuery = connection.prepareStatement(QUERIES[0]);
            
            preQuery.setString(1, model.getName());
            preQuery.setString(2, model.getPosition());
            preQuery.setInt(3, model.getAge());
            
            //Mostrar la consulta completa en pantalla
            System.out.println(preQuery);

            if (preQuery.executeUpdate() > 0) {
                isSuccesfully = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccesfully;
    }

    @Override
    public Player readRecord(Integer idModel) {
        try {
            preQuery = connection.prepareStatement(QUERIES[1]);
            preQuery.setInt(1, idModel);
            ResultSet data = preQuery.executeQuery();

            if (data.next()) {
                player.setName(data.getString("nombre"));
                player.setPosition(data.getString("posicion"));
                player.setAge(data.getInt("edad"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return player;
    }

    @Override
    public boolean updateRecord(Player model, Integer idModel) {
        try {
            preQuery = connection.prepareStatement(QUERIES[2]);
            preQuery.setString(1, model.getName());
            preQuery.setString(2, model.getPosition());
            preQuery.setInt(3, model.getAge());
            preQuery.setInt(4, idModel);

            if (preQuery.executeUpdate() > 0) {
                isSuccesfully = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccesfully;
    }

    @Override
    public boolean deleteRecord(Integer idModel) {
        try {
            preQuery = connection.prepareStatement(QUERIES[3]);
            preQuery.setInt(1, idModel);

            if (preQuery.executeUpdate() > 0) {
                isSuccesfully = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccesfully;
    }

    @Override
    public List<Player> readRecords() {
        try {
            preQuery = connection.prepareStatement(QUERIES[4]);
            ResultSet data = preQuery.executeQuery();

            while (data.next()) {
                players.add(new Player(data.getInt("id"), data.getString("nombre"), data.getString("posicion"), data.getInt("edad")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return players;
    }

}
