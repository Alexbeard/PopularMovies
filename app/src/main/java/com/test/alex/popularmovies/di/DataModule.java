package com.test.alex.popularmovies.di;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.readystatesoftware.chuck.ChuckInterceptor;
import com.test.alex.popularmovies.BuildConfig;
import com.test.alex.popularmovies.data.api.ApiKeyInterceptor;
import com.test.alex.popularmovies.data.api.MoviesApi;
import com.test.alex.popularmovies.data.db.StorageData;
import com.test.alex.popularmovies.data.db.StorageDataProvider;
import com.test.alex.popularmovies.data.network.ServerData;
import com.test.alex.popularmovies.data.network.ServerDataProvider;
import com.test.alex.popularmovies.data.repository.MoviesRepository;
import com.test.alex.popularmovies.data.repository.RepositoryProvider;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class DataModule {

    Context context;

    public DataModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

    @Provides
    @Singleton
    MoviesRepository provideMovieRepository(ServerData server, StorageData storage) {
        return RepositoryProvider.getMoviesRepository(server, storage);
    }

    @Provides
    @Singleton
    ServerData provideServerData(MoviesApi api) {
        return ServerDataProvider.getServerData(api);
    }

    @Provides
    @Singleton
    StorageData provideStorageData(Realm realm) {
        return StorageDataProvider.getStorageData(realm);
    }


    @Provides
    @Singleton
    MoviesApi createService(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(MoviesApi.class);
    }

    @Provides
    @Singleton
    OkHttpClient provideClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new ApiKeyInterceptor())
                .addInterceptor(new ChuckInterceptor(context))
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }

}
