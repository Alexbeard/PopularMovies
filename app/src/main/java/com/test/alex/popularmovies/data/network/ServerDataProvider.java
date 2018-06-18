package com.test.alex.popularmovies.data.network;


import com.test.alex.popularmovies.data.api.MoviesApi;

public class ServerDataProvider {

    private static ServerData sServerData;

    public static ServerData getServerData(MoviesApi api) {
        if (sServerData == null) {
            sServerData = new ServerDataImpl(api);
        }
        return sServerData;
    }

}
