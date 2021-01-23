package crudseleccion;

import Factory.DaoFactory;
import Interface.IDao;
import Models.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author alsorc
 */
public class CrudSeleccion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Objeto para almacenar información
        Player player = new Player();

        //Obtenemos instancia del dao PLAYER
        IDao dao = DaoFactory.getDao(DaoFactory.DaoType.PLAYER);

        //Lista para almacenar registros
        List<Player> players = new ArrayList();
        Scanner sc = new Scanner(System.in);
        //Para almacenar la acción que queremos realizar
        int option;
        //Guarda el id del jugador que queramos
        int idPlayer;
        //Variable para controlar el flujo del menú
        boolean redo = true;

        do {
            System.out.println("*# GESTIÓN DE JUGADORES SELECCIÓN #*");
            System.out.println("¿Qué acción quieres realizar?");
            System.out.println("[1] Crear registro\n"
                    + "[2] Actualizar registro\n"
                    + "[3] Eliminar registro\n"
                    + "[4] Buscar un registro\n"
                    + "[5] Mostrar todos los registros\n"
                    + "[0] Salir");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("|REGISTRAR JUGADOR|");
                    System.out.println("Inserta nombre: ");
                    player.setName(sc.next());
                    System.out.println("Posición del jugador: ");
                    player.setPosition(sc.next());
                    System.out.println("Edad del jugador: ");
                    player.setPosition(sc.next());
                    System.out.println(dao.createRecord(player));
                    break;
                case 2:
                    System.out.println("|ACTUALIZAR JUGADOR|");
                    System.out.println("Id del jugador: ");
                    idPlayer = sc.nextInt();
                    System.out.println("Inserta nombre: ");
                    player.setName(sc.next());
                    System.out.println("Posición del jugador: ");
                    player.setPosition(sc.next());
                    System.out.println("Edad del jugador: ");
                    player.setPosition(sc.next());
                    System.out.println(dao.updateRecord(player, idPlayer));
                    break;
                case 3:
                    System.out.println("|ELIMINAR JUGADOR|");
                    System.out.println("Id del jugador: ");
                    idPlayer = sc.nextInt();
                    System.out.println(dao.deleteRecord(idPlayer));
                    break;
                case 4:
                    System.out.println("|BUSCAR JUGADOR|");
                    System.out.println("Id del jugador: ");
                    idPlayer = sc.nextInt();
                    dao.readRecord(idPlayer);
                    System.out.println(dao.readRecord(idPlayer));
                    break;
                case 5:
                    System.out.println("|LISTA DE REGISTROS|");
                    players.clear();
                    players = dao.readRecords();
                    for (Player record : players) {
                        System.out.println(record.toString());
                    }
                    break;
                case 0:
                    System.out.println("|SALIENDO...|");
                    break;
                default:
                    System.out.println("Opción no válida!");
            }
            
            if (option == 0) {
                redo = false;
            }else{
                System.out.println("¿Realizar otra acción? [1] Sí/[2] No");
                option = sc.nextInt();
                if(option != 1){
                    redo = false;
                    System.out.println("|SALIENDO...|");
                }
            }

        } while (redo);

    }

}
