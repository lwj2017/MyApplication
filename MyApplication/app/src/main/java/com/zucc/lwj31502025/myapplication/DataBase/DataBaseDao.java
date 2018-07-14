package com.zucc.lwj31502025.myapplication.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.zucc.lwj31502025.myapplication.Bean.BeanIC;
import com.zucc.lwj31502025.myapplication.Bean.BeanLicai;
import com.zucc.lwj31502025.myapplication.Bean.BeanPlan;

import java.util.ArrayList;
import java.util.List;

public class DataBaseDao {
    private DataBaseHelper helper;
    private static DataBaseDao dataBaseDao;

    private DataBaseDao(Context context) {
        this.helper = DataBaseHelper.getInstance(context);
    }

    public static DataBaseDao getInstance(Context context) {
        if (dataBaseDao == null)
            dataBaseDao = new DataBaseDao(context);
        return dataBaseDao;

    }
    //用户
    public void addUser(BeanLicai user) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", user.getUsername());
        cv.put("password", user.getPassword());
        db.insert("user", null, cv);
        db.close();
    }

    public void updateUser(BeanLicai user) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", user.getUsername());
        cv.put("password", user.getPassword());
        db.update("user", cv, "_id = ?", new String[]{String.valueOf(user.getId())});
        db.close();
    }
    public List<BeanLicai> searchUser(String[] username) {
        SQLiteDatabase db = helper.getWritableDatabase();
        List<BeanLicai> list = new ArrayList<>();
        Cursor cur = db.query("user", null, "username=?", username, null, null, null);
        while (cur.moveToNext()) {
            BeanLicai user = new BeanLicai();
            user.setId(cur.getInt(cur.getColumnIndex("_id")));
            user.setUsername(cur.getString(cur.getColumnIndex("username")));
            user.setPassword(cur.getString(cur.getColumnIndex("password")));
            list.add(user);
        }
        db.close();
        cur.close();
        return list;
    }
  //收支
    public void addIC(BeanIC ic) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("money", ic.getMoney());
        cv.put("incomeCostDate", ic.getIcDate());
        cv.put("incomeCostType", ic.getIcType());
        cv.put("source", ic.getSource());
        db.insert("ic", null, cv);
        db.close();
    }

    public void updateIC(BeanIC ic) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("money", ic.getMoney());
        cv.put("incomeCostDate", ic.getIcDate());
        cv.put("incomeCostType", ic.getIcType());
        cv.put("source", ic.getSource());
        db.update("ic", cv, "_id = ?", new String[]{String.valueOf(ic.getId())});
        db.close();
    }

    public void deleteIC(int id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("ic", "_id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<BeanIC> searchIC() {
        SQLiteDatabase db = helper.getWritableDatabase();
        List<BeanIC> list = new ArrayList<>();
        Cursor cur = db.query("ic", null, null, null, null, null, "incomeCostDate asc");
        while (cur.moveToNext()) {
            BeanIC ic = new BeanIC();
            ic.setId(cur.getInt(cur.getColumnIndex("_id")));
            ic.setMoney(cur.getFloat(cur.getColumnIndex("money")));
            ic.setIcType(cur.getInt(cur.getColumnIndex("incomeCostType")));
            ic.setSource(cur.getString(cur.getColumnIndex("source")));
            ic.setIcDate(cur.getString(cur.getColumnIndex("incomeCostDate")));
            list.add(ic);
        }
        db.close();
        cur.close();
        return list;
    }
    public List<BeanIC> searchIncome() {
        SQLiteDatabase db = helper.getWritableDatabase();
        List<BeanIC> list = new ArrayList<>();
        Cursor cur = db.query("ic", null, "money > 0", null, null, null, "incomeCostDate asc");
        while (cur.moveToNext()) {
            BeanIC ic = new BeanIC();
            ic.setId(cur.getInt(cur.getColumnIndex("_id")));
            ic.setMoney(cur.getFloat(cur.getColumnIndex("money")));
            ic.setSource(cur.getString(cur.getColumnIndex("source")));
            ic.setIcDate(cur.getString(cur.getColumnIndex("incomeCostDate")));
            list.add(ic);
        }
        db.close();
        cur.close();
        return list;
    }

    public List<BeanIC> searchCost() {
        SQLiteDatabase db = helper.getWritableDatabase();
        List<BeanIC> list = new ArrayList<>();
        Cursor cur = db.query("ic", null, "money < 0", null, null, null, "incomeCostDate asc");
        while (cur.moveToNext()) {
            BeanIC ic = new BeanIC();
            ic.setId(cur.getInt(cur.getColumnIndex("_id")));
            ic.setMoney(cur.getFloat(cur.getColumnIndex("money")));
            ic.setSource(cur.getString(cur.getColumnIndex("source")));
            ic.setIcDate(cur.getString(cur.getColumnIndex("incomeCostDate")));
            list.add(ic);
        }
        db.close();
        cur.close();
        return list;
    }

    public int getICId(){
        String sql="select * from cost order by _id desc limit 0,1";
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToNext();
        return cursor.getInt(cursor.getColumnIndex("_id"));
    }
    //日程
    public void addPlan(BeanPlan plan) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", plan.getTitle());
        values.put("date", plan.getDate());
        values.put("hour", plan.getHour());
        values.put("minutes", plan.getMinutes());
        values.put("postscript", plan.getPostScript());
        values.put("address",plan.getAddress());
        db.insert("plan1", "_id", values);
        db.close();
    }

    public ArrayList<BeanPlan> searchPlanByDate(String date) {
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query("plan1", new String[]{"_id", "date", "title", "hour", "minutes", "postscript","address"},
                " date = ?", new String[]{date}, null, null, "_id asc");
        ArrayList<BeanPlan> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            BeanPlan plan = new BeanPlan();
            plan.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            plan.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            plan.setDate(cursor.getString(cursor.getColumnIndex("date")));
            plan.setHour(cursor.getString(cursor.getColumnIndex("hour")));
            plan.setMinutes(cursor.getString(cursor.getColumnIndex("minutes")));
            plan.setPostScript(cursor.getString(cursor.getColumnIndex("postscript")));
            plan.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            list.add(plan);
        }
        db.close();
        cursor.close();
        return list;
    }

    public BeanPlan searchPlanById(int id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query("plan1", new String[]{"_id", "date", "title", "hour", "minutes",
                "postscript","address"}, " _id = ?", new String[]{String.valueOf(id)}, null, null, "_id asc");
        BeanPlan plan = null;
        if (cursor.moveToNext()) {
            plan = new BeanPlan();
            plan.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            plan.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            plan.setDate(cursor.getString(cursor.getColumnIndex("date")));
            plan.setHour(cursor.getString(cursor.getColumnIndex("hour")));
            plan.setMinutes(cursor.getString(cursor.getColumnIndex("minutes")));
            plan.setPostScript(cursor.getString(cursor.getColumnIndex("postscript")));
            plan.setAddress(cursor.getString(cursor.getColumnIndex("address")));
        }
        db.close();
        cursor.close();
        return plan;
    }

    public void deletePlanById(int id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("plan1", "_id=?", new String[]{String.valueOf(id)});
    }

    public void updatePlan(BeanPlan plan) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", plan.getTitle());
        cv.put("date", plan.getDate());
        cv.put("hour", plan.getHour());
        cv.put("minutes", plan.getMinutes());
        cv.put("postscript", plan.getPostScript());
        cv.put("address",plan.getAddress());
        db.update("plan1", cv, "_id = ?", new String[]{String.valueOf(plan.getId())});
        Log.e("time",plan.getId()+" "+plan.getHour()+":"+plan.getMinutes());
        db.close();
    }

    public ArrayList<BeanPlan> searchByIndistinct(String title) {
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query("plan1", new String[]{"_id", "date", "title", "hour", "minutes", "postscript","address"},
                "title like '%" + title + "%'" + " or postscript like '%" + title + "%'", null, null, null, "_id asc");
        ArrayList<BeanPlan> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            BeanPlan plan = new BeanPlan();
            plan.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            plan.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            plan.setDate(cursor.getString(cursor.getColumnIndex("date")));
            plan.setHour(cursor.getString(cursor.getColumnIndex("hour")));
            plan.setMinutes(cursor.getString(cursor.getColumnIndex("minutes")));
            plan.setPostScript(cursor.getString(cursor.getColumnIndex("postscript")));
            plan.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            list.add(plan);
        }
        db.close();
        cursor.close();
        return list;
    }
}
