package com.example.qlsv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qlsv1.Model.Lop;
import com.example.qlsv1.sqlite.LopDao;

import java.util.List;

public class LopActivity extends AppCompatActivity {
    EditText edtIdlop,edtTenLop,edtNganh;
    List<Lop> lopList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lop);
        addControls();
    }

    private void addControls(){
        edtIdlop=findViewById(R.id.edtIdlop);
        edtTenLop=findViewById(R.id.edtTenLop);
        edtNganh=findViewById(R.id.edtNganh);
    }

    public void xulythem(View view) {
        final LopDao lopDao = new LopDao(this);
        Lop lop=new Lop();
        lop.setIdlop(Integer.parseInt(edtIdlop.getText().toString()));
        lop.setTenLop(edtTenLop.getText().toString());
        lop.setNganh(edtNganh.getText().toString());
        long kq = lopDao.insert(lop);


        lopList = lopDao.getLopBy(edtIdlop.getText().toString());
        if (!lopList.isEmpty()){
            Toast.makeText(LopActivity.this,"ID đã tồn tại. Vui lòng nhập ID khác", Toast.LENGTH_LONG).show();
            return;
        }
        //lopDao.getLopId(Integer.parseInt(edtIdlop.getText().toString()));

        if (kq>0){
            Toast.makeText(LopActivity.this,"thêm mới thành công", Toast.LENGTH_LONG).show();
            // Hàm hiển thị listview vừa nhập ở màng hình khác
            Intent intent= new Intent(LopActivity.this,MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(LopActivity.this,"thêm mới thất bại",Toast.LENGTH_LONG).show();
        }
    }
}
