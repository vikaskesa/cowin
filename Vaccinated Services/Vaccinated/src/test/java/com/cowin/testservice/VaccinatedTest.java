package com.cowin.testservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cowin.entity.VaccinatedEntity;
import com.cowin.exception.VaccinatedDetailsNotFoundByDoseNumberException;
import com.cowin.exception.VaccinatedDetailsNotFoundException;
import com.cowin.model.Vaccinated;
import com.cowin.repository.VaccinatedRepository;
import com.cowin.service.VaccinatedService;
import com.cowin.service.VaccinatedServiceImpl;
import com.cowin.util.EntityModelUtil;

@SpringBootTest(classes = VaccinatedTest.class)
public class VaccinatedTest {
	@InjectMocks
	private VaccinatedService vaccinatedService = new VaccinatedServiceImpl();

	@Mock
	private VaccinatedRepository vaccinatedRepository;

	private VaccinatedEntity existingVaccinated;

	@Test
	public void testGetVaccinatedById() {

		VaccinatedEntity vaccinated = new VaccinatedEntity();
		vaccinated.setVaccinatedId(6);
		vaccinated.setVaccinatedDate(LocalDate.of(2012,11,27));
		vaccinated.setAppointmentId(15);
		vaccinated.setCenterId(44);
		vaccinated.setDoseNumber(2);
		vaccinated.setVaccineName("Covishield");

		Optional<VaccinatedEntity> optionalVaccinated = Optional.of(vaccinated);
		int vaccinatedId = 2;

		when(vaccinatedRepository.findById(2)).thenReturn(optionalVaccinated);

		Vaccinated existingVaccinated = vaccinatedService.getVaccinatedDetailsById(vaccinatedId);

		assertEquals(vaccinated.getVaccinatedId(), existingVaccinated.getVaccinatedId());

	}
	
	@Test
	public void testGetVaccinatedDetailsByIdNotFound() {

		int vaccinatedId = 500;

		when(vaccinatedRepository.findById(vaccinatedId)).thenThrow(VaccinatedDetailsNotFoundException.class);

		assertThrows(VaccinatedDetailsNotFoundException.class, () -> vaccinatedService.getVaccinatedDetailsById(vaccinatedId));

	}
	
	
	@Test
	public void testGetVaccinatedDetailsNotFoundByDoseNumber() {

		int doseNumber = 5;

		when(vaccinatedRepository.findById(doseNumber)).thenThrow(VaccinatedDetailsNotFoundByDoseNumberException.class);

		assertThrows(VaccinatedDetailsNotFoundByDoseNumberException.class, () -> vaccinatedService.getVaccinatedDetailsById(doseNumber));

	}
	
	

	

	
}
