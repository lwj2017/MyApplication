package com.zucc.lwj31502025.myapplication.DataBase;

import android.content.Context;

import com.zucc.lwj31502025.myapplication.Bean.BeanIC;
import com.zucc.lwj31502025.myapplication.Bean.BeanLicai;
import com.zucc.lwj31502025.myapplication.Bean.BeanPlan;

import java.util.ArrayList;
import java.util.List;

public class DataBaseServer {
    // 日程管理数据操作
    public static void addPlan(Context context, BeanPlan plan) {
        DataBaseDao dataBaseDao = DataBaseDao.getInstance(context);
        dataBaseDao.addPlan(plan);
    }

    public static void deletePlanById(Context context, int id) {
        DataBaseDao dataBaseDao = DataBaseDao.getInstance(context);
        dataBaseDao.deletePlanById(id);
    }

    public static void updatePlan(Context context, BeanPlan plan) {
        DataBaseDao dataBaseDao = DataBaseDao.getInstance(context);
        dataBaseDao.updatePlan(plan);
    }

    public static ArrayList<BeanPlan> searchPlanByDate(Context context, String date) {
        DataBaseDao dataBaseDao = DataBaseDao.getInstance(context);
        return dataBaseDao.searchPlanByDate(date);
    }

    public static BeanPlan searchPlanById(Context context, int id) {
        DataBaseDao dataBaseDao = DataBaseDao.getInstance(context);
        return dataBaseDao.searchPlanById(id);
    }

    public static ArrayList<BeanPlan> searchByIndistinct(Context context, String plan) {
        DataBaseDao dataBaseDao = DataBaseDao.getInstance(context);
        return dataBaseDao.searchByIndistinct(plan);
    }
    //取得收支的id
    public static int getICId(Context context) {
        DataBaseDao dataBaseDao = DataBaseDao.getInstance(context);
        return dataBaseDao.getICId();
    }

    //收支数据操作
    public static void addIC(Context context, BeanIC cost) {
        DataBaseDao dataBaseDao = DataBaseDao.getInstance(context);
        dataBaseDao.addIC(cost);
    }

    public static void deleteIC(Context context, int id) {
        DataBaseDao dataBaseDao = DataBaseDao.getInstance(context);
        dataBaseDao.deleteIC(id);
    }

    public static void updataIC(Context context, BeanIC cost) {
        DataBaseDao dataBaseDao = DataBaseDao.getInstance(context);
        dataBaseDao.updateIC(cost);
    }

    public static List<BeanIC> searchIC(Context context) {
        DataBaseDao dataBaseDao = DataBaseDao.getInstance(context);
        return dataBaseDao.searchIC();
    }
    public static List<BeanIC>searchIncome(Context context)
    {
        DataBaseDao dataBaseDao = DataBaseDao.getInstance(context);
        return dataBaseDao.searchCost();
    }
    public static List<BeanIC>searchCost(Context context)
    {
        DataBaseDao dataBaseDao = DataBaseDao.getInstance(context);
        return dataBaseDao.searchIncome();
    }
}
