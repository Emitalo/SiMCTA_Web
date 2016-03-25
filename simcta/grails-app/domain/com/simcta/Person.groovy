package com.simcta

abstract class Person {

	String name
	String cpf
	String rg
	Date birthdate
	Address address
	Phone principalPhone
	String motherName
	String fatherName
	String email
	boolean active

	static embedded = ['address', 'principalPhone']

    static constraints = {
    	name blank: false, matches: "[a-zA-Z À-ú_]+"
    	cpf nullable: false, unique: true
    	rg nullable: false, matches: "[0-9]+"
    	email email: true
    	address nullable: false
    	principalPhone nullable: false
    	motherName blank: false
    	fatherName nullable: true, blank: true
    }
}

class Address {

	String addressInfo
	String number
	String complement
	String cep
	String city

    static constraints = {
    	addressInfo blank: false
  		number blank: false
  		complement blank: true, nullable: true
  		cep blank: false, maxSize: 8
  		city blank: false, matches: "[a-zA-Z À-ú_]+"
        id display: false
        version display: false
    }

    public String toString(){

        def address = addressInfo + " Nº " + number + " - " + city

        return address 
    }
}

class Phone {

	String ddd
	String number

    static constraints = {
    	ddd nullable: false, matches: "[0-9]+", validator:{
            return it.size() == 2
        }

        number nullable: false, matches: "[0-9]+", validator:{
            return (it.size() >= 8 && it.size() <= 9)
        }

        id display: false
        version display: false
    }

    public String toString(){
        def phone = "(" + ddd + ") " + number
        return phone; 
    }
}
