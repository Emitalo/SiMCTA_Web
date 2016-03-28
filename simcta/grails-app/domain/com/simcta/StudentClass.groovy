package com.simcta

class StudentClass {

	Student student
	Float grade
	Integer absence
	String situation
	Clas clas

	static hasMany = [student: Student, clas: Clas]

    static constraints = {
		grade nullable:false, min: 0.00F, max: 10.00F, scale:2
		absence nullable:false, min:0
		student nullable:false
		clas nullable:false
    }
}
