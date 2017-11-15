package by.training.zakharchenya.courseproject.action.command.doctor;

import by.training.zakharchenya.courseproject.action.Command;
import by.training.zakharchenya.courseproject.entity.Visitor;
import by.training.zakharchenya.courseproject.logic.DoctorService;
import by.training.zakharchenya.courseproject.logic.MedServiceLogic;
import by.training.zakharchenya.courseproject.manager.ConfigurationManager;
import by.training.zakharchenya.courseproject.servlet.Constants;

import javax.servlet.http.HttpServletRequest;

public class DeleteDoctorCommand implements Command {

    private static final String MESSAGE_ID_PARAM = "doctorId";

    /**@param request from client
     * @return path for jsp file to be forwarded to
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page ;
        request.getSession().setAttribute(Constants.STATE_KEY, Constants.State.FORWARD);
        int messageID = Integer.valueOf(request.getParameter(MESSAGE_ID_PARAM));

        DoctorService.deleteDoctor(messageID);

        page = ConfigurationManager.getProperty(Constants.PAGE_DOCTORS);
        return page;
    }
}