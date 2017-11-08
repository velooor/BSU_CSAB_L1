package by.training.zakharchenya.courseproject.logic;

import by.training.zakharchenya.courseproject.dao.AccountDAO;
import by.training.zakharchenya.courseproject.dao.MailDAO;
import by.training.zakharchenya.courseproject.dao.MedServiceDAO;
import by.training.zakharchenya.courseproject.database.ConnectionPool;
import by.training.zakharchenya.courseproject.entity.Account;
import by.training.zakharchenya.courseproject.entity.MedService;
import by.training.zakharchenya.courseproject.entity.Message;
import by.training.zakharchenya.courseproject.exception.DAOException;
import by.training.zakharchenya.courseproject.validator.AccountValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Lagarde on 07.11.2017.
 */
public class MedServiceLogic {
    private static final Logger LOG = LogManager.getLogger();
    public enum Result {
        EXCEPTION, SUCCESS, WRONG_LOGIN, INCORRECT_LOGIN
    }

    public static boolean addService(String serviceName, String description, double price, int doctord, int patientId, String date) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            connection.setAutoCommit(false);
            MedServiceDAO medServiceDAO = new MedServiceDAO(connection);
            medServiceDAO.addMedService( serviceName,  description,  price,  doctord,  patientId,  date);

            connection.commit();
            return true;
        } catch (SQLException | DAOException e) {
            LOG.log(Level.ERROR, "Problems with addService operation.", e);
            return false;
        }
    }

    public static boolean changeService(int id, String serviceName, String description, double price, int doctord, int patientId, String date) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            connection.setAutoCommit(false);
            MedServiceDAO medServiceDAO = new MedServiceDAO(connection);
            medServiceDAO.changeMedService(id,  serviceName,  description,  price,  doctord,  patientId,  date);
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
    public static boolean deleteMessage(int messageId) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            connection.setAutoCommit(false);
            MedServiceDAO medServiceDAO = new MedServiceDAO(connection);

            medServiceDAO.removeMessageById(messageId);

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
    public static List<MedService> loadMedServices(){
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            connection.setAutoCommit(false);
            MedServiceDAO medServiceDAO = new MedServiceDAO(connection);

            List<MedService> result = medServiceDAO.findAllMedServices();

            connection.commit();
            return result;
        } catch (SQLException | DAOException e) {
            LOG.log(Level.ERROR, "Problems with loadAllUserIncomingMessages operation.", e);
            return null;
        }
    }
}
