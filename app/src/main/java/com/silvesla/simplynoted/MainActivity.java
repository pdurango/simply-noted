package com.silvesla.simplynoted;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.task_listview);
        itemsList = new ArrayList<>();
        readFile();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsList);
        listView.setAdapter(itemsAdapter);

        //itemsList.add("banana");
        //itemsList.add("beee");
        listViewListener();
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
}
