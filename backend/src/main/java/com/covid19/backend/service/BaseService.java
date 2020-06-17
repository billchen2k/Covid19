package com.covid19.backend.service;


import com.covid19.backend.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseService {

	@Autowired
	public UserMapper userMapper;

	@Autowired
	public PatientMapper patientMapper;

	@Autowired
	public DoctorMapper doctorMapper;

	@Autowired
	public MedicineMapper medicineMapper;

	@Autowired
	public PrescriptionMapper prescriptionMapper;

	@Autowired
	public HospitalMapper hospitalMapper;

	@Autowired
	public ImageMapper imageMapper;

	@Autowired
	public DiagnosisMapper diagnosisMapper;
}
