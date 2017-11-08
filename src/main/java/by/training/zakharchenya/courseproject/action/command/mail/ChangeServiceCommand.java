package by.training.zakharchenya.courseproject.action.command.mail;

import by.training.zakharchenya.courseproject.action.Command;
import by.training.zakharchenya.courseproject.entity.Account;
import by.training.zakharchenya.courseproject.entity.Visitor;
import by.training.zakharchenya.courseproject.logic.MedServiceLogic;
import by.training.zakharchenya.courseproject.manager.ConfigurationManager;
import by.training.zakharchenya.courseproject.servlet.Constants;

import javax.servlet.http.HttpServletRequest;

public class ChangeServiceCommand implements Command{

    private static final String ID_PARAM = "CHserviceId";
    private static final String NAME_PARAM = "CHserviceName";
    private static final String PATIENT_ID_PARAM = "CHpatientId";
    private static final String SERVICE_DATE_PARAM = "CHserviceDate";
    private static final String DOCTOR_ID_PARAM = "CHdoctorId";
    private static final String PRICE_PARAM = "CHprice";
    private static final String DESCRIPTION_PARAM = "CHdescription";

    /**@param request from client
     * @return path for jsp file to be forwarded to
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        request.getSession().setAttribute(Constants.STATE_KEY, Constants.State.FORWARD);
        int id = Integer.valueOf(request.getParameter(ID_PARAM));
        String serviceName = request.getParameter(NAME_PARAM);
        int patientId = Integer.valueOf(request.getParameter(PATIENT_ID_PARAM));
        String serviceDate = request.getParameter(SERVICE_DATE_PARAM);
        int doctorId = Integer.valueOf(request.getParameter(DOCTOR_ID_PARAM));
        double price = Double.valueOf(request.getParameter(PRICE_PARAM));
        String description = request.getParameter(DESCRIPTION_PARAM);

        MedServiceLogic.changeService(id, serviceName,  description,  price,  doctorId,  patientId,  serviceDate);

        page = ConfigurationManager.getProperty(Constants.PAGE_MESSAGES);
        return page;
    }
}
