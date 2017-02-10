package controllers.base;


//import org.jongo.MongoCursor;
import com.avaje.ebean.Model;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import util.EPJson;
import javax.annotation.Nonnull;
import java.util.List;

/**
 * Created by felipeplazas on 2/9/17.
 */
public class EPController extends Controller {

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
        return status(i, EPJson.object("message", m, "code", errorCode, "error", m));
    }

//    protected static Result ok(Iterable<?> list) {
//        Iterable<?> toWrite = list == null ? new ArrayList() : list;
//        response().setContentType(CONTENT_TYPE);
//        Status res = ok(Json.prettyPrint(Json.toJson(toWrite)));
//        if (toWrite instanceof MongoCursor)
//            try {
//                ((MongoCursor) toWrite).close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        return res;
//    }

    protected static Result success(String message) {
        response().setContentType(CONTENT_TYPE);
        return ok(EPJson.object("message", message));
    }

    protected static Result nonNull(Object object) {
        response().setContentType(CONTENT_TYPE);
        return object != null ? ok(Json.prettyPrint(Json.toJson(object))) : notFound();
    }

    protected static <T> List<T> findAll(Class<T> clazz){
        return new Model.Finder<Long, T>(clazz).all();
    }

    /**
     * Finds an Object of the given class and with the given id.
     * @param clazz
     * @param id
     * @param <T>
     * @return
     */
    protected static <T> T findById(Class<T> clazz, Long id){
        return new Model.Finder<Long, T>(clazz).byId(id);
    }
}
