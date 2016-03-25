package com.simcta

import  com.simcta.Person

class Teacher extends Person{

	String qualification

    static constraints = {
    	qualification nullable: false
    }
}
