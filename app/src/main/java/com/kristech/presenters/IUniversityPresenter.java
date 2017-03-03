package com.kristech.presenters;

/**
 * Created by mobileteam on 23/02/17.
 */

public interface IUniversityPresenter extends IBasePresenter {

    void addUniversity(String universityName);

    void deleteUniversity(int position);

    void deleteUniversityById(String Id);

    void getUniversityById(String id);

    void getAllUniversities();
}
