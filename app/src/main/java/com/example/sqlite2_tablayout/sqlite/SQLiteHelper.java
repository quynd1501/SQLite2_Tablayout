package com.example.sqlite2_tablayout.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import com.example.sqlite2_tablayout.model.CongViec;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="CongViecDB.db";
    private static int DATABASE_VERSION =1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE congviec("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "ten TEXT,noidung TEXT,ngay TEXT,tinhtrang TEXT,congtac TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }


    //get tat ca cong viec va sap xep theo ngay
    public List<CongViec> getAll(){
        List<CongViec> list=new ArrayList<>();
        SQLiteDatabase st=getReadableDatabase();//nhu lenh select trong database
        String order= "ngay DESC";//tra ve theo ngay giam dan, hay moi nhat len tren
        Cursor rs= st.query("congviec",null,null,null,null
                ,null,order);
        while (rs!=null && rs.moveToNext()){
            int id=rs.getInt(0);
            String ten=rs.getString(1);
            String noidung=rs.getString(2);
            String ngay=rs.getString(3);
            String tinhtrang=rs.getString(4);
            String congtac=rs.getString(5);
            list.add(new CongViec(id,ten,noidung,ngay,tinhtrang,congtac));//lay duoc tat ca cac ban ghi
        }
        return list;
    }

    //add
    public long addCongViec(CongViec i){
        ContentValues values=new ContentValues();
        values.put("ten", i.getTenCV());
        values.put("noidung", i.getNoidungCV());
        values.put("ngay", i.getNgayHT());
        values.put("tinhtrang", i.getTinhtrang());
        values.put("congtac", i.getCongtac());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("congviec",null, values);
    }


    //get by ten
    public List<CongViec> searchByTen(String key){
        List<CongViec> list=new ArrayList<>();
        String whereClause="ten like ?";//dieu kien truy van
        String[] whereArgs={"%"+key+"%"};//xet dieu kien title chua key
        String order= "ngay DESC";//tra ve theo ngay giam dan, hay moi nhat len tren
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query("congviec",null,whereClause,whereArgs,null,
                null,order);
        while (rs!=null && rs.moveToNext()){
            int id=rs.getInt(0);
            String ten=rs.getString(1);
            String noidung=rs.getString(2);
            String ngay=rs.getString(3);
            String tinhtrang=rs.getString(4);
            String congtac=rs.getString(5);
            list.add(new CongViec(id,ten,noidung,ngay,tinhtrang,congtac));//lay duoc tat ca cac ban ghi
        }
        return list;
    }

    //tim kiem theo category
    public List<CongViec> searchByCategory(String cate){
        List<CongViec> list=new ArrayList<>();
        String whereClause="tinhtrang like ?";//dieu kien truy van
        String[] whereArgs={cate};
        String order= "ngay DESC";//tra ve theo ngay giam dan, hay moi nhat len tren
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query("congviec",null,whereClause,whereArgs,null,
                null,order);
        while (rs!=null && rs.moveToNext()){
            int id=rs.getInt(0);
            String ten=rs.getString(1);
            String noidung=rs.getString(2);
            String ngay=rs.getString(3);
            String tinhtrang=rs.getString(4);
            String congtac=rs.getString(5);
            list.add(new CongViec(id,ten,noidung,ngay,tinhtrang,congtac));//lay duoc tat ca cac ban ghi
        }
        return list;
    }



    //update
    public int update(CongViec i){
        ContentValues values=new ContentValues();
        values.put("ten", i.getTenCV());
        values.put("noidung", i.getNoidungCV());
        values.put("ngay", i.getNgayHT());
        values.put("tinhtrang", i.getTinhtrang());
        values.put("congtac", i.getCongtac());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause="id= ?";//dieu kien truy van
        String[] whereArgs={Integer.toString(i.getId())};
        return sqLiteDatabase.update("congviec",values, whereClause,whereArgs);
    }

    //delete
    public int delete(int id){
        String whereClause="id= ?";//dieu kien truy van
        String[] whereArgs={Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("congviec",whereClause,whereArgs);
    }
}
