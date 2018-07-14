package com.zucc.lwj31502025.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zucc.lwj31502025.myapplication.Bean.BeanPlan;
import com.zucc.lwj31502025.myapplication.DataBase.DataBaseServer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FragmentPlan extends Fragment{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    CalendarView calendarView = null;
    ListView noteListView;
    String planDate = "";
    ArrayList<BeanPlan> planDatas = new ArrayList<>();
    TextView txvTip = null;
    RelativeLayout rm;
    long date;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_plan,
                container, false);
        txvTip = (TextView) rootView.findViewById(R.id.txv);
        rm = (RelativeLayout) rootView.findViewById(R.id.adddate);
        rm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPlan();
            }
        });
        calendarView = (CalendarView) rootView.findViewById(R.id.calendarView);
        calendarView.setShowWeekNumber(false);
        date = calendarView.getDate();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar c = Calendar.getInstance();
                c.set(year, month, dayOfMonth);
                date = c.getTime().getTime();
                refreshNoteList();
            }
        });
        noteListView = (ListView) rootView.findViewById(R.id.lv1);
        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
                showPlan(planDatas.get(position));
            }
        });

        return rootView;
    }
    public void onResume() {
        super.onResume();
        refreshNoteList();
    }

    private void refreshNoteList() {
        planDate = getDateString(date);
        loadData();
        if (planDatas != null && planDatas.size() > 0) {
            ListAdapter adapter = new ListAdapter(getActivity().getApplicationContext());
            noteListView.setAdapter(adapter);
            txvTip.setText(planDate + " 日程列表：");
        } else {
            txvTip.setText(planDate + " 暂无记录");
            ListAdapter adapter = new ListAdapter(getActivity().getApplicationContext());
            noteListView.setAdapter(adapter);
        }
    }
    private void loadData() {
        planDatas = DataBaseServer.searchPlanByDate(getActivity().getApplicationContext(), planDate);
    }

    private void newPlan() {
        Intent intent = new Intent(getActivity(), EditPlanActivity.class);
        intent.putExtra("uiType", "newPlan");
        intent.putExtra("planDate", planDate);
        startActivity(intent);
    }

    private void showPlan (BeanPlan plan) {
        Intent intent = new Intent(getActivity(), EditPlanActivity.class);
        intent.putExtra("uiType", "showPlan");
        intent.putExtra("plan", plan);
        intent.putExtra("planDate", planDate);
        startActivity(intent);
    }
    private void editPlan(BeanPlan plan) {
        Intent intent = new Intent(getActivity(), EditPlanActivity.class);
        intent.putExtra("uiType", "editPlan");
        intent.putExtra("plan", plan);
        startActivity(intent);
    }
    private String getDateString(long date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(date);
        return formatter.format(curDate);
    }

    public class ListAdapter extends BaseAdapter {

        private Context mContext = null;
        private LayoutInflater mInflater = null;

        public ListAdapter(Context c) {
            mContext = c;
            mInflater = LayoutInflater.from(this.mContext);
        }
        @Override
        public int getCount() {
            return planDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ListViewHolder holder;
            if (convertView == null) {
                holder = new ListViewHolder();
                convertView = mInflater.inflate(R.layout.list_item, null);
                holder.txvName = (TextView) convertView.findViewById(R.id.txvName);
                holder.subLine = (TextView) convertView.findViewById(R.id.subLine);
                convertView.setTag(holder);
            } else {
                holder = (ListViewHolder) convertView.getTag();
            }
            holder.txvName.setText(planDatas.get(position).getTitle());
            holder.subLine.setVisibility(View.GONE);
            return convertView;
        }

        class ListViewHolder {
            TextView txvName, subLine;
        }
    }

    private void deleteNote(BeanPlan plan) {
        DataBaseServer.deletePlanById(getContext(), plan.getId());
    }
}
