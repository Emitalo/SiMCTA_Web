<f:field bean="course" property="value" label="${message(code: 'course.value', default: 'Value')}">
    <g:textField name="value" value="${value}"/>
</f:field>

<f:field bean="course" property="duration" label="${message(code: 'course.duration', default: 'Course')}">
    <g:textField name="duration" value="${value}"/>
</f:field>

<f:field bean="course" property="description" label="${message(code: 'course.description', default: 'Course')}">
    <g:textField name="description" value="${value}"/>
</f:field>

<f:field bean="course" property="active" label="${message(code: 'course.active', default: 'Course')}">
    <g:checkBox name="active" value="${value}"/>
</f:field>