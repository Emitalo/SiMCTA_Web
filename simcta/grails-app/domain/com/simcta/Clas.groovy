package com.simcta

class Clas {

	static belongsTo = [course: Course]

	String classId
	Date startDate
	Date endDate
	String shift
	boolean active

    static constraints = {
    	classId nullable: true, unique: true, display: false, editable: false
    	startDate nullable: false
    	shift nullable: false, inList: ["Matutino", "Vespertino", "Noturno"]
    	endDate nullable: true, display:false, editable: false
    }
}
