<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'course.label', default: 'Course')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-course" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-course" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <div id="show">
                <b>${message(code: 'course.name', default: 'Name')}:</b>
                <g:fieldValue bean="${course}" field="name"/>
                <br>
                <b>${message(code: 'course.value', default: 'Value')}:</b>
                <g:fieldValue bean="${course}" field="value"/>
                <br>
                <b>${message(code: 'course.duration', default: 'Duration')}:</b>
                <g:fieldValue bean="${course}" field="duration"/>
                <br>
                <b>${message(code: 'course.description', default: 'Description')}:</b>
                <g:fieldValue bean="${course}" field="description"/>
                <br>
                <b>${message(code: 'course.active', default: 'Active')}:</b>
                    
                    <g:if test="${course.active == true}">
                        Sim
                    </g:if>

                    <g:else>
                        Não
                    </g:else>
            </div>
            <g:form resource="${this.course}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.course}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    
                    <g:link class="edit" action="updateStatus" resource="${this.course}">
                    
                    <g:if test="${course.active == false}">
                        <g:message code="default.button.active.status.label" default="Update status" />
                    </g:if>

                    <g:else>
                        <g:message code="default.button.deactive.status.label" default="Update status" />
                    </g:else>
                    
                    </g:link>

                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
