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

                           <div class="form-group-lg">
                               <input type="text" name="patientId" class="form-control" placeholder="<fmt:message key="label.patient.id"/>*"required/>
                           </div>
                           <div class="form-group-lg">
                               <input type="date" name="serviceDate" class="form-control input" placeholder="<fmt:message key="label.service.date"/>*"  min="1850-01-01" max="2013-01-01" required>
                           </div>
                           <div class="form-group-lg">
                               <input type="text" name="doctorId" class="form-control" placeholder="<fmt:message key="label.doctor.id"/>*" required/>
                           </div>
                           <div class="form-group-lg">
                               <input type="number" name="price" class="form-control"  placeholder="<fmt:message key="label.service.price"/>*" required/>
                           </div>
                           <div class="form-group-lg">
                               <input type="text" name="description" class="form-control" placeholder="<fmt:message key="label.service.descrption"/>*" required/>
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
               <h4 class="panel-heading col-lg-7">${service.serviceName}</h4>
               <h4 class="panel-heading col-lg-5"><fmt:message key="label.title.from"/> ${service.patient} ${service.doctor} (${service.price})</h4>
               <div class="panel-body">

                   <div class="col-lg-3">
                       <label><fmt:message key="label.creating.time"/>:</label>
                   </div>
                   <div class="col-lg-2">
                       <button type="button" onclick="window.location.href='${context}/main?command=deleteMessage&messageid=${service.serviceId}'" class="btn btn-default pull-right"><fmt:message key="label.delete.message"/></button>
                       <br>
                       <button type="button" onclick="window.location.href='${context}/main?command=updateIsReadMessage&messageid=${service.serviceId}&isRead=false'" class="btn btn-primary pull-right"><fmt:message key="label.markasunread.message"/></button>
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
