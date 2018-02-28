/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.samples;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.eci.cosw.jpa.sample.model.Paciente;
import edu.eci.cosw.jpa.sample.model.PacienteId;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author danie
 */
public interface PatientsRepository extends JpaRepository<Paciente, PacienteId>{
    
    @Override
    @Query("select p from Paciente p where p.id = ?1")
    public Paciente getOne(PacienteId id);
    
    @Query("select p from Paciente p where size(p.consultas) >= ?1")
    List<Paciente> findPatientsTop(int top);
}
