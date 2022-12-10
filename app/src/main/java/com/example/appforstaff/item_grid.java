package com.example.appforstaff;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;

import java.net.CookieHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class item_grid extends AppCompatActivity {
    TextInputLayout name, phone, email;
    Integer id;
    Connection cnt;
    Intent Mainactiv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_grid);

        Mainactiv = new Intent(this,MainActivity.class);

        Bundle arguments = getIntent().getExtras();
        String _name = arguments.get("ФИО").toString();
        String _phone = arguments.get("Телефон").toString();
        String _email = arguments.get("Почта").toString();
        id = Integer.valueOf(arguments.get("id").toString());

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);


        name.getEditText().setText(_name);
        phone.getEditText().setText(_phone);
        email.getEditText().setText(_email);

    }
    public void btn_onclick_back_main(View view){this.finish();}
    public void UpdateItem(View view){
        try{
            SQLConnectHelper cntn = new SQLConnectHelper();
            cnt = cntn.connect();
            String qu = "update Staff set name = '"
                    + Objects.requireNonNull(name.getEditText().getText())
                    + "',phone ='" + Objects.requireNonNull(phone.getEditText()).getText()
                    + "', email ='" + Objects.requireNonNull(email.getEditText()).getText()+
                    "' where id = " + id;
            Statement statement = cnt.createStatement();
            ResultSet resultSet = statement.executeQuery(qu);
            cnt.close();
            ((MainActivity)getBaseContext()).GetStaffList();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            Log.d("Error - ",throwables.getMessage());
        }
    }



    public void DeleteItemDialog(View v){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Вы точно хотите удалить данного сотрудника?")
                .setMessage("Для подпверждения нажмите ОК")
                .setPositiveButton("OK",DeleteItem())
                .setNegativeButton("Отмена",null)
                .setCancelable(true)
                .create()
                .show();
    }
    public DialogInterface.OnClickListener DeleteItem(){
        try{
            SQLConnectHelper connection = new SQLConnectHelper();
            cnt = connection.connect();
            String qu = "delete from Staff where id = " + id;
            Statement statement = cnt.createStatement();
            ResultSet resultSet = statement.executeQuery(qu);
            cnt.close();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            Log.d("Error - ",throwables.getMessage());
        }
        return null;
    }

}