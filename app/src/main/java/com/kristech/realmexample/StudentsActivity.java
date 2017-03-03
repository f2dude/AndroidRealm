package com.kristech.realmexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.kristech.adapters.StudentsAdapter;
import com.kristech.dialogs.AddStudentDialog;
import com.kristech.models.Student;
import com.kristech.presenters.IStudentPresenter;
import com.kristech.presenters.impl.StudentPresenter;
import com.kristech.realm.table.RealmTable;

import io.realm.RealmList;

/**
 * Created by mobileteam on 24/02/17.
 */

public class StudentsActivity extends BaseActivity implements View.OnClickListener {

    private IStudentPresenter presenter;

    private FloatingActionButton fbAdd;
    private RecyclerView rvStudents;
    private StudentsAdapter adapter;

    private RealmList<Student> students;
    private String universityId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        presenter = new StudentPresenter(this);
        universityId = getIntent().getStringExtra(RealmTable.ID);

        initComponents();
    }

    @Override
    protected void initComponents() {

        fbAdd = (FloatingActionButton) findViewById(R.id.fab_add_student);
        fbAdd.setOnClickListener(this);
        initRecyclerListener();

    }

    public void updateToolbarTittle(String tittle) {
        getSupportActionBar().setTitle(getString(R.string.students) + " - " + tittle);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.subscribeCallBacks();
        presenter.getUniversityById(universityId);
        presenter.getAllStudentsByUniversityId(universityId);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unsubscribeCallbacks();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_add_student: {
                showAddStudentDialog();
                break;
            }
        }
    }

    private void initRecyclerListener() {
        rvStudents = (RecyclerView) findViewById(R.id.rv_students);
        rvStudents.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvStudents.setItemAnimator(new DefaultItemAnimator());

        ItemTouchHelper swipeToDismissTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override

            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                presenter.deleteStudentById(students.get(viewHolder.getAdapterPosition()).getId());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        swipeToDismissTouchHelper.attachToRecyclerView(rvStudents);
    }

    private void showAddStudentDialog() {
        final AddStudentDialog dialog = new AddStudentDialog();
        dialog.show(getSupportFragmentManager(), dialog.getClass().getName());
        dialog.setListener(new AddStudentDialog.OnAddStudentClickListener() {
            @Override
            public void onAddStudentClickListener(Student student) {
                dialog.dismiss();
                presenter.addStudentByUniversityId(student, universityId);
                presenter.getAllStudentsByUniversityId(universityId);
            }
        });
    }

    public void showStudents(RealmList<Student> students) {
        this.students = students;
        adapter = new StudentsAdapter(students);
        rvStudents.setAdapter(adapter);
    }
}
