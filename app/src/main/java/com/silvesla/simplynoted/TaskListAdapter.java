package com.silvesla.simplynoted;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class TaskListAdapter extends BaseAdapter{

    private Context mContext;
    private List<Task> mTaskList;

    public TaskListAdapter(Context mContext, List<Task> mTaskList) {
        this.mContext = mContext;
        this.mTaskList = mTaskList;
    }

    @Override
    public int getCount() {
        return mTaskList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTaskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.list_item, null);
        TextView taskItem = (TextView) view.findViewById(R.id.task_item);
        TextView dateCreated = (TextView) view.findViewById(R.id.date_created);
        TextView itemId = (TextView) view.findViewById(R.id.item_id);

        taskItem.setText(mTaskList.get(position).getName());
        dateCreated.setText(mTaskList.get(position).getDateCreated());
        itemId.setTag(mTaskList.get(position).getId());

        return view;
    }
}
