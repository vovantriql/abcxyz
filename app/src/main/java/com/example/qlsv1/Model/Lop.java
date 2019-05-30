package com.example.qlsv1.Model;

import java.io.Serializable;

public class Lop implements Serializable {
    private int idlop;
    private String tenLop;
    private String nganh;

    public int getIdlop(){
        return idlop;
    }
    public void setIdlop(int idlop){
        this.idlop=idlop;
    }
    public String getTenLop(){
        return tenLop;
    }
    public  void setTenLop(String tenLop){
        this.tenLop=tenLop;
    }
    public String getNganh(){
        return nganh;
    }
    public void setNganh(String nganh){
        this.nganh = nganh;
    }
}
