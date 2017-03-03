package com.kristech.presenters;

import com.kristech.models.Student;

/**
 * Created by mobileteam on 23/02/17.
 */

public interface IStudentPresenter extends IBasePresenter {

    void addStudent(Student student);

    void addStudentByUniversityId(Student student, String universityId);

    void deleteStudentByPosition(int position);

    void deleteStudentById(String studentId);

    void getAllStudents();

    void getAllStudentsByUniversityId(String id);

    void getStudentById(String id);

    void getUniversityById(String id);
}
