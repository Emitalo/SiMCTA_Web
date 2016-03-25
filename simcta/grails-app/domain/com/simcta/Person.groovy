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
        cpf editable:false, nullable: false, unique: true, validator:{ Person.validCpf(it)}
        rg nullable: false, matches: "[0-9]+"
        email unique: true, email: true
        address nullable: false
        principalPhone nullable: false
        motherName blank: false
        fatherName nullable: true, blank: true
        id display: false
        version display: false
    }

    static mapping = {
        tablePerHierarchy false
    }

    private static def validCpf = {cpf -> 
        try{
            int d1, d2
            int dg1, dg2, remaining
            int dgcpf

            d1 = d2 = 0
            dg1 = dg2 = remaining = 0

            for (int i = 1; i < cpf.length() -1; i++) {
                dgcpf = cpf.substring(i -1, i) as Integer
                d1 = d1 + ( 11 - i ) * dgcpf
                d2 = d2 + ( 12 - i ) * dgcpf
            }

            remaining = (d1 % 11)
            dg1 = (remaining < 2) ? 0 : 11 - remaining

            d2 += 2 * dg1
            remaining = (d2 % 11)
            dg2 = (remaining < 2) ? 0 : 11 - remaining

            def dgverif = cpf.substring(cpf.length()-2)
            def dgresult = (dg1 as String)  + (dg2 as String)

            return dgverif == dgresult

        }catch (Exception e){
            return false
        }
    }
}

class Address {

	String addressInfo
	String number
	String complement
	String cep
	String city

    static constraints = {
        id display: false
        version display: false
        addressInfo blank: false
        number blank: false
        complement blank: true, nullable: true
        cep blank: false, maxSize: 8
        city blank: false, matches: "[a-zA-Z À-ú_]+"
    }

    public String toString(){

        def address = addressInfo + " Nº " + number + " - "

        if(complement){
            address += complement + " - "
        }

        address += city

        return address 
    }
}

class Phone {

	String ddd
	String number

    static constraints = {
        id display: false
        version display: false
        ddd nullable: false, matches: "[0-9]+", validator:{
            return it.size() == 2
        }

        number nullable: false, matches: "[0-9]+", validator:{
            return (it.size() >= 8 && it.size() <= 9)
        }

    }

    public String toString(){
        def phone = "(" + ddd + ") " + number

        return phone; 
    }
}
