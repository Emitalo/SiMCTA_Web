package com.simcta

import  com.simcta.Person

class Teacher extends Person{

	String qualification

    static constraints = {
    	qualification nullable: false
    }

    public String toString(){

    	return name;
    }
}
