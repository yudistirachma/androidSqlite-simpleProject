package com.example.sqlliterahmayudistira;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editNama, editAlamat, editJenisKelamin, editId, editAgama;
    Button btnAddData;
    Button btnViewAll;
    Button btnUpdate;
    Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editNama = (EditText)findViewById(R.id.inputNama);
        editAlamat = (EditText)findViewById(R.id.inputAlamat);
        editJenisKelamin = (EditText)findViewById(R.id.inputJenisKelamin);
        editAgama = (EditText)findViewById(R.id.inputAgama);
        editId = (EditText)findViewById(R.id.editTextId);
        btnAddData = (Button)findViewById(R.id.saveData);
        btnViewAll = (Button)findViewById(R.id.viewAll);
        btnUpdate = (Button)findViewById(R.id.update);
        btnDelete = (Button)findViewById(R.id.delete);

        AddData();
        viewAll();
        update();
        delete();
    }

    public  void  AddData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(
                                editNama.getText().toString(),
                                editAlamat.getText().toString(),
                                editJenisKelamin.getText().toString(),
                                editAgama.getText().toString()
                        );
                        if (isInserted == true)
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public  void  viewAll(){
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0){
//                            show message
                            showMessage("Error", "Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("id : "+ res.getString(0)+"\n");
                            buffer.append("nama : "+ res.getString(1)+"\n");
                            buffer.append("alamat : "+ res.getString(2)+"\n");
                            buffer.append("jenis kelamin : "+ res.getString(3)+"\n");
                            buffer.append("agama : "+ res.getString(4)+"\n\n");
                        }

//                        show all Data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }
    public  void  update (){
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Boolean isUpdate = myDb.updateData(
                                editId.getText().toString(),
                                editNama.getText().toString(),
                                editAlamat.getText().toString(),
                                editJenisKelamin.getText().toString(),
                                editAgama.getText().toString()
                        );
                        if (isUpdate == true)
                            Toast.makeText(MainActivity.this,"Data updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data Not updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public  void  delete (){
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer isDelete = myDb.deleteData(editId.getText().toString());
                        if (isDelete > 0)
                            Toast.makeText(MainActivity.this,"Data is deleted ",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data Not deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public  void  showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
