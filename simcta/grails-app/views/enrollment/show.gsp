<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'enrollment.label', default: 'Enrollment')}" />
        <title><g:message code="enrollment.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-enrollment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="enrollment.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="enrollment.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-enrollment" class="content scaffold-show" role="main">
            <h1><g:message code="enrollment.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:display bean="enrollment" />
            <g:form resource="${this.enrollment}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.enrollment}"><g:message code="enrollment.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'enrollment.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'enrollment.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
