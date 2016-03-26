package com.simcta

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PackgeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Packge.list(params), model:[packgeCount: Packge.count()]
    }

    def search(){
        def name = params.name
        def active = params.active

        def like = "%" + name + "%";

        def packges
        def view

        packges = Packge.findAllByActiveAndNameIlike(true, like)
        view = "index"

        render view: view, model: [packgeList: packges, packgeCount: packges.size()]

    }

    def show(Packge packge) {
        respond packge
    }

    def create() {
        respond new Packge(params)
    }

    @Transactional
    def save(Packge packge) {
        if (packge == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (packge.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond packge.errors, view:'create'
            return
        }

        // Set duration based on courses duration
        packge.duration = calculateDuration(packge)

        packge.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'packge.label', default: 'Packge'), packge.id])
                redirect packge
            }
            '*' { respond packge, [status: CREATED] }
        }
    }

    def edit(Packge packge) {
        respond packge
    }

    @Transactional
    def update(Packge packge) {
        if (packge == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (packge.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond packge.errors, view:'edit'
            return
        }

        // Set duration based on courses duration
        packge.duration = calculateDuration(packge)

        packge.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'packge.label', default: 'Packge'), packge.id])
                redirect packge
            }
            '*'{ respond packge, [status: OK] }
        }
    }

    @Transactional
    def delete(Packge packge) {

        if (packge == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        packge.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'packge.label', default: 'Packge'), packge.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'packge.label', default: 'Packge'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
    @Transactional
    def updateStatus(Packge packge) {

        boolean status = packge.active
        boolean newStatus = false

        if(status == true){
            newStatus = false
        }
        else{
            newStatus = true
        }

        if(packge != null){
            
            packge.active = newStatus
            packge.save(insert: false) 
            redirect action: "show", id: packge.id
        }
       
    }

    private calculateDuration(Packge packge){

        int duration = 0

        for(course in packge.courses) {
                
            duration += course.duration

        }

        return duration
    }
}
