package controllers;

import controllers.base.EPController;
import models.ConcejoAutomatico;
import models.HistoriaClinica;
import models.Medicion;
import models.Paciente;
import play.mvc.Result;

import java.util.ArrayList;

/**
 * Created by felipeplazas on 2/11/17.
 */
public class MedicionController extends EPController {


    public Result procesarMedicion(){
        Medicion medicion = bodyAs(Medicion.class);
        Paciente paciente = pacientesCrud.findById( medicion.getIdPaciente() );

        if (paciente.getHistoriaClinica() == null) {
            paciente.setHistoriaClinica( new HistoriaClinica() );
            paciente.getHistoriaClinica().setMediciones( new ArrayList<Medicion>() );
            paciente.getHistoriaClinica().setConcejosAutomaticos( new ArrayList<ConcejoAutomatico>() );
        }

        paciente.getHistoriaClinica().getMediciones().add( medicion );
        pacientesCrud.save( paciente );
        medicionCrud.save( medicion );
        return ok( medicion );
    }
}
