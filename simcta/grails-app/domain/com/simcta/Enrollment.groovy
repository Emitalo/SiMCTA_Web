package com.simcta

class Enrollment {

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
		paymentType nullable: false, inList: ["A vista", "Parcelado"]
		paymentForm nullable: false, inList: ["Dinheiro", "Cartão", "Cheque"]
		installments nullable: false, min: 0
		student nullable: true, display: false, editable: false
		id display: false
        version display: false
	}

	def beforeValidate() {
		
		def itens = this.serviceItens

		def total = 0
		for(item in itens) {
			total += item.value
		}

		this.totalValue = total

		this.contractsDate = new Date()
	}
}