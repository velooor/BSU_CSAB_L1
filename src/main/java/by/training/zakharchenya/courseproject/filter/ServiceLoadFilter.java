package by.training.zakharchenya.courseproject.filter;

import by.training.zakharchenya.courseproject.dao.MedServiceDAO;
import by.training.zakharchenya.courseproject.entity.Account;
import by.training.zakharchenya.courseproject.entity.Doctor;
import by.training.zakharchenya.courseproject.entity.MedService;
import by.training.zakharchenya.courseproject.entity.Message;
import by.training.zakharchenya.courseproject.logic.DoctorService;
import by.training.zakharchenya.courseproject.logic.MailLogic;
import by.training.zakharchenya.courseproject.logic.MedServiceLogic;
import by.training.zakharchenya.courseproject.servlet.Constants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Filter class, serves to upload list of messages before opening corresponding pages.
 * @author Vadim Zakharchenya
 * @version 1.0
 */
@WebFilter(filterName = "ServiceLoadFilter", urlPatterns = {"/jsp/user/messages.jsp"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class ServiceLoadFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession ses = request.getSession();

        List<MedService> allMessages;
        allMessages = MedServiceLogic.loadMedServices();

        List<Doctor> all;
        all = DoctorService.loadDoctors();

        ses.setAttribute(Constants.DOCTORS_KEY, all);
        ses.setAttribute(Constants.READ_MESSAGES_KEY, allMessages);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

}