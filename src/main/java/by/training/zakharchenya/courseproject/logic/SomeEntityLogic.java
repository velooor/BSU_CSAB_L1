package by.training.zakharchenya.courseproject.logic;

import by.training.zakharchenya.courseproject.dao.DoctorDAO;
import by.training.zakharchenya.courseproject.dao.EntityDAO;
import by.training.zakharchenya.courseproject.database.ConnectionPool;
import by.training.zakharchenya.courseproject.entity.Doctor;
import by.training.zakharchenya.courseproject.entity.Fieeld;
import by.training.zakharchenya.courseproject.entity.SomeEntity;
import by.training.zakharchenya.courseproject.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SomeEntityLogic {
    private static final Logger LOG = LogManager.getLogger();

    public static boolean addEntity(String name, String surname, String prof) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            connection.setAutoCommit(false);
            DoctorDAO doctorDAO = new DoctorDAO(connection);
            doctorDAO.addDoctor( name,  surname,  prof);

            connection.commit();
            return true;
        } catch (SQLException | DAOException e) {
            LOG.log(Level.ERROR, "Problems with addService operation.", e);
            return false;
        }
    }

    public static String getEntityName() {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            connection.setAutoCommit(false);
            EntityDAO doctorDAO = new EntityDAO(connection);
            String name = doctorDAO.getEntityName();

            connection.commit();
            return name.toLowerCase();
        } catch (SQLException | DAOException e) {
            LOG.log(Level.ERROR, "Problems with addService operation.", e);
            return null;
        }
    }

    public static List<SomeEntity> loadEntities(List<Fieeld> fields, String tableName) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            connection.setAutoCommit(false);
            EntityDAO doctorDAO = new EntityDAO(connection);

            List<SomeEntity> result = doctorDAO.findAllEntities(fields, tableName);

            connection.commit();
            return result;
        } catch (SQLException | DAOException e) {
            LOG.log(Level.ERROR, "Problems with loadAllUserIncomingMessages operation.", e);
            return null;
        }
    }//getFields

    public static List<Fieeld> getFields() {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            connection.setAutoCommit(false);
            EntityDAO doctorDAO = new EntityDAO(connection);

            List<Fieeld> result = doctorDAO.findAllFields();

            connection.commit();
            return result;
        } catch (SQLException | DAOException e) {
            LOG.log(Level.ERROR, "Problems with loadAllUserIncomingMessages operation.", e);
            return null;
        }
    }//getFields

    /**Deletes message by id in database.
     * @param messageId message id
     * @return corresponding result enum
     */
    public static boolean deleteDoctor(int messageId) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            connection.setAutoCommit(false);
            DoctorDAO doctorDAO = new DoctorDAO(connection);

            doctorDAO.removeMessageById(messageId);

            connection.commit();
            return true;
        } catch (SQLException | DAOException e) {
            LOG.log(Level.ERROR, "Problems with deleteMessage operation.", e);
            return false;
        }
    }

}
