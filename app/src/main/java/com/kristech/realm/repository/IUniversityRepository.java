package com.kristech.realm.repository;

import com.kristech.models.University;

import io.realm.RealmResults;

/**
 * Created by mobileteam on 24/02/17.
 */

public interface IUniversityRepository extends IBaseRepository {

    interface OnAddUniversityCallback {
        void onSuccess();
        void onError(String message);
    }

    interface OnGetAllUniversityCallback {
        void onSuccess(RealmResults<University> universities);
        void onError(String message);
    }

    interface OnGetUniversityByIdCallback {
        void onSuccess(University university);
        void onError(String message);
    }

    interface OnDeleteUniversityCallback {
        void onSuccess();
        void onError(String message);
    }

    void addUniversity(University university, OnAddUniversityCallback callback);

    void deleteUniversityById(String Id, OnDeleteUniversityCallback callback);

    void deleteUniversityByPosition(int position, OnDeleteUniversityCallback callback);

    void getAllUniversities(OnGetAllUniversityCallback callback);

    void getUniversityById(String id, OnGetUniversityByIdCallback callback);
}
