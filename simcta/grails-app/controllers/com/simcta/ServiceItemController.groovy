package com.simcta

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ServiceItemController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ServiceItem.list(params), model:[serviceItemCount: ServiceItem.count()]
    }

    def show(ServiceItem serviceItem) {
        respond serviceItem
    }

    def create() {
        respond new ServiceItem(params)
    }

    @Transactional
    def save(ServiceItem serviceItem) {
        if (serviceItem == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (serviceItem.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond serviceItem.errors, view:'create'
            return
        }

        serviceItem.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'serviceItem.label', default: 'ServiceItem'), serviceItem.id])
                redirect serviceItem
            }
            '*' { respond serviceItem, [status: CREATED] }
        }
    }

    def edit(ServiceItem serviceItem) {
        respond serviceItem
    }

    @Transactional
    def update(ServiceItem serviceItem) {
        if (serviceItem == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (serviceItem.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond serviceItem.errors, view:'edit'
            return
        }

        serviceItem.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'serviceItem.label', default: 'ServiceItem'), serviceItem.id])
                redirect serviceItem
            }
            '*'{ respond serviceItem, [status: OK] }
        }
    }

    @Transactional
    def delete(ServiceItem serviceItem) {

        if (serviceItem == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        serviceItem.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'serviceItem.label', default: 'ServiceItem'), serviceItem.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'serviceItem.label', default: 'ServiceItem'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
