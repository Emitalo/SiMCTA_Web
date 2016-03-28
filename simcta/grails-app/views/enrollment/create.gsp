<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'enrollment.label', default: 'Enrollment')}" />
        <title><g:message code="enrollment.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-enrollment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="enrollment.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-enrollment" class="content scaffold-create" role="main">
            <h1><g:message code="Adicionar cursos, pacotes e pagamento para ${studentName}" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.enrollment}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.enrollment}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>

            <g:form action="save">

                <g:hiddenField id="status" name="student" value="${student}"/>

                <fieldset class="form">
                    <f:all bean="enrollment"/>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'enrollment.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
