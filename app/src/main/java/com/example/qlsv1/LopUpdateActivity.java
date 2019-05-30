package com.example.qlsv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qlsv1.Model.Lop;
import com.example.qlsv1.sqlite.LopDao;


public class LopUpdateActivity extends AppCompatActivity {

    EditText edtIdlop,edtTenLop,edtNganh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lop_update);

        addControl();
    }

    private void addControl() {
        edtIdlop = findViewById(R.id.edtIdlop);
        edtTenLop= findViewById(R.id.edtTenLop);
        edtNganh=findViewById(R.id.edtNganh);

        edtIdlop.setText(MainActivity.selectedLop.getIdlop()+"");
        edtTenLop.setText(MainActivity.selectedLop.getTenLop());
        edtNganh.setText(MainActivity.selectedLop.getNganh());
        edtIdlop.setEnabled(false);
    }

    public void xulysua(View view) {
        final LopDao lopDao = new LopDao(this);
        Lop lop=new Lop();
        lop.setIdlop(Integer.parseInt(edtIdlop.getText().toString()));
        lop.setTenLop(edtTenLop.getText().toString());
        lop.setNganh(edtNganh.getText().toString());
        long kq = lopDao.update(lop);
        if (kq>0){
            Toast.makeText(LopUpdateActivity.this,"sửa thành công", Toast.LENGTH_LONG).show();
            // Hàm hiển thị listview vừa nhập ở màng hình khác
            Intent intent= new Intent(LopUpdateActivity.this,MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(LopUpdateActivity.this,"sửa thất bại",Toast.LENGTH_LONG).show();
        }
    }

    public void xulyxoa(View view) {
        final LopDao lopDao = new LopDao(this);
        Lop lop=new Lop();
        lop.setIdlop(Integer.parseInt(edtIdlop.getText().toString()));
        //lop.setTenLop(edtTenLop.getText().toString());
        //lop.setNganh(edtNganh.getText().toString());
        long kq = lopDao.delete(edtIdlop.getText().toString());
        if (kq>0){
            Toast.makeText(LopUpdateActivity.this,"Đã xóa", Toast.LENGTH_LONG).show();
            // Hàm hiển thị listview vừa nhập ở màng hình khác
            Intent intent= new Intent(LopUpdateActivity.this,MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(LopUpdateActivity.this,"Chưa xóa",Toast.LENGTH_LONG).show();
        }
    }

    public void xulyquayve(View view) {
        Intent intent= new Intent(LopUpdateActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
