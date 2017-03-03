package com.kristech.realmexample;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by mobileteam on 24/02/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    abstract protected void initComponents();

    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();
    }
}
