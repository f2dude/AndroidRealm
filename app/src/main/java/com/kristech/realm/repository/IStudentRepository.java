package com.kristech.realm.repository;

import com.kristech.models.Student;

import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by mobileteam on 24/02/17.
 */

public interface IStudentRepository extends IBaseRepository {

    interface OnSaveStudentCallback {
        void onSuccess();
        void onError(String message);
    }

    interface OnDeleteStudentCallback {
        void onSuccess();
        void onError(String message);
    }

    interface OnGetStudentByIdCallback {
        void onSuccess(Student student);
        void onError(String message);
    }

    interface OnGetAllStudentsCallback {
        void onSuccess(RealmResults<Student> students);
        void onError(String message);
    }

    interface OnGetStudentsCallback{
        void onSuccess(RealmList<Student> students);
        void onError(String message);
    }

    void addStudent(Student student, OnSaveStudentCallback callback);

    void addStudentByUniversityId(Student student, String universityId, OnSaveStudentCallback callback);

    void deleteStudentById(String id, OnDeleteStudentCallback callback);

    void deleteStudentByPosition(int position, OnDeleteStudentCallback callback);

    void getAllStudents(OnGetAllStudentsCallback callback);

    void getAllStudentsByUniversityId(String id, OnGetStudentsCallback callback);

    void getStudentById(String id, OnGetStudentByIdCallback callback);
}
