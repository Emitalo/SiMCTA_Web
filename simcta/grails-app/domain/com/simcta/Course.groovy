package com.simcta

class Course extends ServiceItem{

	String description

    static hasMany = [classes: Clas, packge: Packge]
	static belongsTo = Packge
    
    static constraints = {	
    	description blank:true, nullable:true
    	packge nullable:true
    }
    
    String toString(){
    	return this.name
    }

}
