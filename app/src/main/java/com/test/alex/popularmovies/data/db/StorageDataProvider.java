package com.test.alex.popularmovies.data.db;

import io.realm.Realm;


public class StorageDataProvider {

    private static StorageData sStorageData;

    public static StorageData getStorageData(Realm realm) {
        if (sStorageData == null) {
            sStorageData = new StorageDataImpl(realm);
        }
        return sStorageData;
    }
}
