package Factory;

import Daos.PlayerDao;
import Interface.IDao;

/**
 *
 * @author alsorc
 */
public class DaoFactory {
    
    
    public enum DaoType{
        PLAYER
    }
    /**
     * @param daoType recibe el tipo de dao que quieres crear. PLAYER |     
     * @return una instancia del dao que indicamos
    */
    public static IDao getDao (DaoType daoType){
        IDao dao = null;
        switch (daoType){
            case PLAYER:
                dao = new PlayerDao();
                break;
        }
        return dao;
    }
    
}
