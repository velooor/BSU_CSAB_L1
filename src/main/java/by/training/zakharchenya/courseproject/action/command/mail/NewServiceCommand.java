package by.training.zakharchenya.courseproject.action.command.mail;

import by.training.zakharchenya.courseproject.action.Command;
import by.training.zakharchenya.courseproject.entity.Account;
import by.training.zakharchenya.courseproject.entity.Visitor;
import by.training.zakharchenya.courseproject.logic.MailLogic;
import by.training.zakharchenya.courseproject.logic.MedServiceLogic;
import by.training.zakharchenya.courseproject.manager.ConfigurationManager;
import by.training.zakharchenya.courseproject.manager.MessageManager;
import by.training.zakharchenya.courseproject.servlet.Constants;

import javax.servlet.http.HttpServletRequest;

import static by.training.zakharchenya.courseproject.manager.MessageConstants.MESSAGE_DATABASE_PROBLEM;
import static by.training.zakharchenya.courseproject.manager.MessageConstants.MESSAGE_INCORRECT_LOGIN;
import static by.training.zakharchenya.courseproject.manager.MessageConstants.MESSAGE_WRONG_LOGIN;
import static by.training.zakharchenya.courseproject.servlet.Constants.NEWMESSAGE_MESSAGE;

/** Class serves to process the corresponding command: Create and save message
 * @author Vadim Zakharchenya
 * @version 1.0
 */
public class NewServiceCommand implements Command {

    private static final String NAME_PARAM = "serviceName";
    private static final String PATIENT_ID_PARAM = "patientId";
    private static final String SERVICE_DATE_PARAM = "serviceDate";
    private static final String DOCTOR_ID_PARAM = "doctorId";
    private static final String PRICE_PARAM = "price";
    private static final String DESCRIPTION_PARAM = "description";

    /**@param request from client
     * @return path for jsp file to be forwarded to
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        request.getSession().setAttribute(Constants.STATE_KEY, Constants.State.FORWARD);
        String serviceName = request.getParameter(NAME_PARAM);
        int patientId = Integer.valueOf(request.getParameter(PATIENT_ID_PARAM));
        String serviceDate = request.getParameter(SERVICE_DATE_PARAM);
        int doctorId = Integer.valueOf(request.getParameter(DOCTOR_ID_PARAM));
        double price = Double.valueOf(request.getParameter(PRICE_PARAM));
        String description = request.getParameter(DESCRIPTION_PARAM);


        Visitor visitor = (Visitor)request.getSession().getAttribute(Constants.VISITOR_KEY);
        Account currentAccount = (Account)request.getSession().getAttribute(Constants.ACCOUNT_KEY);

        MedServiceLogic.addService( serviceName,  description,  price,  doctorId,  patientId,  serviceDate);

        page = ConfigurationManager.getProperty(Constants.PAGE_MESSAGES);
        return page;
    }
}