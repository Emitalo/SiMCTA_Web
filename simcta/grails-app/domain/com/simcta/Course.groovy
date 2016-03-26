package com.simcta

class Course extends ServiceItem{

	String description

    static hasMany = [classes: Clas, packge: Packge]
    static belongsTo = [Enrollment, Packge]

    static constraints = {	
    	description blank:true, nullable:true
    	packge nullable:true
    }

    String toString(){
        
    	return "Curso" + name
    }

}
