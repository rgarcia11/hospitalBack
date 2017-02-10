package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.*;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

/**
 * Created by felipeplazas on 2/9/17.
 */
public class Application extends Controller {

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

}
