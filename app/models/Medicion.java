package models;

import models.base.IdObject;

import java.util.Date;

/**
 * Created by felipeplazas on 2/11/17.
 */
public class Medicion extends IdObject {

    private int tipoMedicion;
    private long valorMedicion;
    private String idPaciente;
    private long latitude;
    private long longitude;
    private Date fecha;

    public int getTipoMedicion() {
        return tipoMedicion;
    }

    public void setTipoMedicion(int tipoMedicion) {
        this.tipoMedicion = tipoMedicion;
    }

    public long getValorMedicion() {
        return valorMedicion;
    }

    public void setValorMedicion(long valorMedicion) {
        this.valorMedicion = valorMedicion;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public Date getFecha() {return fecha;}

    public void setFecha(Date fecha) {this.fecha = fecha;
    }
}
