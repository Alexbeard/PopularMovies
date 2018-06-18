package com.test.alex.popularmovies;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.test.alex.popularmovies.di.AppComponent;
import com.test.alex.popularmovies.di.DaggerAppComponent;
import com.test.alex.popularmovies.di.DataModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class App extends Application {

    private static Context context;
    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

        sAppComponent = DaggerAppComponent.builder()
                .dataModule(new DataModule(context))
                .build();

    }

    public static Context getContext() {
        return context;
    }

    @NonNull
    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
}
