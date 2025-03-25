package com.cda.PayYouPayMe.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Float amount;
	
	private String messageContent;

	private LocalDate date;

	@ManyToOne
	private Utilisateur sender;

	@ManyToOne
	private Utilisateur receiver;

	private boolean validate;
	
	private boolean reject;

	private boolean report;

	public Transaction(Float amount, String messageContent, LocalDate date) {
		super();
		this.amount = amount;
		this.messageContent = messageContent;
		this.date = date;
	}

	public Transaction() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Utilisateur getSender() {
		return sender;
	}

	public void setSender(Utilisateur sender) {
		this.sender = sender;
	}

	public Utilisateur getReceiver() {
		return receiver;
	}

	public void setReceiver(Utilisateur receiver) {
		this.receiver = receiver;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public boolean isReject() {
		return reject;
	}

	public void setReject(boolean reject) {
		this.reject = reject;
	}

	public boolean isReport() {
		return report;
	}

	public void setReport(boolean report) {
		this.report = report;
	}
}
