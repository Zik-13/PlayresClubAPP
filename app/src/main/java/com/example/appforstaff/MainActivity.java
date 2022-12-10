package com.example.appforstaff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Connection cnt;
    SimpleAdapter adapter;
    String ConnectionResult = "";
    ArrayList<Staff> stafflist = new ArrayList<>();
    Intent add_new_staff;
    Intent item_gridd;
    ListView list;
    int[] imagestaff = {R.drawable.ic_baseline_person_24};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.gridviewlist);
        item_gridd = new Intent(this,item_grid.class);
        add_new_staff = new Intent(this, com.example.appforstaff.add_new_staff.class);
        GetStaffList();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Staff item = (Staff) list.getItemAtPosition(i);

                item_gridd.putExtra("ФИО",item.name);
                item_gridd.putExtra("Телефон",item.phone);
                item_gridd.putExtra("Почта",item.email);
                item_gridd.putExtra("id", item.id);

                startActivity(item_gridd);
            }
        });
    }
    public void onclick_view_add_staff(View view){startActivity(add_new_staff);}
    public void UpdateList(View view){GetStaffList();}

    public void GetStaffList(){
        try{
            stafflist.clear();
            cnt = SQLConnectHelper.connect();
            if(cnt != null){
                String qu = "select * from Staff";
                Statement statement = cnt.createStatement();
                ResultSet resultSet = statement.executeQuery(qu);
                while (resultSet.next()){
                    Log.d(ConnectionResult, resultSet.getString("name"));
                    stafflist.add(new Staff(resultSet.getString("name"),resultSet.getString("phone"),resultSet.getString("email"),resultSet.getString("id")));
                }
                ConnectionResult = "Success";
                AdaptStaff adapter = new AdaptStaff(this,stafflist,imagestaff);
                list.setAdapter(adapter);
            }
            else {
                ConnectionResult = "Failed";
            }
            Log.d(ConnectionResult,"");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Log.d(ConnectionResult, throwables.getMessage());
        }
    }
}