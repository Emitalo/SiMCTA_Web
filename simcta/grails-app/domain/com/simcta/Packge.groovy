package com.simcta

// The real name is Package
class Packge extends ServiceItem{

	static hasMany = [courses: Course]
    static constraints = {
    	duration nullable: true, display: false// To set on controller
    }
}
