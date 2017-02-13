package controllers;

import controllers.base.EPController;
import models.ConcejoAutomatico;
import models.HistoriaClinica;
import models.Medicion;
import models.Paciente;
import play.mvc.Result;

import java.util.concurrent.CompletableFuture;
import java.util.ArrayList;

/**
 * Created by felipeplazas on 2/11/17.
 */
public class MedicionController extends EPController {

    private final static int BUFFER_SIZE = 1000;
    private static Medicion [] medsBuffer = new Medicion[BUFFER_SIZE];
    private static int bufferIndex = 0;

    public Result procesarMedicion() {
        Medicion medicion = bodyAs(Medicion.class);
        CompletableFuture.runAsync(() -> {
            Paciente paciente = pacientesCrud.findById(medicion.getIdPaciente());
            if (paciente == null) {
                //This shouldn't happen, a prerequisite for a mobile device to send measurements is to be logged in.
                throw new RuntimeException("ERROR, a measurement came with a non-existing patiendId.");
            }
            if (paciente.getHistoriaClinica() == null) {
                paciente.setHistoriaClinica(new HistoriaClinica());
                paciente.getHistoriaClinica().setMediciones(new ArrayList<Medicion>());
                paciente.getHistoriaClinica().setConcejosAutomaticos(new ArrayList<ConcejoAutomatico>());
            }
            paciente.getHistoriaClinica().getMediciones().add(medicion);

            medsBuffer[bufferIndex++] = medicion;
            if ( bufferIndex == BUFFER_SIZE ) {
                medicionesCrud.collection().insert( medsBuffer );
//                medicionesCrud.save(medicion);
//                historiaClinicaCrud.save(paciente.getHistoriaClinica());
//                pacientesCrud.save(paciente);
                bufferIndex = 0;
            }
        });
        return ok();
    }

}
