package com.yavuzoktay.sqlliteexample;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper peopleDB;

    Button btnAddData, btnViewData,btnUpdateData,btnDelete;
    EditText edtName,edtEmail,edtTvShow,edtID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peopleDB = new DatabaseHelper(this);


        edtName=findViewById(R.id.edtName);
        edtEmail=findViewById(R.id.edtEmail);
        edtTvShow=findViewById(R.id.edtTVShows);
        btnAddData=findViewById(R.id.btnAddData);
        btnViewData=findViewById(R.id.btnViewData);
        btnUpdateData=findViewById(R.id.btnUpdateData);
        edtID=findViewById(R.id.edtID);
        btnDelete=findViewById(R.id.btnDelete);

        addData();
        viewData();
        updateData();
        deleteData();
    }

    public void addData(){
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =edtName.getText().toString();
                String email=edtEmail.getText().toString();
                String tvShow=edtTvShow.getText().toString();

                boolean insertData=peopleDB.addData(name,email,tvShow);

                if (insertData==true){
                    Toast.makeText(MainActivity.this, "Data Succesfully Inserted!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void viewData(){
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data=peopleDB.showData();

                if (data.getCount()==0){
                    //message
                    display("Error","No Data Found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (data.moveToNext()){
                    buffer.append("ID: "+ data.getString(0)+"\n");
                    buffer.append("Name: "+ data.getString(1)+"\n");
                    buffer.append("Email" + data.getString(2)+"\n");
                    buffer.append("Favorite TV Show" + data.getString(3)+"\n");

                    //display message
                    display("All Stored Data ",buffer.toString());
                }
            }
        });
    }

    public void display(String title, String message){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void updateData(){
        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp=edtID.getText().toString().length();
                if (temp>0){
                    boolean update=peopleDB.updateData(edtID.getText().toString(),edtName.getText().toString(),
                            edtEmail.getText().toString(),edtTvShow.getText().toString());
                    if (update==true){
                        Toast.makeText(MainActivity.this, "Succesfully Updated Data!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "You must enter an ID to update", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void deleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp=edtID.getText().toString().length();
                if (temp>0){
                    Integer deleteRow=peopleDB.deleteData(edtID.getText().toString());
                    if (deleteRow>0){
                        Toast.makeText(MainActivity.this, "Succesfully Deleted The Data", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "You must enter an ID to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}
