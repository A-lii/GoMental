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
import java.util.Arrays;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version + 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "create table users (username text, email text, password text)"; //,role text)";
        sqLiteDatabase.execSQL(qry1);

        String qry2 = "create table cart (username text,product text, price float, otype text)";
        sqLiteDatabase.execSQL(qry2);

        String qry3 = "create table orderplace (username text,fullname text, address text, contactno text, pincode int,date text,time text,amount float,otype text)";
        sqLiteDatabase.execSQL(qry3);

        String qry4 = "create table call (username text, date text, time text, duration text)";
        sqLiteDatabase.execSQL(qry4);

        String qry5 = "create table text (username text, date text, time text)";
        sqLiteDatabase.execSQL(qry5);

        String qry6 = "CREATE TABLE orders (username TEXT, fullname TEXT, address TEXT, contactno TEXT, date TEXT, time TEXT)";
        sqLiteDatabase.execSQL(qry6);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS cart");
        db.execSQL("DROP TABLE IF EXISTS orderplace");
    }

    public void register (String username, String email, String password) // String role)
    {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        //cv.put("role", role);
        /*long result = db.insert("users", null, cv);
        if(result != -1){
            return false;
        }else{
            return true;
        }
         */
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv);
        db.close();
    }

    public int login(String username, String password) //, String role)
     {
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = password;
        //str[2] = role;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username=? and password=?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
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

    public int checkAppointmentExists(String username, String fullname, String address, String contact, String date, String time) {
        int result = 0;
        String str[] = new String[6];
        str[0] = username;
        str[1] = fullname;
        str[2] = address;
        str[3] = contact;
        str[2] = date;
        str[3] = time;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from orderplace where username = ? and fullname = ? and address = ? and contactno = ? and date = ? and time = ?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
        db.close();
        return result;
    }
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

/*
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

 */

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
                String price = c.getString(priceIndex);
                String item = product + "$" + price;
                cartData.add(item);
            }
        }

        c.close();
        db.close();
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

    /*
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

     */

    public ArrayList<String[]> getOrderData(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String[]> orderData = new ArrayList<>();

        // Query the database and retrieve the order data
        Cursor c = db.rawQuery("SELECT * FROM orders WHERE username = ?", new String[]{username});
        if (c.moveToFirst()) {
            do {
                // Retrieve the columns using column names
                String fullname = c.getString(c.getColumnIndexOrThrow("fullname"));
                String address = c.getString(c.getColumnIndexOrThrow("address"));
                String contactno = c.getString(c.getColumnIndexOrThrow("contactno"));
                String date = c.getString(c.getColumnIndexOrThrow("date"));
                String time = c.getString(c.getColumnIndexOrThrow("time"));


                // Create a string array to hold the order details
                String[] orderDetails = new String[]{fullname, address, contactno, date, time};

                // Add the order details to the list
                orderData.add(orderDetails);
            } while (c.moveToNext());
        }
        c.close();
        return orderData;
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
