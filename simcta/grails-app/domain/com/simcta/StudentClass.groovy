package com.simcta

class StudentClass {

	Student student
	Clas clas

	Float grade
	Integer absence
	String situation

    static constraints = {
		situation nullable: true
		student nullable: false
		clas nullable: false
		grade nullable: true, min: 0.00F, max: 10.00F, scale:2
		absence nullable: true, min:0
    }
}
