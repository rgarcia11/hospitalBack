package models;

import models.base.IdObject;

/**
 * Created by felipeplazas on 2/10/17.
 */
public class Medico extends IdObject {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
