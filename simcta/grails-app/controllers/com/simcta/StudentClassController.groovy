package com.simcta

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class StudentClassController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond StudentClass.list(params), model:[studentClassCount: StudentClass.count()]
    }

    def show(StudentClass studentClass) {
        respond studentClass
    }

    def create() {
        respond new StudentClass(params)
    }

    @Transactional
    def enroll(){

        def studentId = params.studentId
        def classId = params.clasId

        def studentClass = new StudentClass(student: Student.get(studentId), clas: Clas.get(classId))

        this.save(studentClass)
    }

    @Transactional
    def save(StudentClass studentClass) {
        if (studentClass == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (studentClass.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond studentClass.errors, view: 'enrollStudents', controller: "clas"
            return
        }

        studentClass.save flush: true
        
        flash.message = message(code: "Aluno matriculado na turma com sucesso!")

        redirect controller: "clas", action: "enrollStudents", id: studentClass.clas.id
    }

    def edit(StudentClass studentClass) {
        respond studentClass
    }

    @Transactional
    def update(StudentClass studentClass) {
        if (studentClass == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (studentClass.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond studentClass.errors, view:'edit'
            return
        }

        studentClass.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'studentClass.label', default: 'StudentClass'), studentClass.id])
                redirect studentClass
            }
            '*'{ respond studentClass, [status: OK] }
        }
    }

    @Transactional
    def delete(StudentClass studentClass) {

        if (studentClass == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        studentClass.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'studentClass.label', default: 'StudentClass'), studentClass.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'studentClass.label', default: 'StudentClass'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
