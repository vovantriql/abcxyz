package com.example.qlsv1.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.qlsv1.Model.Lop;

import java.util.ArrayList;
import java.util.List;


public class LopDao {
    private SQLiteDatabase db;
    public LopDao(Context context){
        DbHelper dbHelper=new DbHelper(context);
        db=dbHelper.getWritableDatabase();
    }

    public List<Lop> getLopBy(String idLop) {
        String sql="SELECT * FROM lop WHERE IdLop=?";
        List<Lop> list =getLop(sql,idLop);

        return list;

    }

    public List<Lop> getLopAll1() {
        List<Lop> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM Lop ", null);
        while (c.moveToNext()) {
            //khởi tạo class
            Lop lop = new Lop();
            lop.setIdlop(c.getInt(0));
            lop.setTenLop(c.getString(c.getColumnIndex("TenLop")));
            lop.setNganh(c.getString(c.getColumnIndex("Nganh")));

            list.add(lop);
        }
        return list;

    }

    public List<Lop> getLop(String sql,String... selectionArgs) {
        List<Lop> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            //khởi tạo class
            Lop lop = new Lop();
            lop.setIdlop(c.getColumnIndex("IdLop"));
            lop.setTenLop(c.getString(c.getColumnIndex("TenLop")));
            lop.setNganh(c.getString(c.getColumnIndex("Nganh")));

            list.add(lop);
        }
        return list;

    }
    //phương thức truy vấn lấy tất cả danh sách lớp
    public List<Lop>getLopAll(){
        String sql = "SELECT * FROM Lop";
        return getLop(sql);
    }

    //phương thức truy vấn lấy dữ liệu thông qua điều kiện
    public Lop getLopId(String idLop){
        String sql="SELECT * FROM lop WHERE IdLop=?";
        List<Lop> list =getLop(sql,idLop);
        return list.get(0);
    }

    //phương thức thêm dữ liệu vào database
    public long insert(Lop l)
    {
        ContentValues values=new ContentValues();
        values.put("IdLop",l.getIdlop());
        values.put("TenLop",l.getTenLop());
        values.put("Nganh",l.getNganh());
        return db.insert("Lop",null,values);
    }

    public int update(Lop l){
        ContentValues values= new ContentValues();
        values.put("IdLop",l.getIdlop());
        values.put("TenLop",l.getTenLop());
        values.put("Nganh",l.getNganh());
        return db.update("Lop",values,"IdLop=?",new String[]{String.valueOf(l.getIdlop())});
    }
    public int delete(String idLop){
        return db.delete("Lop", "IdLop", new String[]{idLop});
    }
}
