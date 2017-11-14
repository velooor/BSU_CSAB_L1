package by.training.zakharchenya.courseproject.action.command.doctor;

import by.training.zakharchenya.courseproject.action.Command;
import by.training.zakharchenya.courseproject.entity.Account;
import by.training.zakharchenya.courseproject.entity.Visitor;
import by.training.zakharchenya.courseproject.logic.DoctorService;
import by.training.zakharchenya.courseproject.logic.MedServiceLogic;
import by.training.zakharchenya.courseproject.manager.ConfigurationManager;
import by.training.zakharchenya.courseproject.servlet.Constants;

import javax.servlet.http.HttpServletRequest;

public class AddDoctorCommand  implements Command {

    private static final String NAME_PARAM = "doctorName";
    private static final String SURNAME_PARAM = "doctorSurname";
    private static final String PROF_PARAM = "profession";

    /**@param request from client
     * @return path for jsp file to be forwarded to
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        request.getSession().setAttribute(Constants.STATE_KEY, Constants.State.FORWARD);
        String name = request.getParameter(NAME_PARAM);
        String surname = request.getParameter(SURNAME_PARAM);
        String prof = request.getParameter(PROF_PARAM);

        DoctorService.addDoctor( name,  surname,  prof);

        page = ConfigurationManager.getProperty(Constants.PAGE_DOCTORS);
        return page;
    }
}