package com.simcta

class Enrollment {

	private static final String PAYMENT_IN_INSTALLMENTS = "Parcelado"
	private static final String PAYMENT_IN_CASH_TYPE = "A vista"

	private static final String PAYMENT_IN_CASH = "Dinheiro"
	private static final String PAYMENT_IN_CARD = "Cartão"
	private static final String PAYMENT_IN_CHECK = "Cheque"

    Date contractsDate
	Integer totalValue

	String paymentType
	String paymentForm
	Integer installments

	Student student

	static hasMany = [serviceItens: ServiceItem]

	static constraints = {
		contractsDate nullable: false, display: false, editable: false
		totalValue nullable: false, display: false, editable: false
		paymentType nullable: false, inList: [PAYMENT_IN_CASH_TYPE, PAYMENT_IN_INSTALLMENTS]
		paymentForm nullable: false, inList: [PAYMENT_IN_CASH, PAYMENT_IN_CARD, PAYMENT_IN_CHECK]
		installments nullable: false, min: 0
		student nullable: true, display: false, editable: false
		id display: false
        version display: false
	}

	def beforeValidate() {
		
		// Calculate the total of the service
		def itens = this.serviceItens

		def total = 0
		for(item in itens) {
			total += item.value
		}

		this.totalValue = total

		// Date of now
		this.contractsDate = new Date()
	}

	def beforeInsert() {
		
		// If have installments
		if(this.installments != 0){
			this.paymentType = PAYMENT_IN_INSTALLMENTS
		}
	}

	public String toString(){

		return student.name
	}
}