package com.simcta

class StudentClass {

	Student student
	Clas clas

	Float grade
	Integer absence
	String situation

    static constraints = {
		situation nullable: true
		clas nullable: false
		student nullable: false, unique: ['clas']
		grade nullable: true, min: 0.00F, max: 10.00F, scale:2
		absence nullable: true, min: 0
    }

    public String toString(){

    	return clas.classId + " - " + student.name 
    }
}
