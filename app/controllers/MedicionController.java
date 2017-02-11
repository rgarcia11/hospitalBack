package controllers;

import controllers.base.EPController;
import models.HistoriaClinica;
import models.Medicion;
import models.Paciente;
import play.mvc.Result;

/**
 * Created by felipeplazas on 2/11/17.
 */
public class MedicionController extends EPController {


    public Result procesarMedicion(){
        Medicion medicion = bodyAs(Medicion.class);
        Paciente paciente = pacientesCrud.findById(medicion.getIdPaciente());
        paciente.getHistoriaClinica().getMediciones().add(medicion);
        return ok(medicion);
    }
}
