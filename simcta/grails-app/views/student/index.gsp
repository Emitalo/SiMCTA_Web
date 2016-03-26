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
                </ul>
            </div>
        </div>

        <br>
        <div class="row">
            <g:form action="search">
                <div class="col-md-4">
                    <div class="input-group">
                    
                        <g:textField class="form-control" placeholder="Pesquisar aluno" name="name" />
                        
                        <span class="input-group-btn">
                            <g:submitButton class="btn btn-primary" name="search" value="Pesquisar"/>
                        </span>

                    </div>
                </div>
            </g:form>
        </div>

        <br>
        <div class="row">
            <div id="list-student" class="content scaffold-list" role="main">
                <h1><g:message code="student.list.label" args="[entityName]" /></h1>
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>
                <f:table collection="${studentList}" />

                <div class="pagination">
                    <g:paginate total="${studentCount ?: 0}" />
                </div>
            </div>
        </div>
    </body>
</html>