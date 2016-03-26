package com.simcta

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class StudentController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Student.findAllByActive(true, params), model:[studentCount: Student.count()]
    }

    def search(){
        def name = params.name
        def status = params.status

        def like = "%" + name + "%";

        def students
        def view

        if(status == "true"){

            students = Student.findAllByActiveAndNameIlike(true, like)
            view = "index"
        }else{
            
            students = Student.findAllByActiveAndNameIlike(false, like)
            view = "showDeactivated"
        }

        render view: view, model: [studentList: students, studentCount: students.size()]
    }

    def show(Student student) {
        respond student
    }

    def showDeactivated(){

        respond Student.findAllByActive(false)
    }

    def create() {
        respond new Student(params)
    }

    @Transactional
    def save(Student student) {
        if (student == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (student.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond student.errors, view:'create'
            return
        }

        student.save flush:true

        flash.message = message(code: 'default.created.message', args: [message(code: 'student.label', default: 'Student'), student.id])

        redirect controller: "enrollment",  action: "create" , params: [student: student.id, studentName: student.name]
    }

    def edit(Student student) {
        respond student
    }

    @Transactional
    def update(Student student) {
        if (student == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (student.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond student.errors, view:'edit'
            return
        }

        student.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'student.label', default: 'Student'), student.id])
                redirect student
            }
            '*'{ respond student, [status: OK] }
        }
    }

    @Transactional
    def delete(Student student) {

        if (student == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        // If is active, deactivate, if is not, activate
        student.active = !student.active

        student.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'student.changeStatus.message', args: [message(code: 'student.label', default: 'Student'), student.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'student.label', default: 'Student'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
