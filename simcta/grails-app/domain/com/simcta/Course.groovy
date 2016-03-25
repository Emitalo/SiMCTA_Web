package com.simcta

class Course extends ServiceItem{

	String description

	static hasMany = [classes: Clas]

    static constraints = {	
    	description blank:true, nullable:true
    }

    public String toString(){

    	return this.name
    }
}
