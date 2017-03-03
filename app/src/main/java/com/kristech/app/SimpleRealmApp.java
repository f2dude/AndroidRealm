package com.kristech.app;

import android.app.Application;

import com.kristech.realm.module.SimpleRealmModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by mobileteam on 23/02/17.
 */

public class SimpleRealmApp extends Application {

    private static SimpleRealmApp instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        Realm.init(getInstance());
        RealmConfiguration config = new RealmConfiguration.Builder().modules(new SimpleRealmModule()).build();
        Realm.setDefaultConfiguration(config);
    }

    public static SimpleRealmApp getInstance() {
        return instance;
    }
}
