package com.simcta

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
@Secured(['ROLE_SECRETARY'])
class HomeController {

    def index() {
        
    }

}
