package com.simcta

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class EnrollmentController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def show(Enrollment enrollment) {
        respond enrollment
    }

    def create() {
        respond new Enrollment(params), model: [student: params.student, studentName: params.studentName]
    }

    @Transactional
    def save(Enrollment enrollment) {
        if (enrollment == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (enrollment.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond enrollment.errors, view:'create'
            return
        }

        def studentId = params.student

        enrollment.student = Student.get(studentId)

        enrollment.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'enrollment.label', default: 'Enrollment'), enrollment.id])
                redirect enrollment
            }
            '*' { respond enrollment, [status: CREATED] }
        }
    }

    def edit(Enrollment enrollment) {
        respond enrollment
    }

    @Transactional
    def update(Enrollment enrollment) {
        if (enrollment == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (enrollment.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond enrollment.errors, view:'edit'
            return
        }

        enrollment.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'enrollment.label', default: 'Enrollment'), enrollment.id])
                redirect enrollment
            }
            '*'{ respond enrollment, [status: OK] }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'enrollment.label', default: 'Enrollment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
