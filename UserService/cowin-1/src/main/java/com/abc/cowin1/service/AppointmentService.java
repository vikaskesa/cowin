package com.abc.cowin1.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.abc.cowin1.model.Appointment;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class AppointmentService {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "AppointmentServiceFallBack")
	public Appointment getAppointmentDetails(int appointmentId) {

		String resourceUrl = "http://appointmentservice/appointmentservice/appointment/get" + appointmentId;

		Appointment Appointment = restTemplate.getForObject(resourceUrl, Appointment.class);

		return Appointment;

	}

	@SuppressWarnings("unused")
	private Appointment AppointmentServiceFallBack(int appointmentId) {

		Appointment Appointment = new Appointment();
		Appointment.setAppointmentId(appointmentId);
		Appointment.setCenterId(0);
		Appointment.setDate_of_appointment(LocalDate.now());
		Appointment.setDose_number(0);
		Appointment.setType_of_vaccine(" ");
		Appointment.setUserId(0);

		System.out.println("Appointment Service is down!!! fallback route enabled...");
		return Appointment;

	}

}
