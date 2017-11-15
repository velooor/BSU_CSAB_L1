package by.training.zakharchenya.courseproject.logic;

import by.training.zakharchenya.courseproject.dao.DoctorDAO;
import by.training.zakharchenya.courseproject.dao.MailDAO;
import by.training.zakharchenya.courseproject.dao.MedServiceDAO;
import by.training.zakharchenya.courseproject.database.ConnectionPool;
import by.training.zakharchenya.courseproject.entity.Doctor;
import by.training.zakharchenya.courseproject.entity.MedService;
import by.training.zakharchenya.courseproject.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DoctorService{
    private static final Logger LOG = LogManager.getLogger();

    public static boolean addDoctor(String name, String surname, String prof) {
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

    public static boolean changeDoctor(String name, String surname, String prof) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            connection.setAutoCommit(false);
            DoctorDAO doctorDAO = new DoctorDAO(connection);
            doctorDAO.changeDoctor( name,  surname,  prof);

            connection.commit();
            return true;
        } catch (SQLException | DAOException e) {
            LOG.log(Level.ERROR, "Problems with addService operation.", e);
            return false;
        }
    }

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

    /**Updates message status by id in database.
     * @param isRead true, if message read
     * @param messageId message id
     * @return corresponding result enum
     */
    public static MailLogic.Result updateMessage(boolean isRead, int messageId) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            connection.setAutoCommit(false);
            MailLogic.Result res;
            MailDAO mailDAO = new MailDAO(connection);

            mailDAO.updateMessageById(isRead, messageId);
            res = MailLogic.Result.SUCCESS;

            connection.commit();
            return res;
        } catch (SQLException | DAOException e) {
            LOG.log(Level.ERROR, "Problems with updateMessage operation.", e);
            return MailLogic.Result.EXCEPTION;
        }
    }
    public static List<Doctor> loadDoctors(){
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            connection.setAutoCommit(false);
            DoctorDAO doctorDAO = new DoctorDAO(connection);

            List<Doctor> result = doctorDAO.findAllDoctors();

            connection.commit();
            return result;
        } catch (SQLException | DAOException e) {
            LOG.log(Level.ERROR, "Problems with loadAllUserIncomingMessages operation.", e);
            return null;
        }
    }
}