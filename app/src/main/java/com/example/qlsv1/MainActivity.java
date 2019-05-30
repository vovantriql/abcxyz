package com.example.qlsv1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.qlsv1.Model.Lop;
import com.example.qlsv1.adaptor.LopAdaptor;
import com.example.qlsv1.sqlite.DbHelper;
import com.example.qlsv1.sqlite.LopDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listLop;
    List<Lop> adaptor;
    LopAdaptor lopAdaptor;
    List<Lop> lopList;
    public static Lop selectedLop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadDulieu();
        setAdapter();
        addEvent();
    }

    private void addEvent() {
        listLop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               selectedLop = lopAdaptor.getItem(position);
            }
        });
        listLop.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedLop = lopAdaptor.getItem(position);
                return false;
            }
        });
    }

    private  void loadDulieu(){
        final LopDao lopDao = new LopDao(this);
        lopList = lopDao.getLopAll1();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.mnuAdd){
            Intent intent= new Intent(MainActivity.this,LopActivity.class);
            startActivity(intent);
        }

        if(item.getItemId()==R.id.mnuUpdate)
        {
            if(selectedLop!=null){
                Intent intent = new Intent(MainActivity.this,LopUpdateActivity.class);
                startActivity(intent);
                //Toast.makeText(MainActivity.this, "Không sửa được", Toast.LENGTH_LONG).show();
            } else{
                Toast.makeText(MainActivity.this, "Không sửa được", Toast.LENGTH_LONG).show();
                //Toast.makeText(MainActivity.this, "Không sửa được", Toast.LENGTH_LONG).show();
            }
        };
        return super.onOptionsItemSelected(item);
    }

    private void getData() {
        SQLiteDatabase db;
        DbHelper dbHelper = new DbHelper(this);
        db = dbHelper.getWritableDatabase();
        Cursor c= db.rawQuery("SELECT * FROM Lop",null);
        adaptor.clear();
        while (c.moveToNext()){
            //khoi tao class de gan du lieu tu database qua cac thuoc tinh trong class Lop
            Lop lop=new Lop();
            //set, gan du lieu tu database vao bien
            lop.setIdlop (c.getColumnIndex("IdLop"));
            lop.setTenLop (c.getString(c.getColumnIndex("TenLop")));
            lop.setNganh (c.getString(c.getColumnIndex("Nganh")));
            //sau do dd vao list lop
            adaptor.add(lop);
        }
        c.close();
    }

    private void setAdapter() {
        listLop=findViewById(R.id.listLop);
        lopAdaptor =  new LopAdaptor(this, R.layout.item,lopList);
        listLop.setAdapter(lopAdaptor);
    }

}
