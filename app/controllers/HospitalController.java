package controllers;

/**
 * Created by felipeplazas on 2/9/17.
 */

import com.avaje.ebean.Model;
import controllers.base.EPController;
import models.Hospital;
import play.mvc.Result;
import java.util.List;

public class HospitalController extends EPController {

    public Result create() {
        //Turns the request body into the given class file
        Hospital hospital = bodyAs(Hospital.class);
        //Saved the object into the DB
        hospital.save();
        //Returns a 200 response with the object hospital serialized.
        return ok(hospital);
    }

    public Result listAll() {
        //Finds all the hospitals
        List<Hospital> hospitals = findAll(Hospital.class);
        //Return a 200 response with all the hospitals serialized.
        return ok(hospitals);
    }

    public Result findById(Long id) {
        //Finds a hospital with the given id
        Hospital hospital = findById(Hospital.class, id);
        return ok(hospital);
    }


}
