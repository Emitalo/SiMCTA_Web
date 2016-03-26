package com.simcta

// The real name is Package
class Packge extends ServiceItem{

	static hasMany = [courses: Course]

	static belongsTo = Enrollment

    static constraints = {
    	duration nullable: true, display: false// To set on controller
    }

    public String toString(){
    	return "Pacote - " + name
    }
}
