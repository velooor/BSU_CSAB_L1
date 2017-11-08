package by.training.zakharchenya.courseproject.dao;

import by.training.zakharchenya.courseproject.entity.Account;
import by.training.zakharchenya.courseproject.entity.MedService;
import by.training.zakharchenya.courseproject.entity.Message;
import by.training.zakharchenya.courseproject.exception.DAOException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lagarde on 07.11.2017.
 */
public class MedServiceDAO extends AbstractDAO{
    private static final String SQL_ADD_MESSAGE ="INSERT INTO `medicalservice` (`serviceName`, `patient`, `doctor`, `price`, `serviceDate`, `serviceDescription`) "
            +"VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_FIND_INCOME_MESSAGES_BY_ACCOUNT_ID =
            "SELECT  * FROM `medicalservice`";

    private static final String SQL_REMOVE_MESSAGE_BY_ID = "DELETE FROM `medicalservice` WHERE `serviceId` = ?";

    private static final String SQL_UPDATE_ISREAD_BY_MESSAGE_ID = "UPDATE `message` SET `isRead` = ? WHERE `messageId` = ?";
    private static final String SQL_UPDATE_SERVICE_BY_ID = "UPDATE `medicalservice` SET `serviceName` = ?, `patient` = ?, `doctor` = ?, `price` = ?, `serviceDate` = ?, `serviceDescription` = ? WHERE `serviceId` = ?";

    private static final String SERVICE_ID_COLUMN = "serviceId";
    private static final String SERVICE_NAME_COLUMN = "serviceName";
    private static final String SERVICE_DOCTOR_COLUMN = "doctor";
    private static final String SERVICE_PATIENT_COLUMN = "patient";
    private static final String SERVICE_PRICE_COLUMN = "price";
    private static final String SERVICE_DATE_COLUMN = "serviceDate";
    private static final String SERVICE_DESCRIPTION_COLUMN = "serviceDescription";



    public MedServiceDAO(Connection connection) {
        super(connection);
    }


    public boolean addMedService(String serviceName, String description, double price, int doctord, int patientId, String date) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_ADD_MESSAGE, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, serviceName);
            statement.setInt(2, patientId);
            statement.setInt(3, doctord);
            statement.setDouble(4, price);
            statement.setString(5, date);
            statement.setString(6, description);
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Problems with addMedService to database.", e);
        }
    }

    public List<MedService> findAllMedServices() throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_INCOME_MESSAGES_BY_ACCOUNT_ID)) {
            List<MedService> messages = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                MedService medService = new MedService(
                        resultSet.getInt(SERVICE_ID_COLUMN),
                        resultSet.getString(SERVICE_NAME_COLUMN),
                        resultSet.getInt(SERVICE_PATIENT_COLUMN),
                        resultSet.getInt(SERVICE_DOCTOR_COLUMN),
                        resultSet.getDouble(SERVICE_PRICE_COLUMN),
                        resultSet.getDate(SERVICE_DATE_COLUMN),
                        resultSet.getString(SERVICE_DESCRIPTION_COLUMN));
                messages.add(medService);
            }
            return messages;
        } catch (SQLException e) {
            throw new DAOException("Problems with finding messages by account id and in database.", e);
        }
    }

    public void changeMedService(int id, String serviceName, String description, double price, int doctord, int patientId, String date) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_SERVICE_BY_ID)) {
            statement.setString(1, serviceName);
            statement.setInt(2, patientId);
            statement.setInt(3, doctord);
            statement.setDouble(4, price);
            statement.setString(5, date);
            statement.setString(6, description);
            statement.setInt(7, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException("Problems with database(updatePasswordByAccountId).", e);
        }
    }

    /**Removes message in database.
     * @param id game id
     * @throws DAOException signals, that statement was not executed successfully
     */
    public void removeMessageById(int id) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_REMOVE_MESSAGE_BY_ID)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException("Problems with database.", e);
        }
    }

    /**Updates message status by id in database.
     * @param isRead account id of addressee
     * @param id game id
     * @throws DAOException signals, that statement was not executed successfully
     */
    public void updateMessageById(boolean isRead, int id) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ISREAD_BY_MESSAGE_ID)) {
            statement.setBoolean(1, isRead);
            statement.setInt(2, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException("Problems with database.", e);
        }
    }

}
