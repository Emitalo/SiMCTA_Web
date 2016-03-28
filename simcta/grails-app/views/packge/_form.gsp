<f:field bean="packge" property="value" label="${message(code: 'packge.value', default: 'Value')}">
    <g:textField name="value" value="${value}"/>
</f:field>

<f:field bean="packge" property="duration" label="${message(code: 'packge.duration', default: 'packge')}">
    <g:textField name="duration" value="${value}"/>
</f:field>

<f:field bean="packge" property="active" label="${message(code: 'packge.active', default: 'packge')}">
    <g:checkBox name="active" value="${value}"/>
</f:field>

<f:field bean="packge" property="courses" label="${message(code: 'packge.courses', default: 'packge')}">
    
    <g:select name="course.id"
                    from="${content_hub_admin.Course.list()}" optionKey="id"
                    value="${packge?.course?.id}" />
</f:field>