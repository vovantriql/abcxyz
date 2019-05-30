package com.example.qlsv1.adaptor;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.qlsv1.Model.Lop;
import com.example.qlsv1.R;

import java.util.List;


public class LopAdaptor extends ArrayAdapter<Lop> {
    Activity contextA;
    int resource;
    private Context context;
    private List<Lop> lopList;

    //load du lieu lop List
    public LopAdaptor(Activity context, int resource){
        super(context ,resource);
        this.context=context;
        this.resource=resource;
    }

    public LopAdaptor(@NonNull Context context, @LayoutRes int resource, @NonNull List<Lop> objects){
        super(context,resource,objects);
        this.context = context;
        this.resource=resource;
        this.lopList = objects;
    }

    //load du lieu qua man hinh update
    public View getView(int postion, View convertView, ViewGroup parent){

        //View custom=contextA.getLayoutInflater().inflate(resource, null);
        View custom = LayoutInflater.from(context).inflate(R.layout.item, parent, false );
        TextView txtIdLop=custom.findViewById(R.id.txtID);
        TextView txtTenLop=custom.findViewById(R.id.txtTenLop);
        TextView txtNganh=custom.findViewById(R.id.txtTenNganh);

        Lop lop=lopList.get(postion);
        txtIdLop.setText(String.valueOf(lop.getIdlop()));
        txtTenLop.setText(lop.getTenLop());
        txtNganh.setText(lop.getNganh());

        return custom;
    }
}
