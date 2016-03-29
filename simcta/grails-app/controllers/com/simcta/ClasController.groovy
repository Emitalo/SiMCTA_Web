package com.simcta

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
@Secured(['ROLE_SECRETARY'])
class ClasController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", activate: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        // Get the active classes
        respond Clas.findAllByActive(true, params), model:[clasCount: Clas.count()]
    }

    def show(Clas clas) {
        respond clas
    }

    def showDeactivated(){

        respond Clas.findAllByActive(false)
    }

    def create() {
        respond new Clas(params)
    }

    @Transactional
    def save(Clas clas) {


        if (clas == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (clas.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond clas.errors, view:'create'
            return
        }

        clas.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'clas.label', default: 'Clas'), clas.id])
                redirect clas
            }
            '*' { respond clas, [status: CREATED] }
        }
    }

    def edit(Clas clas) {
        respond clas
    }

    @Transactional
    def update(Clas clas) {
        if (clas == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (clas.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond clas.errors, view:'edit'
            return
        }

        clas.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'clas.label', default: 'Clas'), clas.id])
                redirect clas
            }
            '*'{ respond clas, [status: OK] }
        }
    }

    @Transactional
    def delete(Clas clas) {

        if (clas == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        def classStudents = StudentClass.findAllByClas(clas)

        def allDataAssigned = false
        for(student in classStudents){
            if(student.situation == null || student.situation.empty){
                allDataAssigned = false
                break
            }else{
                allDataAssigned = true
            }
        }

        if(allDataAssigned){

            // If is active, deactivate, if is not, activate
            clas.active = !clas.active

            clas.save flush:true

            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'clas.changeStatus.message', args: [message(code: 'clas.label', default: 'Clas'), clas.id])
                    redirect action:"index", method:"GET"
                }
                '*'{ render status: NO_CONTENT }
            }
        }else{
            
            flash.message = message(code: "Para fechar a turma todas as notas e faltas devem estar lançadas.")
            redirect action: "closeClass", id: clas.id
        }
    } 

    def enrollStudents(Clas clas){

        def courseEnrollments = clas.course.enrollments

        def students = []
        for(enrollment in courseEnrollments){

            def student = enrollment.student

            def studentClasses = StudentClass.findAllByStudent(student)
            
            // Student isnt enrolled to any classes
            if(studentClasses == null || studentClasses.empty){
                students.add(student)
            }else{
                def isEnrolled = true
                for(studentClass in studentClasses){

                    // Student is already enrolled in this classes
                    if(studentClass.clas.id == clas.id){
                        isEnrolled = true
                        break;
                    }else{
                        isEnrolled = false
                    }
                }

                // If is not enrolled in the class, add it to appear on the page
                if(!isEnrolled){
                    students.add(student)
                }
            }
        }

        // Get all students who hired a package with clas course
        def allEnrollments = Enrollment.list()
        for(enrollment in allEnrollments){

            for(item in enrollment.serviceItens){

                if(item.getClass() == new Packge().getClass()){

                    for(course in item.courses){
                        
                        if(course.id == clas.course.id){

                            def student = enrollment.student

                            def studentClasses = StudentClass.findAllByStudent(student)

                            // Student isnt enrolled to any classes
                            if(studentClasses == null || studentClasses.empty){
                                students.add(student)
                            }else{
                                def isEnrolled = true
                                for(studentClass in studentClasses){

                                    // Student is already enrolled in this class
                                    if(studentClass.clas.id == clas.id){
                                        isEnrolled = true
                                        break;
                                    }else{
                                        isEnrolled = false
                                    }
                                }

                                // If is not enrolled in the class, add it to appear on the page
                                if(!isEnrolled){
                                    students.add(student)
                                }
                            }

                        }
                    }
                }
            }
        }
        
        render view: "enrollStudents", model: [studentList: students, clas: clas, course: clas.course]
    }

    def closeClass(Clas clas){

        def studentClasses = StudentClass.findAllByClas(clas)

        def studentData = [:]
        def students = []
        for(studentClass in studentClasses){

            students.add(studentClass.student)
            studentData[studentClass.student.id] = studentClass
        }

        render view: "closeClass", model: [studentList: students, clas: clas, studentData: studentData]
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'clas.label', default: 'Clas'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
