package by.training.zakharchenya.courseproject.dao;

import by.training.zakharchenya.courseproject.entity.Doctor;
import by.training.zakharchenya.courseproject.entity.Fieeld;
import by.training.zakharchenya.courseproject.entity.SomeEntity;
import by.training.zakharchenya.courseproject.exception.DAOException;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntityDAO extends AbstractDAO{
    private static final String SQL_ADD_DOCTOR ="INSERT INTO `doctor` (`doctorName`, `doctorSurname`, `prof`) "
            +"VALUES (?, ?, ?)";

    private static final String SQL_FIND_INCOME_MESSAGES_BY_ACCOUNT_ID =
            "SELECT  * FROM `doctor`";

    private static final String SQL_GET_ALL_FIELDS =
            "SELECT `fieldId`, `fieldName`, `fieldRealName` FROM `tablefield`";

    private static final String SQL_GET_ALL_ENTITIES =
            "SELECT ?, ?, ?, ? FROM ?";

    private static final String SQL_GET_ENTITY_NAME =
            "SELECT `tableName` FROM `table`";

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



    public EntityDAO(Connection connection) {
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

    public String getEntityName() throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ENTITY_NAME)) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getString("tableName");
        } catch (SQLException e) {
            throw new DAOException("Problems with finding messages by account id and in database.", e);
        }
    }
//"SELECT `fieldId`, `fieldName`, `fieldRealName` FROM `tablefield`";
    public List<Fieeld> findAllFields() throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_FIELDS)) {
            List<Fieeld> messages = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Fieeld medService = new Fieeld(
                        resultSet.getInt("fieldId"),
                        resultSet.getString("fieldName"),
                        resultSet.getString("fieldRealName"));
                messages.add(medService);
            }
            return messages;
        } catch (SQLException e) {
            throw new DAOException("Problems with finding messages by account id and in database.", e);
        }
    }

    public List<SomeEntity> findAllEntities(List<Fieeld> fields, String tableName) throws DAOException {
        String query = "SELECT `"+ fields.get(0).getFieldName()
                + "`, `" +fields.get(1).getFieldName()
                + "`, `" +fields.get(2).getFieldName()
                + "`, `" +fields.get(3).getFieldName()
                + "` FROM `" + tableName + "`";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            /*statement.setString(1, fields.get(0).getFieldName());
            statement.setString(2, fields.get(1).getFieldName());
            statement.setString(3, fields.get(2).getFieldName());
            statement.setString(4, fields.get(3).getFieldName());
            statement.setString(5, tableName);*/
            List<SomeEntity> messages = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                SomeEntity medService = new SomeEntity(
                        resultSet.getString(fields.get(0).getFieldName()),
                        resultSet.getString(fields.get(1).getFieldName()),
                        resultSet.getString(fields.get(2).getFieldName()),
                        resultSet.getString(fields.get(3).getFieldName()));
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

}
