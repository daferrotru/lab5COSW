package edu.eci.cosw.samples;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import edu.eci.cosw.samples.services.PatientServices;
import edu.eci.cosw.jpa.sample.model.Consulta;
import edu.eci.cosw.jpa.sample.model.Paciente;
import edu.eci.cosw.jpa.sample.model.PacienteId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Assert;
import edu.eci.cosw.samples.services.ServicesException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringDataRestApiApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class SpringDataRestApiApplicationTests {

    @Autowired
    PatientsRepository patiensRepository = null;

    @Autowired
    PatientServices services = null;

    @Test
    public void contextLoads() {
    }

    //The patient must exist
    @Test
    public void patientLoadTest() throws ServicesException{
        PacienteId pId = new PacienteId(2104835, "cc");
        Paciente p = new Paciente(pId, "Daniel F Rodriguez", new Date());

        patiensRepository.save(p);

        Paciente pacienteConsulta = services.getPatient(2104835, "cc");
        Assert.assertEquals(pacienteConsulta.getId().getId() + pacienteConsulta.getId().getTipoId(), p.getId().getId() + p.getId().getTipoId());
    }

    @Test
    //Patient must not exist
    public void patiendNotLoaded()throws ServicesException {
        Paciente pacienteConsulta = services.getPatient(2104835, "cc");
        Assert.assertNull(pacienteConsulta);
    }

    @Test
    //There must not be pacients with N or more queries
    public void patientNQueries() throws ServicesException {
        Consulta c = new Consulta(new Date(), "New Query");
        Set<Consulta> queries = new HashSet<>();
        queries.add(c);

        PacienteId pId = new PacienteId(2104835, "cc");
        Paciente p = new Paciente(pId, "Daniel F Rodriguez", new Date());

        patiensRepository.save(p);

        //Assuming we got N=2
        List<Paciente> pacientes = services.topPatients(2);
        Assert.assertTrue(pacientes.isEmpty());

    }

}
