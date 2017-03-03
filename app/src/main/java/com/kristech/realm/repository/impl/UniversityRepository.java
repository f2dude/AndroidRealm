package com.kristech.realm.repository.impl;

import android.util.Log;

import com.kristech.models.University;
import com.kristech.realm.RealmController;
import com.kristech.realm.repository.IUniversityRepository;
import com.kristech.realm.table.RealmTable;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by mobileteam on 24/02/17.
 */

public class UniversityRepository implements IUniversityRepository {

    @Override
    public void addUniversity(final University university, final OnAddUniversityCallback callback) {
        Realm realm = RealmController.getInstance();

        /**
         * Normal insertion is carried out
         */

        realm.beginTransaction();
        University u = realm.createObject(University.class, UUID.randomUUID().toString());
        u.setName(university.getName());
        realm.commitTransaction();

        if (callback != null)
            callback.onSuccess();


        /**
         * Direct transaction insertion os carried out
         */

//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                University u = realm.createObject(University.class, UUID.randomUUID().toString());
//                u.setName(university.getName());
//
//                if (callback != null)
//                    callback.onSuccess();
//            }
//        });

        /**
         * Insertion is carried out using background thread
         */

//        realm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                University u = realm.createObject(University.class, UUID.randomUUID().toString());
//                u.setName(university.getName());
//            }
//        }, new Realm.Transaction.OnSuccess() {
//            @Override
//            public void onSuccess() {
//                Log.i("Realm Database:::::", "=====Record inserted successfully=====");
//            }
//        }, new Realm.Transaction.OnError() {
//            @Override
//            public void onError(Throwable error) {
//                Log.i("Realm Database:::::", "=====Insersion Error has Occured=====");
//            }
//        });
    }

    @Override
    public void deleteUniversityById(String Id, OnDeleteUniversityCallback callback) {
        Realm realm = RealmController.getInstance();
        realm.beginTransaction();
        University university = realm.where(University.class).equalTo(RealmTable.ID, Id).findFirst();
//        university.removeFromRealm();
        university.deleteFromRealm();
        realm.commitTransaction();

        if (callback != null)
            callback.onSuccess();
    }

    @Override
    public void deleteUniversityByPosition(int position, OnDeleteUniversityCallback callback) {
        Realm realm = RealmController.getInstance();
        realm.beginTransaction();
        RealmQuery<University> query = realm.where(University.class);
        RealmResults<University> results = query.findAll();
//        results.remove(position);
        results.deleteFromRealm(position);
        realm.commitTransaction();

        if (callback != null)
            callback.onSuccess();
    }

    @Override
    public void getAllUniversities(OnGetAllUniversityCallback callback) {
        Realm realm = RealmController.getInstance();
        RealmQuery<University> query = realm.where(University.class);
        RealmResults<University> results = query.findAll();

        if (callback != null)
            callback.onSuccess(results);
    }

    @Override
    public void getUniversityById(String id, OnGetUniversityByIdCallback callback) {
        Realm realm = RealmController.getInstance();
        University result = realm.where(University.class).equalTo(RealmTable.ID, id).findFirst();

        if (callback != null)
            callback.onSuccess(result);
    }
}
