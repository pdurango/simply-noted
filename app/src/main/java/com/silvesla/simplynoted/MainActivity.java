package com.silvesla.simplynoted;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskDialog.TaskDialogListener {

    private ListView listView;
    private TaskListAdapter listAdapter;
    private List<Task> mTaskList;
    File taskFile;
    public FloatingActionButton addButton;
    private Boolean firstTime = null;


    /* TODO
    Fix task id's
    Clean code
     */
    @Override
    public void onResume() {
        super.onResume();
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
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
        addButton = findViewById(R.id.add_fab);

        isFirstTime();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    public void openDialog() {
        TaskDialog taskDialog = new TaskDialog();
        taskDialog.show(getSupportFragmentManager(), "Dialog");
    }

    @Override
    public void applyText(String task) {

        if(task.equals(""));
        else {
            mTaskList.add(0, new Task(1, task, "Date created: " + getDateTime()));
            listAdapter.notifyDataSetChanged();
            writeFile();
        }
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "Hold to delete", Toast.LENGTH_SHORT).show();
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

    private void readFile() {
        try {
            FileInputStream fis = new FileInputStream(taskFile);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
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

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private boolean isFirstTime() {
        if (firstTime == null) {
            SharedPreferences mPreferences = this.getSharedPreferences("first_time", Context.MODE_PRIVATE);
            firstTime = mPreferences.getBoolean("firstTime", true);
            if (firstTime) {
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putBoolean("firstTime", false);
                editor.commit();

                mTaskList.add(new Task(1, "Hold me to delete!", ""));
                listAdapter.notifyDataSetChanged();
            }
        }
        return firstTime;
    }
}