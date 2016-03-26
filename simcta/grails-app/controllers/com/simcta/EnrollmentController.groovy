package com.simcta

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class EnrollmentController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Enrollment.list(params), model:[enrollmentCount: Enrollment.count()]
    }

    def show(Enrollment enrollment) {
        respond enrollment
    }

    def create() {
        respond new Enrollment(params)
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

    @Transactional
    def delete(Enrollment enrollment) {

        if (enrollment == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        enrollment.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'enrollment.label', default: 'Enrollment'), enrollment.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
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
