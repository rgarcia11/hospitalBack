package models;

import models.base.IdObject;

import java.util.List;

/**
 * Created by felipeplazas on 2/11/17.
 */
public class HistoriaClinica extends IdObject{

    private List<Medicion> mediciones;
    private List<ConcejoAutomatico> concejosAutomaticos;

    public List<Medicion> getMediciones() {
        return mediciones;
    }

    public void setMediciones(List<Medicion> mediciones) {
        this.mediciones = mediciones;
    }

    public List<ConcejoAutomatico> getConcejosAutomaticos() {
        return concejosAutomaticos;
    }

    public void setConcejosAutomaticos(List<ConcejoAutomatico> concejosAutomaticos) {
        this.concejosAutomaticos = concejosAutomaticos;
    }
}
