package com.simcta

class Course extends ServiceItem{

	String description

    static constraints = {	
    	description blank:true, nullable:true
    }
}
