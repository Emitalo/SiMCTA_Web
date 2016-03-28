package com.simcta

import  com.simcta.Person
import  com.simcta.Enrollment
import  java.util.Set

class Student extends Person{

	static hasMany = [studentClasses: StudentClass]
	
	static constraints = {
		studentClasses nullable:true, display: false
	}

	public String toString(){
		return name
	}
}