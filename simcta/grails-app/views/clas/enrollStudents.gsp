<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'student.label', default: 'Student')}" />
        <title><g:message code="student.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="row">
            <a href="#list-student" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
            <div class="nav" role="navigation">
                <ul>
                    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                    <li><g:link class="create" action="create"><g:message code="student.new.label" args="[entityName]" /></g:link></li>

                    <li><g:link class="create" action="showDeactivated"><g:message code="student.list.deactivated.label" args="[entityName]" /></g:link></li>
                </ul>
            </div>
        </div>

        <br>
        <div class="row">
            <div id="list-student" class="content scaffold-list" role="main">
                <h1><g:message code="Alunos matriculados no curso ${course.name}" /></h1>
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>

                <g:each in="${studentList}">        

                    <div class="col-xs-6 col-md-3" align="center">
                        <div class="thumbnail">
                        <div class="caption">
                            <h3><a href="/student/show/${it.id}">${it.name}</a></h3>
                            <b><h2>CPF:</b> ${it.cpf}</h2></b>
                            <br>
                            
                            <g:form controller="studentClass" action="enroll">

                                <g:hiddenField id="clasId" name="clasId" value="${clas.id}"/>
                                <g:hiddenField id="studentId" name="studentId" value="${it.id}"/>

                                <input class="btn btn-primary" type="submit" value="Matricular" />
                            </g:form>
                        </div>
                        </div>
                    </div>

                </g:each>

            </div>
        </div>
    </body>
</html>