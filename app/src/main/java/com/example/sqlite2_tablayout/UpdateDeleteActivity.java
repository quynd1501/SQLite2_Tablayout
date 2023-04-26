package com.example.sqlite2_tablayout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner sp;
    private EditText eTen,eNoiDung,eNgay;
    private RadioButton rb1,rb2;
    private Button btUpdate,btDel,btBack;
    private CongViec congViec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        sp=findViewById(R.id.spCategory);
        eTen=findViewById(R.id.tvTenCV);
        eNoiDung=findViewById(R.id.tvNoiDung);
        eNgay=findViewById(R.id.tvDate);
        rb1=findViewById(R.id.rb1);//Lam chung
        rb2=findViewById(R.id.rb2);//Mot minh
        btUpdate=findViewById(R.id.btUpdate);
        btDel=findViewById(R.id.btDel);
        btBack=findViewById(R.id.btBack);
        sp.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,
                getResources().getStringArray(R.array.tinhtrang)));
        btUpdate.setOnClickListener(this);
        btBack.setOnClickListener(this);
        btDel.setOnClickListener(this);
        eNgay.setOnClickListener(this);


        //nhan doi tuong dc truyen tu item len
        Intent intent=getIntent();
        congViec=(CongViec) intent.getSerializableExtra("congviec");
        eTen.setText(congViec.getTenCV());
        eNoiDung.setText(congViec.getNoidungCV());
        eNgay.setText(congViec.getNgayHT());
        if(congViec.getCongtac().equalsIgnoreCase("True")){
            rb1.setChecked(true);
        }
        if(congViec.getCongtac().equalsIgnoreCase("False")){
            rb2.setChecked(true);
        }
        int p=0;
        for(int i=0;i<sp.getCount();i++){
            if(sp.getItemAtPosition(i).toString().equalsIgnoreCase(congViec.getTinhtrang())){
                p=i;
                break;
            }
        }
        sp.setSelection(p);
    }

    @Override
    public void onClick(View view) {
        SQLiteHelper db= new SQLiteHelper(this);
        if(view==eNgay){
            final Calendar c=Calendar.getInstance();
            int year=c.get(Calendar.YEAR);
            int month=c.get(Calendar.MONTH);
            int day=c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog=new DatePickerDialog(UpdateDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    String date="";
                    if(m>8){
                        date=d+"/"+(m+1)+"/"+y;
                    }else
                        date=d+"/0"+(m+1)+"/"+y;
                    eNgay.setText(date);
                }
            },year,month,day);
            dialog.show();
        }
        if(view==btBack){
            finish();
        }
        if(view==btUpdate){
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
                int id=congViec.getId();
                CongViec congViec=new CongViec(id,ten,noidung,ngay,tinhtrang,congtac);
                db=new SQLiteHelper(this);
                db.update(congViec);
                finish();
            }
        }
        if(view==btDel){
            int id=congViec.getId();
            AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thong bao");
            builder.setMessage("Ban co muon xoa "+congViec.getTenCV()+" khong?");
            builder.setIcon(R.drawable.ic_delete);
            builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SQLiteHelper bb=new SQLiteHelper(getApplicationContext());
                    bb.delete(id);
                    finish();
                }
            });
            builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog dialog=builder.create();
            dialog.show();
        }
    }
}