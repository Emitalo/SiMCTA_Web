package com.simcta

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
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
