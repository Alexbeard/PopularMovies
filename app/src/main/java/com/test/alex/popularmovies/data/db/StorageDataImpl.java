package com.test.alex.popularmovies.data.db;

import com.test.alex.popularmovies.model.DBcontent.MovieRealm;
import com.test.alex.popularmovies.model.MovieToRealmMapper;
import com.test.alex.popularmovies.model.RealmToMovieMapper;
import com.test.alex.popularmovies.model.content.Genre;
import com.test.alex.popularmovies.model.content.Movie;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;


public class StorageDataImpl implements StorageData {

    private Realm realm;

    public StorageDataImpl(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void saveMovie(Movie movie) {
        Realm.getDefaultInstance().executeTransaction(realm -> realm.insertOrUpdate(new MovieToRealmMapper().map(movie)));
    }

    @Override
    public void deleteMovie(Movie movie) {
        realm = Realm.getDefaultInstance();
        RealmResults<MovieRealm> movies = realm.where(MovieRealm.class)
                .equalTo("mId", movie.getId())
                .findAllSorted("mTitle", Sort.ASCENDING);

        realm.executeTransaction(r -> movies.deleteAllFromRealm());
    }

    @Override
    public boolean searchMovieInDb(int movieId) {
        realm = Realm.getDefaultInstance();
        return realm.where(MovieRealm.class).equalTo("mId", movieId).findAll().size() > 0;
    }

    @Override
    public Flowable<List<Movie>> getMoviesFromDB() {
        realm = Realm.getDefaultInstance();
        return realm.where(MovieRealm.class)
                .findAllSorted("mTitle", Sort.ASCENDING)
                .asFlowable()
                .flatMap(movieRealms -> Flowable.just(realm.copyFromRealm(movieRealms))
                        .map(new RealmToMovieMapper())
                        .subscribeOn(Schedulers.computation()))
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Genre>> getGenresByIndexes(Integer[] genresIds) {
        realm = Realm.getDefaultInstance();
        return Observable.just(realm.copyFromRealm(realm.where(Genre.class).in("id", genresIds).findAll()))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Genre>> getGenres() {
        realm = Realm.getDefaultInstance();
        return Observable.just(realm.copyFromRealm(realm.where(Genre.class).findAll()))
                .subscribeOn(Schedulers.computation());

    }

    @Override
    public Function<List<Genre>, Observable<List<Genre>>> getSaveFunc() {
        return mSaveFuncGenre;
    }

    private final Function<List<Genre>, Observable<List<Genre>>> mSaveFuncGenre = genres -> {
        Realm.getDefaultInstance().executeTransaction(realm -> {
            realm.delete(Genre.class);
            realm.insert(genres);
        });
        return Observable.just(genres)
                .subscribeOn(Schedulers.computation());
    };


}
