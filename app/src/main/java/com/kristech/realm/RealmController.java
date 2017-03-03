package com.kristech.realm;

import io.realm.Realm;

/**
 * Created by mobileteam on 23/02/17.
 */

public class RealmController {

    private static Realm realm = null;

    private RealmController() {

    }

    public static Realm getInstance() {
        if (realm == null) {

            realm = Realm.getDefaultInstance();

            return realm;
        }

        return realm;
    }
}
