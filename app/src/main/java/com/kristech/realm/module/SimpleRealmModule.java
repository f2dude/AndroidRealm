package com.kristech.realm.module;

import com.kristech.models.Student;
import com.kristech.models.University;

import io.realm.annotations.RealmModule;

/**
 * Created by mobileteam on 23/02/17.
 */

@RealmModule(classes={Student.class, University.class})
public class SimpleRealmModule {
}
