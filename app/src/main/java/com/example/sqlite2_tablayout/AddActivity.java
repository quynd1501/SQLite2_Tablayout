package com.example.sqlite2_tablayout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;


import com.example.sqlite2_tablayout.model.CongViec;
import com.example.sqlite2_tablayout.sqlite.SQLiteHelper;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner sp;
    private EditText eTen,eNoiDung,eNgay;
    private RadioButton rb1,rb2;
    private Button btAdd,btCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        sp=findViewById(R.id.spCategory);
        eTen=findViewById(R.id.tvTenCV);
        eNoiDung=findViewById(R.id.tvNoiDung);
        eNgay=findViewById(R.id.tvDate);
        rb1=findViewById(R.id.rb1);//Lam chung
        rb2=findViewById(R.id.rb2);//Mot minh
        btAdd=findViewById(R.id.btAdd);
        btCancel=findViewById(R.id.btCancel);
        sp.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.tinhtrang)));
        btAdd.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        eNgay.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==eNgay){
            final Calendar c=Calendar.getInstance();
            int year= c.get(Calendar.YEAR);
            int month=c.get(Calendar.MONTH);
            int day=c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog=new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    String date="";
                    if(m>0){
                        date=d+"/"+(m+1)+"/"+y;
                    }else
                        date=d+"/0"+(m+1)+"/"+y;
                    eNgay.setText(date);
                }
            },year,month,day);
            dialog.show();
        }
        if (view==btAdd){
            String ten=eTen.getText().toString();
            String noidung=eNoiDung.getText().toString();
            String ngay=eNgay.getText().toString();
            String tinhtrang=sp.getSelectedItem().toString();
            String congtac="";
            if(rb1.isChecked())
                congtac="True";
            else if (rb2.isChecked())
                congtac="False";
            if(!ten.isEmpty() && !noidung.isEmpty()){
                CongViec congViec=new CongViec(ten,noidung,ngay,tinhtrang,congtac);
                SQLiteHelper db=new SQLiteHelper(this);
                db.addCongViec(congViec);
                finish();
            }
        }
        if (view==btCancel){
            finish();
        }
    }
}