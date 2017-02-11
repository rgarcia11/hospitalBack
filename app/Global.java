import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.jongo.*;
import play.*;
import play.libs.*;
import com.avaje.ebean.Ebean;
import models.*;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import java.util.*;

/**
 * Created by felipeplazas on 2/9/17.
 */

public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {

    }

}
