<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ttll" uri="http://example.com/elgrand" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><fmt:message key="label.messages"/></title>

    <!-- Bootstrap -->
    <link href="${context}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="${context}/css/style.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Chewy" rel="stylesheet">
    <link rel="shortcut icon" href="${context}/css/mainFavicon.png" type="image/png">
    <script type="text/javascript" src="${context}js/registration.js"></script>

    <link rel="stylesheet" media="all" href="${context}css/animate.css">
    <script src="${context}js/wow.min.js"></script>
    <script>new WOW().init();</script>
</head>
<body>
<c:choose>
    <c:when test="${visitor.role == 'GUEST'}">
        <jsp:forward page="/jsp/guest/welcome.jsp"/>
    </c:when>
</c:choose>
<%@include file="../../WEB-INF/jspf/header.jsp"%>
<main class="container">
        <div class="visible-lg-inline-block">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".new-message-modal-lg"><fmt:message key="label.new.service"/></button>
        </div>
        <div class="visible-lg-inline-block">
            <p class="message">
                ${newMessageMessage}
            </p>
        </div>

    <div class="modal fade new-message-modal-lg" tabindex="-1" role="dialog" aria-labelledby="NewMessage">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title"><fmt:message key="label.new.service"/></h3>
                </div>
                <div class="modal-body">
                       <form id="newMessageForm" name="newMessageForm" action="/main" method="post" onsubmit="return validateForm ( )">


                           <div class="form-group">
                               <input type="text" id="serviceName" name="serviceName" class="form-control"  value="" placeholder="<fmt:message key="label.service.name"/>" minlength="2" maxlength="30">
                           </div>

                           <%--<div class="form-group-lg">--%>
                               <%--<input type="text" name="patientId" class="form-control" placeholder="<fmt:message key="label.patient.id"/>*"required/>--%>
                           <%--</div>--%>
                           <select name="patientId" class="form-control">
                               <c:forEach var="item" items="${allUsers}">
                                   <option value="${item.accountId}">${item.name} ${item.surname}</option>
                               </c:forEach>
                           </select>
                           <div class="form-group-lg">
                               <input type="date" name="serviceDate" class="form-control input" placeholder="<fmt:message key="label.service.date"/>*"  min="1850-01-01" max="2017-01-01" required>
                           </div>
                           <%--<div class="form-group-lg">
                               <input type="text" name="doctorId" class="form-control" placeholder="<fmt:message key="label.doctor.id"/>*" required/>
                           </div>--%>
                           <select name="doctorId" class="form-control">
                               <c:forEach var="item" items="${doctors}">
                                   <option value="${item.doctorId}">${item.doctorName} ${item.doctorSurname} (${item.prof})</option>
                               </c:forEach>
                           </select>
                           <div class="form-group-lg">
                               <input type="number" name="price" class="form-control"  placeholder="<fmt:message key="label.service.price"/>*" required/>
                           </div>
                           <div class="form-group-lg">
                               <textarea id="description" name="description" class="form-control" rows="5" placeholder="<fmt:message key="label.service.descrption"/>" minlength="1" maxlength="5000" required></textarea>
                           </div>
                           <input type="hidden" name="command" value="newmessage">
                           <p class="message">
                               ${NewMessageMessage}
                           </p>
                           <hr>
                       </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="label.close"/></button>
                    <button type="submit" form="newMessageForm" class="btn btn-primary"><fmt:message key="label.submit"/></button>
                </div>
            </div>

        </div>
    </div>


   <div class="clearfix pulse animated">
       <div class="page-header text-center"></div>
       <c:forEach var="service" items="${readMessages}">
           <div class="panel panel-default col-lg-12">
               <h4 class="panel-heading col-lg-5"><fmt:message key="label.service.id"/>: ${service.serviceId}</h4>
               <h4 class="panel-heading col-lg-5"><fmt:message key="label.service.name"/>: ${service.serviceName}</h4>
                <div class="panel-body">
                    <h6 class="panel-heading col-lg-5"><fmt:message key="label.patient.id"/>: ${service.patient}</h6>
                    <h6 class="panel-heading col-lg-5"><fmt:message key="label.doctor.id"/>: ${service.doctor}</h6>
                    <h6 class="panel-heading col-lg-5"><fmt:message key="label.service.price"/>: ${service.price}</h6>
                    <h6 class="panel-heading col-lg-5"><fmt:message key="label.service.date"/>: ${service.serviceDate}</h6>
                    <h6 class="panel-heading col-lg-5"><fmt:message key="label.service.descrption"/>: ${service.description}</h6>

                    <div class="col-lg-2">
                       <button type="button" onclick="window.location.href='${context}/main?command=deleteMessage&messageid=${service.serviceId}'" class="btn btn-default pull-right"><fmt:message key="label.delete.service"/></button>

                        <br>
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".change-message-modal-lg${service.serviceId}"><fmt:message key="label.change.service"/></button>

                    </div>


                    <div class="modal fade change-message-modal-lg${service.serviceId}" tabindex="-1" role="dialog" aria-labelledby="NewMessage">
                        <div class="modal-dialog modal-lg" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h3 class="modal-title"><fmt:message key="label.change.service"/></h3>
                                </div>
                                <div class="modal-body">
                                    <form id="changeMessageForm" name="changeMessageForm" action="/main" method="post" onsubmit="return validateForm ( )">


                                        <div class="form-group">
                                            <input type="text" id="CHserviceName" name="CHserviceName" class="form-control"  value="" placeholder="<fmt:message key="label.service.name"/>" minlength="2" maxlength="30">
                                        </div>

                                        <%--<div class="form-group-lg">--%>
                                            <%--<input type="text" name="CHpatientId" class="form-control" placeholder="<fmt:message key="label.patient.id"/>*"required/>--%>
                                        <%--</div>--%>
                                        <select name="CHpatientId" class="form-control">
                                            <c:forEach var="item" items="${allUsers}">
                                                <option value="${item.accountId}">${item.name} ${item.surname}</option>
                                            </c:forEach>
                                        </select>
                                        <div class="form-group-lg">
                                            <input type="date" name="CHserviceDate" class="form-control input" placeholder="<fmt:message key="label.service.date"/>*"  min="1850-01-01" max="2017-01-01" required>
                                        </div>
                                        <%--<div class="form-group-lg">--%>
                                            <%--<input type="text" name="CHdoctorId" class="form-control" placeholder="<fmt:message key="label.doctor.id"/>*" required/>--%>
                                        <%--</div>--%>
                                        <select name="CHdoctorId" class="form-control">
                                            <c:forEach var="item" items="${doctors}">
                                                <option value="${item.doctorId}">${item.doctorName} ${item.doctorSurname} (${item.prof})</option>
                                            </c:forEach>
                                        </select>
                                        <div class="form-group-lg">
                                            <input type="number" name="CHprice" class="form-control"  placeholder="<fmt:message key="label.service.price"/>*" required/>
                                        </div>
                                        <div class="form-group-lg">
                                            <%--<input type="text" name="CHdescription" class="form-control" placeholder="<fmt:message key="label.service.descrption"/>*" required/>--%>
                                            <textarea id="CHdescription" name="CHdescription" class="form-control" rows="5" placeholder="<fmt:message key="label.service.descrption"/>" minlength="1" maxlength="5000" required></textarea>

                                        </div>
                                        <input type="hidden" name="CHserviceId" value="${service.serviceId}">
                                        <input type="hidden" name="command" value="CHmessage">
                                        <p class="message">
                                                ${NewMessageMessage}
                                        </p>
                                        <hr>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="label.close"/></button>
                                    <button type="submit" form="changeMessageForm" class="btn btn-primary"><fmt:message key="label.submit"/></button>
                                </div>
                            </div>

                        </div>
                    </div>


               </div>
           </div>
       </c:forEach>
   </div>

</main>
<%@include file="../../WEB-INF/jspf/footer.jsp"%>
<script src="${context}/js/jquery-3.2.0.js"></script>
<script src="${context}/js/jquery.form.min.js"></script>
<script src="${context}/js/bootstrap.min.js"></script>
</body>
</html>
