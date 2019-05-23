package com.fdmgroup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="payment")
public class Payment implements IStorable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_paymentid")
	@SequenceGenerator(sequenceName="seq_paymentid", allocationSize=1, name="seq_paymentid")
	@Column(name="payment_id")
	private int paymentId;
	
	@Column(name="amount")
	private double amount;
	
	@ManyToOne
	@JoinColumn(name="appointment_id")
	private Appointment appointment;
	
	public Payment() {
	}

	public Payment(int paymentId, double amount, Appointment appointment) {
		super();
		this.paymentId = paymentId;
		this.amount = amount;
		this.appointment = appointment;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public Payment setAppointment(Appointment appointment) {
		this.appointment = appointment;
		return this;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public Payment setPaymentId(int paymentId) {
		this.paymentId = paymentId;
		return this;
	}

	public double getAmount() {
		return amount;
	}

	public Payment setAmount(double amount) {
		this.amount = amount;
		return this;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", amount=" + amount + ", appointment=" + appointment + "]";
	}
}
