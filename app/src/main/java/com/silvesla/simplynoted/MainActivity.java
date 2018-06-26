package com.silvesla.simplynoted;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private TaskListAdapter listAdapter;
    private List<Task> mTaskList;
    File taskFile;

    @Override
    public void onResume() {
        super.onResume();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.task_listview);
        mTaskList = new ArrayList<>();
        taskFile = new File(getFilesDir(), "tasks.txt");
        readFile();

        /*
        mTaskList.add(new Task(1, "yes", "no"));
        mTaskList.add(new Task(1, "n0", "y3s"));
        */

        listAdapter = new TaskListAdapter(getApplicationContext(), mTaskList);
        listView.setAdapter(listAdapter);

        listViewListener();
    }

    private void listViewListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                mTaskList.remove(position);
                listAdapter.notifyDataSetChanged();
                //Log.d("nice", mTaskList.toString());
                writeFile();
                return true;
            }
        });
    }

    private void writeFile() {
        try {

            FileOutputStream fos = new FileOutputStream(taskFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Task task : mTaskList) {
                oos.writeObject(task);
                //Log.d("nice", "Name: " + task.getName() + " Date: " + task.getDateCreated());
            }
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile()
    {
        try {
            FileInputStream fis = new FileInputStream(taskFile);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while(true)
            {
                Task task = (Task) ois.readObject();
                mTaskList.add(task);
            }
            //ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addNewItem(View view) {

        TextView addItem = (TextView) findViewById(R.id.task_textview);
        String item = addItem.getText().toString();

        if (item.equals("") || item.equals("Add a todo")) ;
        else {
            mTaskList.add(new Task(1, item, "Date created: " + getDateTime()));
            listAdapter.notifyDataSetChanged();
        }
        addItem.setText("");
        writeFile();
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
}