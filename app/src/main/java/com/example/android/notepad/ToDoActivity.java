package com.example.android.notepad;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import org.json.JSONArray;
import org.json.JSONException;

public class ToDoActivity extends Activity {
    private ArrayList<String> todoList;
    private ArrayAdapter<String> adapter;
    private SharedPreferences prefs;
    private static final String TODO_KEY = "todo_list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        todoList = loadTodoList();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todoList);

        ListView listView = findViewById(R.id.todo_list_view);
        listView.setAdapter(adapter);

        EditText editText = findViewById(R.id.todo_edit_text);
        Button addButton = findViewById(R.id.todo_add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = editText.getText().toString();
                if (!item.isEmpty()) {
                    todoList.add(item);
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                    saveTodoList();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveTodoList();
    }

    private void saveTodoList() {
        JSONArray array = new JSONArray(todoList);
        prefs.edit().putString(TODO_KEY, array.toString()).apply();
    }

    private ArrayList<String> loadTodoList() {
        String json = prefs.getString(TODO_KEY, null);
        ArrayList<String> list = new ArrayList<>();
        if (json != null) {
            try {
                JSONArray array = new JSONArray(json);
                for (int i = 0; i < array.length(); i++) {
                    list.add(array.getString(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
