package com.mdw31g2.sharepref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mPreferences ;
    private SharedPreferences.Editor mEditor ;


    private EditText UserName , Password;
    private Button Login;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserName =  findViewById(R.id.username);
        Password =  findViewById(R.id.password);
        Login =  findViewById(R.id.login);
        mCheckBox = findViewById(R.id.checkBox);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit() ;
        checkSharedPreferences();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mCheckBox.isChecked()){
                    mEditor.putString(getString(R.string.checkbox),"True");
                    mEditor.commit();
                    String name = UserName.getText().toString();
                    mEditor.putString(getString(R.string.name),name);
                    mEditor.commit();
                    String password = Password.getText().toString();
                    mEditor.putString(getString(R.string.password),password);
                    mEditor.commit();
                }else {

                    //faux initial
                    mEditor.putString(getString(R.string.checkbox),"False");
                    mEditor.commit();


                    mEditor.putString(getString(R.string.name), "");
                    mEditor.commit();


                    mEditor.putString(getString(R.string.password), "");
                    mEditor.commit();
                }
            }
        });
    }

    private void checkSharedPreferences () {
        String chechbox = mPreferences.getString(getString(R.string.checkbox),"False");
        String name = mPreferences.getString(getString(R.string.name) , "");
        String password = mPreferences.getString(getString(R.string.password),"");

        UserName.setText(name);
        Password.setText(password);

        if (chechbox.equals("True")){
            mCheckBox.setChecked(true);
        }else {
            mCheckBox.setChecked(false);
        }
    }
}