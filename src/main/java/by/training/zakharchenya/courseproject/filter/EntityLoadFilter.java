package by.training.zakharchenya.courseproject.filter;

import by.training.zakharchenya.courseproject.entity.Account;
import by.training.zakharchenya.courseproject.entity.Doctor;
import by.training.zakharchenya.courseproject.entity.Fieeld;
import by.training.zakharchenya.courseproject.entity.SomeEntity;
import by.training.zakharchenya.courseproject.logic.DoctorService;
import by.training.zakharchenya.courseproject.logic.SomeEntityLogic;
import by.training.zakharchenya.courseproject.servlet.Constants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "EntityLoadFilter", urlPatterns = {"/jsp/user/someentity.jsp"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class EntityLoadFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession ses = request.getSession();
        String name= SomeEntityLogic.getEntityName();
        List<Fieeld> fields = SomeEntityLogic.getFields();
        List<SomeEntity> all;
        all = SomeEntityLogic.loadEntities(fields, name);

        ses.setAttribute("entities", all);
        ses.setAttribute("field1", fields.get(0).getFieldRealName());
        ses.setAttribute("field2", fields.get(1).getFieldRealName());
        ses.setAttribute("field3", fields.get(2).getFieldRealName());
        ses.setAttribute("field4", fields.get(3).getFieldRealName());

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

}