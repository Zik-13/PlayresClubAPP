package com.example.appforstaff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class add_new_staff extends AppCompatActivity {
    Intent Mainactiv;
    Connection cnt;
    TextInputLayout name, phone , email;
    String nulq = "";
    ConstraintLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_staff);
        Mainactiv = new Intent(this,MainActivity.class);
        name = (TextInputLayout) findViewById(R.id.name);
        phone = (TextInputLayout) findViewById(R.id.phone);
        email = (TextInputLayout) findViewById(R.id.email);
        table = (ConstraintLayout) findViewById(R.id.table);




    }
    public void btn_onclick_to_main_act(View view){
        this.finish();
    }
    public void add_staff(View view){
        Log.d("name - ", String.valueOf(Objects.requireNonNull(name.getEditText()).getText()));
        try{
            SQLConnectHelper connection = new SQLConnectHelper();
            cnt = connection.connect();
            String qu = "insert into Staff values('"
                    + Objects.requireNonNull(name.getEditText().getText()) + "', '"
                    + Objects.requireNonNull(phone.getEditText()).getText() + "', '"
                    + Objects.requireNonNull(email.getEditText()).getText()+ "')" ;
            Statement statement = cnt.createStatement();
            ResultSet resultSet = statement.executeQuery(qu);

            cnt.close();

            name.getEditText().setText("");


            Log.d("", String.valueOf((resultSet.last())));
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            Log.d("Error - ",throwables.getMessage());
        }
    }

}