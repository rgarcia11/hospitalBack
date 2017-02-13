import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.jongo.*;
import play.*;
import play.libs.*;
import com.avaje.ebean.Ebean;
import models.*;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by felipeplazas on 2/9/17.
 */

public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
//        Akka.system().scheduler().scheduleOnce(
//                Duration.create(0, TimeUnit.MILLISECONDS),
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        Logger.info("ON START ---    " + System.currentTimeMillis());
//                    }
//                }
//        );
//
//        FiniteDuration duration = Duration.create((long) 10, TimeUnit.SECONDS);
//
//        Akka.system().scheduler().scheduleOnce(
//                duration,
//                () -> {
//                    System.out.println();;
//                }, Akka.system().dispatcher()
//        );
//
//        Akka.system().scheduler().schedule(
//                Duration.create(nextExecutionInSeconds(8, 0), TimeUnit.SECONDS),
//                Duration.create(24, TimeUnit.HOURS),
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        Logger.info("EVERY DAY AT 8:00 ---    " + System.currentTimeMillis());
//                    }
//                }
//        );
    }

    public static int nextExecutionInSeconds(int hour, int minute){
        return Seconds.secondsBetween(
                new DateTime(),
                nextExecution(hour, minute)
        ).getSeconds();
    }

    public static DateTime nextExecution(int hour, int minute){
        DateTime next = new DateTime()
                .withHourOfDay(hour)
                .withMinuteOfHour(minute)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0);

        return (next.isBeforeNow())
                ? next.plusHours(24)
                : next;
    }

}
