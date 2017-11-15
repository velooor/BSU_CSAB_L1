package by.training.zakharchenya.courseproject.action.command.doctor;

import by.training.zakharchenya.courseproject.action.Command;
import by.training.zakharchenya.courseproject.logic.DoctorService;
import by.training.zakharchenya.courseproject.manager.ConfigurationManager;
import by.training.zakharchenya.courseproject.servlet.Constants;

import javax.servlet.http.HttpServletRequest;

public class ChangeDoctorCommand  implements Command {

    private static final String NAME_PARAM = "CHdoctorName";
    private static final String SURNAME_PARAM = "CHdoctorSurname";
    private static final String PROF_PARAM = "CHprofession";

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

        DoctorService.changeDoctor( name,  surname,  prof);

        page = ConfigurationManager.getProperty(Constants.PAGE_DOCTORS);
        return page;
    }
}