package by.training.zakharchenya.courseproject.dao;

import by.training.zakharchenya.courseproject.entity.Doctor;
import by.training.zakharchenya.courseproject.entity.MedService;
import by.training.zakharchenya.courseproject.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO extends AbstractDAO{
    private static final String SQL_ADD_DOCTOR ="INSERT INTO `doctor` (`doctorName`, `doctorSurname`, `prof`) "
            +"VALUES (?, ?, ?)";

    private static final String SQL_FIND_INCOME_MESSAGES_BY_ACCOUNT_ID =
            "SELECT  * FROM `doctor`";

    private static final String SQL_REMOVE_MESSAGE_BY_ID = "DELETE FROM `doctor` WHERE `doctorId` = ?";

    private static final String SQL_UPDATE_ISREAD_BY_MESSAGE_ID = "UPDATE `message` SET `isRead` = ? WHERE `messageId` = ?";
    private static final String SQL_UPDATE_SERVICE_BY_ID = "UPDATE `doctor` SET `doctorName` = ?, `doctorSurname` = ?, `prof` = ? WHERE `doctorId` = ?";

    private static final String SERVICE_ID_COLUMN = "serviceId";
    private static final String SERVICE_NAME_COLUMN = "serviceName";
    private static final String SERVICE_DOCTOR_COLUMN = "doctor";
    private static final String SERVICE_PATIENT_COLUMN = "patient";
    private static final String SERVICE_PRICE_COLUMN = "price";
    private static final String SERVICE_DATE_COLUMN = "serviceDate";
    private static final String SERVICE_DESCRIPTION_COLUMN = "serviceDescription";



    public DoctorDAO(Connection connection) {
        super(connection);
    }


    public boolean addDoctor(String name, String surname, String prof) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_ADD_DOCTOR, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, prof);
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Problems with addMedService to database.", e);
        }
    }

    public List<Doctor> findAllDoctors() throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_INCOME_MESSAGES_BY_ACCOUNT_ID)) {
            List<Doctor> messages = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Doctor medService = new Doctor(
                        resultSet.getInt("doctorId"),
                        resultSet.getString("doctorName"),
                        resultSet.getString("doctorSurname"),
                        resultSet.getString("prof"));
                messages.add(medService);
            }
            return messages;
        } catch (SQLException e) {
            throw new DAOException("Problems with finding messages by account id and in database.", e);
        }
    }

    public boolean changeDoctor(String name, String surname, String prof) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_SERVICE_BY_ID, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, prof);
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Problems with addMedService to database.", e);
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
