package by.training.zakharchenya.courseproject.action;

import by.training.zakharchenya.courseproject.action.command.*;
import by.training.zakharchenya.courseproject.action.command.change.*;
import by.training.zakharchenya.courseproject.action.command.doctor.AddDoctorCommand;
import by.training.zakharchenya.courseproject.action.command.doctor.ChangeDoctorCommand;
import by.training.zakharchenya.courseproject.action.command.doctor.DeleteDoctorCommand;
import by.training.zakharchenya.courseproject.action.command.medservice.ChangeServiceCommand;
import by.training.zakharchenya.courseproject.action.command.medservice.DeleteMessageCommand;
import by.training.zakharchenya.courseproject.action.command.medservice.NewServiceCommand;
import by.training.zakharchenya.courseproject.action.command.medservice.UpdateMessageCommand;
import by.training.zakharchenya.courseproject.action.command.sign.SignInCommand;
import by.training.zakharchenya.courseproject.action.command.sign.SignUpCommand;
import javax.servlet.http.HttpServletRequest;
import static by.training.zakharchenya.courseproject.action.CommandType.*;

/**Class, which interpret command param from client
 * and return corresponding command object
 * @author Vadim Zakharchenya
 * @version 1.0
 */
public class CommandFactory {
    /**Interpret command param from client
     * and return corresponding class
     * @param request from client
     * @return command object
     */
    public Command getCurrentCommand(HttpServletRequest request) {
        String command = request.getParameter(COMMAND_PARAM);
        switch (command) {
            case LOCALE:
                return new LocaleCommand();
            case LOGIN:
                return new SignInCommand();
            case LOGOUT:
                return new LogoutCommand();
            case NULL:
                return new ErrorCommand();
            case SINGUP:
                return new SignUpCommand();
            case LOAD_IMAGE:
                return new LoadImageCommand();
            case CHANGE_AVATAR:
                return new ChangeAvatarCommand();
            case RESET_AVATAR:
                return new ResetAvatarCommand();
            case NEW_MESSAGE:
                return new NewServiceCommand();
            case DELETE_MESSAGE:
                return new DeleteMessageCommand();
            case UPDATE_MESSAGE:
                return new UpdateMessageCommand();
            case CHANGE_NAME:
                return new ChangeNameCommand();
            case CHANGE_PASSWORD:
                return new ChangePasswordCommand();
            case CHANGE_SERVICE:
                return new ChangeServiceCommand();
            case ADD_DOCTOR:
                return new AddDoctorCommand();
            case DELETE_DOCTOR:
                return new DeleteDoctorCommand();
            case CHANGE_DOCTOR:
                return new ChangeDoctorCommand();
        }
        return null;
    }
}

