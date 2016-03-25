package com.simcta

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ClasController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Clas.list(params), model:[clasCount: Clas.count()]
    }

    def show(Clas clas) {
        respond clas
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

        clas = generateDependentFields(clas)

        clas.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'clas.label', default: 'Clas'), clas.id])
                redirect clas
            }
            '*' { respond clas, [status: CREATED] }
        }
    }

    def generateDependentFields(Clas clas){

        clas.endDate = clas.startDate.plus(clas.course.duration*7)
        clas.classId = generateClassId(clas)

        return clas
    }

    def generateClassId(Clas clas){

        def startDate = clas.startDate

        def shift = clas.shift
        shift = shift[0] + shift[1]
        shift = shift.toUpperCase()

        def courseName = clas.course.name
        courseName = courseName.split(" ");
        
        def courseFirstName = courseName[0].toUpperCase()

        def classId = courseFirstName + "-" + shift + " " + startDate.format("dd/mm/YY")

        return classId
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

        clas = generateDependentFields(clas)

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

        clas.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'clas.label', default: 'Clas'), clas.id])
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
