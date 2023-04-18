package com.example.firsttodoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TODO_INDEX = "currentTodo";
    private TextView todoTextView;
    private String[] mTodos;

    private Button nextButton;
    private Button prevButton;

    private int currentTodo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoTextView = findViewById(R.id.textViewTodo);
        mTodos = getResources().getStringArray(R.array.todos);
        nextButton = findViewById(R.id.buttonNext);
        prevButton = findViewById(R.id.buttonPrev);

        if (savedInstanceState != null){
            currentTodo = savedInstanceState.getInt(TODO_INDEX, 0);
        }

        todoTextView.setText(mTodos[currentTodo]);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTodo = ++currentTodo % mTodos.length;
                todoTextView.setText(mTodos[currentTodo]);
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentTodo == 0) {
                    currentTodo = mTodos.length - 1;
                } else {
                    currentTodo = --currentTodo % mTodos.length;
                }
                todoTextView.setText(mTodos[currentTodo]);
            }
        });
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(TODO_INDEX, currentTodo);
        // call superclass to save any view hierarchy
        super.onSaveInstanceState(savedInstanceState);
    }
}