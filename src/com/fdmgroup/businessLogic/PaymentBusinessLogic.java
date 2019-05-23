package com.fdmgroup.businessLogic;

import com.fdmgroup.dao.jpa.AppointmentJpaDao;
import com.fdmgroup.dao.jpa.PaymentJpaDao;
import com.fdmgroup.model.Appointment;
import com.fdmgroup.model.Doctor;
import com.fdmgroup.model.Payment;

public class PaymentBusinessLogic {
	private PaymentJpaDao paymentDao;
	private AppointmentJpaDao appointmentDao;
	
	public PaymentBusinessLogic() {
		paymentDao = new PaymentJpaDao();
		appointmentDao = new AppointmentJpaDao();
	}

	public Payment makePayment(Appointment appointment, double amount) {
		Payment payment = new Payment();
		payment.setAmount(amount).setAppointment(appointment);
		paymentDao.create(payment);
		return payment;
	}

	public void updatedAppointmentPayment(Appointment appointmentOld, Doctor doc, Doctor doctor) {
		boolean result = false;
		double amount = 0;
		
		int doctor_id = doctor.getDoctor_id();
		int doctor_id_old = doc.getDoctor_id();
		
		if(doctor_id == doctor_id_old)
			result=true;
		
		if(result==false) {
			double amount_old = doc.getConsultationFee();

			double amountNew = doctor.getConsultationFee();
			
			amount = amountNew-amount_old;
			
			Appointment appointment = appointmentDao.readById(appointmentOld.getAppointmentId());
			appointment.setDoctor(doctor);
			
			Payment payment = makePayment(appointment, amount);
			
			if(amount<0) 
				System.out.println("You will get a refund of $" + Math.abs(amount));
			else 
				System.out.println("You will pay an extra $" + amount);
			
			paymentDao.update(payment);
		}
	}

	public Payment getPaymentmentByAppointmentId(int appointment_id) {
		return paymentDao.getPaymentByAppointmentId(appointment_id);
	}

}
