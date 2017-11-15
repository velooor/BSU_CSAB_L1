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
    <title><fmt:message key="label.doctors"/></title>

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
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".add-doctor-modal-lg"><fmt:message key="label.add"/></button>
    </div>
    <div class="visible-lg-inline-block">
        <p class="message">
            ${newMessageMessage}
        </p>
    </div>

    <div class="modal fade add-doctor-modal-lg" tabindex="-1" role="dialog" aria-labelledby="AddDoctor">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title"><fmt:message key="label.add"/></h3>
                </div>
                <div class="modal-body">
                    <form id="addDoctorForm" name="addDoctorForm" action="/main" method="post" onsubmit="return validateForm ( )">


                        <div class="form-group">
                            <input type="text" id="doctorName" name="doctorName" class="form-control"  value="" placeholder="<fmt:message key="label.first.name"/>" minlength="2" maxlength="30">
                        </div>

                        <div class="form-group">
                            <input type="text" id="doctorSurname" name="doctorSurname" class="form-control"  value="" placeholder="<fmt:message key="label.second.name"/>" minlength="2" maxlength="30">
                        </div>

                        <div class="form-group">
                            <select name="profession" class="form-control">
                                <option value="dentist">Dentist</option>
                                <option value="therapist">Therapist</option>
                                <option value="surgeon">Surgeon</option>
                                <option value="neurologist">Neurologist</option>
                            </select>
                        </div>

                        <input type="hidden" name="command" value="adddoctor">

                        <hr>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="label.close"/></button>
                    <button type="submit" form="addDoctorForm" class="btn btn-primary"><fmt:message key="label.submit"/></button>
                </div>
            </div>

        </div>
    </div>


    <div class="clearfix pulse animated">
        <div class="page-header text-center"></div>
        <c:forEach var="doctor" items="${doctors}">
            <div class="panel panel-default col-lg-12">
                <h4 class="panel-heading col-lg-5"><fmt:message key="label.doctor.id"/>: ${doctor.doctorId}</h4>
                <h4 class="panel-heading col-lg-5"><fmt:message key="label.first.name"/>: ${doctor.doctorName}</h4>
                <div class="panel-body">
                    <h6 class="panel-heading col-lg-5"><fmt:message key="label.second.name"/>: ${doctor.doctorSurname}</h6>
                    <h6 class="panel-heading col-lg-5"><fmt:message key="label.doctor.prof"/>: ${doctor.prof}</h6>

                    <div class="col-lg-2">
                        <button type="button" onclick="window.location.href='${context}/main?command=deleteDoctor&doctorId=${doctor.doctorId}'" class="btn btn-default pull-right"><fmt:message key="label.delete"/></button>

                        <br>
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".change-doctor-modal-lg${doctor.doctorId}"><fmt:message key="label.change.doctor"/></button>

                    </div>


                    <div class="modal fade change-doctor-modal-lg${doctor.doctorId}" tabindex="-1" role="dialog" aria-labelledby="NewMessage">
                        <div class="modal-dialog modal-lg" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h3 class="modal-title"><fmt:message key="label.change.service"/></h3>
                                </div>
                                <div class="modal-body">
                                    <form id="changeDoctorForm" name="changeDoctorForm" action="/main" method="post" onsubmit="return validateForm ( )">


                                        <div class="form-group">
                                            <input type="text" id="CHdoctorName" name="CHdoctorName" class="form-control"  value="" placeholder="<fmt:message key="label.first.name"/>" minlength="2" maxlength="30">
                                        </div>

                                        <div class="form-group">
                                            <input type="text" id="CHdoctorSurname" name="CHdoctorSurname" class="form-control"  value="" placeholder="<fmt:message key="label.second.name"/>" minlength="2" maxlength="30">
                                        </div>

                                        <select name="CHprofession" class="form-control">
                                            <option value="dentist">Dentist</option>
                                            <option value="therapist">Therapist</option>
                                            <option value="surgeon">Surgeon</option>
                                            <option value="neurologist">Neurologist</option>
                                        </select>

                                        <input type="hidden" name="command" value="changedoctor">
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="label.close"/></button>
                                    <button type="submit" form="changeDoctorForm" class="btn btn-primary"><fmt:message key="label.submit"/></button>
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
