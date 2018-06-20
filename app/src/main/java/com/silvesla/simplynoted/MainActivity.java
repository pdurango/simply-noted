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
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<HashMap<String, String>> itemsList;
    SimpleAdapter itemsAdapter;
    HashMap<String, String> nameAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.task_listview);

        DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
        String curDate = dateFormat.format(new Date());


        nameAddress = new HashMap<>();

        nameAddress.put("Bill", "1212");
        nameAddress.put("Dillon", "2123");
        nameAddress.put("Jodie", "5212");

        itemsList = new ArrayList<>();
        itemsAdapter = new SimpleAdapter(this, itemsList, R.layout.list_item, new String[]{"First Line", "Second Line"}, new int[]{R.id.text1, R.id.text2});
        Iterator iterator = nameAddress.entrySet().iterator();
        listView.setAdapter(itemsAdapter);
        listViewListener();


        while (iterator.hasNext())
        {
            HashMap<String, String> resultsMap = new HashMap<>();
            Map.Entry pair = (Map.Entry)iterator.next();
            resultsMap.put("First Line", pair.getKey().toString());
            resultsMap.put("Second Line", pair.getValue().toString());
            itemsList.add(resultsMap);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void addNewItem(View view) {
       /* TextView addItem = (TextView) findViewById(R.id.task_textview);
        String item = addItem.getText().toString();
        if (item.equals("") || item.equals("Add a todo")) ;
        else
            itemsAdapter.add(item);
        addItem.setText(""); */
    }



    private void listViewListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                itemsList.remove(position);
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

}


    /*
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
*/
//https://stackoverflow.com/questions/45054908/how-to-add-a-gesture-detector-to-a-view-in-android

