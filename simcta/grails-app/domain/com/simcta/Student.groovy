package com.simcta

import  com.simcta.Person
import  com.simcta.Enrollment

class Student extends Person{

	static belongsTo = [studentClass: StudentClass]
	
	static constraints = {
		studentClass nullable:true, display:false
	}

	public String toString(){
		return name
	}
}