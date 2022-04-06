package com.example.basic_sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

        ListView lvName = findViewById(R.id.lvName);
        EditText edtName = findViewById(R.id.edtName);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnCancel = findViewById(R.id.btnCancel);
        //db.deleteAll();
        List<String> listName = db.getAllName();
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listName);

        lvName.setAdapter(adapter);

        //db.addName("Nguyen Minh Vuong");
        //String name = edtName.getText().toString();

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                if(name.equals(""))
                    thongBao();
                else{
                    listName.add(name);
                    db.addName(name);
                    adapter.notifyDataSetChanged();
                }

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Xác nhận");
                dialog.setMessage("Bạn có muốn thoát ứng dụng?");
                dialog.setCancelable(true);
                dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);
                    }
                });
                dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.show();
            }
        });




        //Toast.makeText(this,listName.get(0),Toast.LENGTH_SHORT).show();





    }
    private void thongBao(){
        Toast.makeText(this,"Bạn phải nhập dữ liệu!",Toast.LENGTH_SHORT).show();
    }
}