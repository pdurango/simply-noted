package com.silvesla.simplynoted;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> itemsList;
    ArrayAdapter<String> itemsAdapter;
    ListView listView;

    private GestureDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.task_listview);
        itemsList = new ArrayList<>();
        readFile();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsList);
        listView.setAdapter(itemsAdapter);
        listViewListener();

        //mDetector = new GestureDetector(this, new MyGestureListener());
        //listView.setOnTouchListener(touchListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void addNewItem(View view) {
        TextView addItem = (TextView) findViewById(R.id.task_textview);
        String item = addItem.getText().toString();
        if (item.equals("") || item.equals("Add a todo")) ;
        else
            itemsAdapter.add(item);
        addItem.setText("");
        writeFile();
    }

    private void readFile() {
        File fileLoc = getFilesDir();
        File file = new File(fileLoc, "simpletodo.txt");
        try {
            itemsList = new ArrayList<String>(FileUtils.readLines(file));
        } catch (IOException e) {
            itemsList = new ArrayList<String>();
        }
    }

    private void writeFile() {
        File fileLoc = getFilesDir();
        File file = new File(fileLoc, "simpletodo.txt");
        try {
            FileUtils.writeLines(file, itemsList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void listViewListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                itemsList.remove(position);
                itemsAdapter.notifyDataSetChanged();
                writeFile();
                return true;
            }
        });
    }

    View.OnTouchListener touchListener = new View.OnTouchListener() {
        public boolean onTouch(View view, MotionEvent event) {
        return mDetector.onTouchEvent(event);
        }
    };

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent event)
        {
            Log.d("TAG", "onDown: ");
            return true;
        }
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i("TAG", "onSingleTapConfirmed: ");
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.i("TAG", "onLongPress: ");
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i("TAG", "onDoubleTap: ");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            Log.i("TAG", "onScroll: ");
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            Log.d("TAG", "onFling: ");
            return true;
        }
    }
}

//https://stackoverflow.com/questions/45054908/how-to-add-a-gesture-detector-to-a-view-in-android

