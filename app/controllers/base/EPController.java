package controllers.base;


//import org.jongo.MongoCursor;
import com.avaje.ebean.Model;
import com.avaje.ebean.Query;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import models.Hospital;
import org.jongo.Jongo;
import org.jongo.MongoCursor;
import play.Application;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import util.EPJson;
import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by felipeplazas on 2/9/17.
 */
public class EPController extends Controller {

    /**
     * Creen una linea por cada nueva tabla (y objeto) que se vaya a manejar
     */
    protected static final EPCrudService<Hospital> hospitalCrud = new EPCrudService<>("hospitales", Hospital.class);
    protected static final EPCrudService<Hospital> medicosCrud = new EPCrudService<>("medicos", Hospital.class);

    protected static final String CONTENT_TYPE = "application/json; charset=utf-8";

    @Nonnull
    public static <T> T bodyAs(Class<T> clazz) {
        Http.RequestBody body = request().body();
        if (body == null || body.asJson() == null) {
            System.out.println("ERROR, this should never happen");
        }
        return Json.fromJson(body.asJson(), clazz);
    }

    protected static Result ok(Object object) {
        response().setContentType(CONTENT_TYPE);
        return object == null ? ok() : ok(Json.prettyPrint(Json.toJson(object)));
    }

    protected static Result error(String message) {
        return error(message, null);
    }

    protected static Result error(String message, Integer errorCode) {
        response().setContentType(CONTENT_TYPE);
        String m = message != null ? message : "Request cannot be processed";
        int i = errorCode != null ? errorCode : 400;
        return status(i, EPJson.object("message", m, "code", errorCode));
    }

    protected static Result success(String message) {
        response().setContentType(CONTENT_TYPE);
        return ok(EPJson.object("message", message));
    }

    protected static Result nonNull(Object object) {
        response().setContentType(CONTENT_TYPE);
        return object != null ? ok(Json.prettyPrint(Json.toJson(object))) : notFound();
    }


    protected static Result ok(Iterable<?> list) {
        Iterable<?> toWrite = list == null ? new ArrayList<>() : list;
        response().setContentType(CONTENT_TYPE);
        Status res = ok(Json.prettyPrint(Json.toJson(toWrite)));
        if (toWrite instanceof MongoCursor)
            try {
                ((MongoCursor) toWrite).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return res;
    }
}
