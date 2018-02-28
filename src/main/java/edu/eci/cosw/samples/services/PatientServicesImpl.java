/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.samples.services;

import edu.eci.cosw.samples.PatientsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.eci.cosw.jpa.sample.model.Paciente;
import edu.eci.cosw.jpa.sample.model.PacienteId;

/**
 *
 * @author danie
 */
@Service
public class PatientServicesImpl implements PatientServices{
    @Autowired
    PatientsRepository patiensRepository = null;
    
    
    @Override
    public Paciente getPatient(int id, String tipoid) throws ServicesException {
        return patiensRepository.getOne(new PacienteId(id, tipoid));
    }

    @Override
    public List<Paciente> topPatients(int n) throws ServicesException {
        List<Paciente> tp = patiensRepository.findPatientsTop(n);
        System.out.println("Pacient list: "+tp);
        System.out.println("size: "+tp.size());
        return tp;
    }
    
}
