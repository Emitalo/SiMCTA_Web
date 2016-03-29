package com.simcta

class Clas {

	String classId
	Date startDate
	Date endDate
	String shift
	boolean active

	Teacher teacher

	static belongsTo = [course: Course]

    static hasMany = [studentClasses: StudentClass]

    static constraints = {
    	classId nullable: false, unique: true, display: false, editable: false
    	shift nullable: false, inList: ["Matutino", "Vespertino", "Noturno"]
    	startDate nullable: false
    	endDate nullable: false, display:false, editable: false
        studentClasses nullable:true, display: false
    }

    // Generates the classId and the endDate
    def beforeValidate(){

        generateEndDate()
		generateClassId()        
    }

    private def generateEndDate(){

    	def startingDate = this.startDate
    	
    	// Generates the end date by adding the duration days of the course
        this.endDate = startingDate.plus(this.course.duration*7)
    }

    private def generateClassId(){

    	def startingDate = this.startDate

    	def clasShift = this.shift
        clasShift = clasShift[0] + clasShift[1]
        clasShift = clasShift.toUpperCase()

        def courseName = this.course.name
        courseName = courseName.split(" ");
        
        def courseFirstName = courseName[0].toUpperCase()

    	// Generates the class ID with the course name, shift and start date
        this.classId = courseFirstName + "-" + clasShift + " " + startingDate.format("dd/MM/YY")
    }
}
