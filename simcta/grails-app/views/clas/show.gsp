<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'clas.label', default: 'Clas')}" />
        <title><g:message code="clas.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-clas" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="clas.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="clas.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-clas" class="content scaffold-show" role="main">
            <h1><g:message code="clas.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
            <f:display bean="clas" />

                <g:form resource="${this.clas}" action="delete" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.clas}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    
                    <g:if test="${clas.active == true}">
                        <input class="delete" type="submit" value="${message(code: 'clas.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'clas.button.delete.confirm.message', default: 'Tem certeza?')}');" />
                    </g:if>
                    <g:else>
                        <input class="delete" type="submit" value="${message(code: 'clas.button.activate.label', default: 'Ativar')}" onclick="return confirm('${message(code: 'clas.button.activate.confirm.message', default: 'Tem certeza?')}');" />
                
                    </g:else>

                    <g:link class="btn btn-default" action="enrollStudents" resource="${this.clas}"><g:message code="clas.button.enroll.label" default="Matricular alunos" /></g:link>

                    <g:link class="btn btn-default" action="closeClass" resource="${this.clas}"><g:message code="clas.button.close.label" default="Fechar turma" /></g:link>
                </fieldset>
                </g:form>
        </div>
    </body>
</html>
