package com.example.gomental;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;


import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version + 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "create table users (username text, email text, password text,role text)";
        sqLiteDatabase.execSQL(qry1);

        String qry2 = "create table cart (username text,product text, price float, otype text)";
        sqLiteDatabase.execSQL(qry2);

        String qry3 = "create table orderplace (username text,fullname text, address text, contactno text, pincode int,date text,time text,amount float,otype text)";
        sqLiteDatabase.execSQL(qry3);

        String qry4 = "create table call (username text, date text, time text, duration text)";
        sqLiteDatabase.execSQL(qry4);

        String qry5 = "create table text (username text, date text, time text)";
        sqLiteDatabase.execSQL(qry5);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS cart");
        db.execSQL("DROP TABLE IF EXISTS orderplace");
    }

    public Boolean register (String username, String email, String password, String role){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        cv.put("role", role);
        long result = db.insert("users", null, cv);
        if(result != -1){
            return false;
        }else{
            return true;
        }
    }

    public int login(String username, String password, String role) {
        int result = 0;
        String str[] = new String[3];
        str[0] = username;
        str[1] = password;
        str[2] = role;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username=? and password=? and role=?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
        c.close();
       return result;

    }

    /*
    public void addCart(String username, String product, float price, String otype) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("product", product);
        cv.put("price", price);
        cv.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart", null, cv);
        db.close();
    }
    */

    public void addCart(String username, String product, float price, String otype) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("otype", otype);
        values.put("product", product);
        values.put("price", price);

        db.insert("cart", null, values);
        db.close();
    }



    public int checkCart(String username, String product) {
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from cart where username = ? and product = ?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
        db.close();
        return result;
    }


  /*  public ArrayList getCartData(String username, String otype) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        Cursor c = db.rawQuery("select * from cart where username = ? and otype = ?", str);
        if (c.moveToFirst()) {
            do {
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product + "$" + price);
            } while (c.moveToNext());
        }
        db.close();
        return arr;


    } */

    public void removeCart(String username, String otype) {
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        SQLiteDatabase db = getWritableDatabase();

        db.delete("cart", "username=? and otype=?", str);
        db.close();
    }


    public ArrayList<String> getCartData(String username, String otype) {
        ArrayList<String> cartData = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"product", "price"};
        String selection = "username=? AND otype=?";
        String[] selectionArgs = {username, otype};

        Cursor c = db.query("cart", columns, selection, selectionArgs, null, null, null);

        int productIndex = c.getColumnIndex("product");
        int priceIndex = c.getColumnIndex("price");

        while (c.moveToNext()) {
            if (productIndex != -1 && priceIndex != -1) {
                String product = c.getString(productIndex);
                double price = c.getDouble(priceIndex);
                String item = product + "$" + price;
                cartData.add(item);
            }
        }

        c.close();
        return cartData;
    }

    public void addOrder(String username, String fullname, String address, String contact, int pincode, String date, String time, float price, String otype){

        ContentValues cv = new ContentValues ();
        cv.put ("username", username);
        cv.put ("fullname", fullname);
        cv.put ("address", address);
        cv.put ("contactno", contact);
        cv.put ("pincode", pincode);
        cv.put ("date", date);
        cv.put ("time", time);
        cv.put ("amount", price);
        cv.put ("otype", otype);
        SQLiteDatabase db = getWritableDatabase ();
        db. insert( "orderplace", null, cv);
        db.close ();

    }

    public ArrayList getOrderData(String username){
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0] = username;
        Cursor c = db.rawQuery("SELECT * FROM orderplace WHERE username = ?", str);
        if (c.moveToFirst()) {
            do {
                arr.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)+"$"+c.getString(5)+"$"+c.getString(6)+"$"+c.getString(7)+"$"+c.getString(8));
            } while (c.moveToNext());
        }
        db.close();
        return arr;
    }



    public void addCallHistory(String name, String dateTime, String duration) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("date_time", dateTime);
        values.put("duration", duration);

        db.insert("call_history", null, values);
        db.close();
    }

    public void addCall(String username, String date, String time, String duration) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("date", date);
        values.put("time", time);
        values.put("duration", duration);

        db.insert("call", null, values);
        db.close();
    }

    public String getRoleByUsername(String username) {
        String role = "";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT role FROM users WHERE username=?", new String[]{username});
        if (c.moveToFirst()) {
            int columnIndex = c.getColumnIndex("role");
            if (columnIndex >= 0) {
                role = c.getString(columnIndex);
            }
        }
        c.close();
        return role;

    }

}
