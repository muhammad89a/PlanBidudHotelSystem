package planBidudHotel.database;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.observable.ObservableJust;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import planBidudHotel.entities.Country;
import planBidudHotel.entities.Request;
import planBidudHotel.entities.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class DataBaseAPI {

    private static DataBaseAPI dataBaseAPI = null;


    public static DataBaseAPI getInstance() {
        if (dataBaseAPI == null) {
            dataBaseAPI = new DataBaseAPI();
        }
        return dataBaseAPI;
    }

    private DataBaseAPI() {

    }

    //APIs
    public Observable<ArrayList<Country>> getCountries() {

        return DatabaseConn.getInstance().execQuery(Consts.SQL_SEL_COUNTRY)
                .observeOn(Schedulers.io())
                .map(rs -> {
                    ArrayList<Country> results = new ArrayList<Country>();
                    while (rs.next()) {
                        int i = 1;
                        Country r = new Country(rs.getInt(i++), rs.getString(i++), rs.getBoolean(i++));
                        results.add(r);
                        System.out.println("---"+r);
                    }
                    return results;
                })
                .subscribeOn(JavaFxScheduler.platform())
                .doOnError(Throwable::printStackTrace);


    }

    public Observable<ArrayList<Request>> getRequests() {

        return DatabaseConn.getInstance().execQuery(Consts.SQL_SEL_REQUEST)
                .observeOn(Schedulers.io())
                .map(rs -> {
                    ArrayList<Request> results = new ArrayList<Request>();
                    while (rs.next()) {
                        int i = 1;
                        Request r = new Request(rs.getInt(i++), rs.getString(i++), rs.getString(i++),
                                rs.getString(i++), rs.getDate(i++), rs.getDate(i++), rs.getDate(i++),
                                Status.valueOf(rs.getString(i++)!=null?rs.getString(i++):"Pending"), rs.getInt(i++)
                        );
                        results.add(r);
                        System.out.println(r);
                    }
                    return results;
                })
                .subscribeOn(JavaFxScheduler.platform())
                .doOnError(Throwable::printStackTrace);


    }

    public Single<Boolean> insertRequest(Request request) {
        String QueryInsert = Consts.SQL_INSERT_REQUEST;
        System.out.println("request-> " + request);

        QueryInsert = QueryInsert.replaceFirst("[?]", '\"' + request.getEmail() + '\"');
        QueryInsert = QueryInsert.replaceFirst("[?]", '\"' + request.getPhoneNo() + '\"');
        QueryInsert = QueryInsert.replaceFirst("[?]", '\"' + "5" + '\"');
        QueryInsert = QueryInsert.replaceFirst("[?]", '\"' + request.getExpectedLandDate().toString() + '\"');
        QueryInsert = QueryInsert.replaceFirst("[?]", '\"' + new Date().toString() + '\"');
        QueryInsert = QueryInsert.replaceFirst("[?]", '\"' + Status.Pending.name() + '\"');
        QueryInsert = QueryInsert.replaceFirst("[?]", '\"' + "1" + '\"');
        QueryInsert = QueryInsert.replaceFirst("[?]", '\"' + "2" + '\"');
        QueryInsert = QueryInsert.replaceFirst("[?]", '\"' + "6" + '\"');
        QueryInsert = QueryInsert.replaceFirst("[?]", '\"' + "1" + '\"');
        System.out.println("QueryInsert-> " + QueryInsert);

        System.out.println("request-> finished");
        return Observable.just(DatabaseConn.getInstance().executeUpdate(QueryInsert))
                .observeOn(Schedulers.io())
                .subscribeOn(JavaFxScheduler.platform())
                .doOnError(Throwable::printStackTrace)
                .singleOrError();
    }

    public Observable insertCountry(Country country) {
        String QueryInsert = Consts.SQL_INSERT_COUNTRY;
        System.out.println("country-> " + country);

        QueryInsert = QueryInsert.replaceFirst("[?]", '\"' + country.getName() + '\"');
        QueryInsert = QueryInsert.replaceFirst("[?]", Boolean.toString(country.isNeedIsolation()));
        System.out.println("insertCountry-> " + QueryInsert);
        System.out.println("country-> finished");
        return Observable.just(DatabaseConn.getInstance().executeUpdate(QueryInsert))
                .observeOn(Schedulers.io())
                .subscribeOn(JavaFxScheduler.platform())
                .doOnError(Throwable::printStackTrace);
    }

}
