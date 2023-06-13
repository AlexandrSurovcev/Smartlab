package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView text,title,skip;
ImageView board1,board2,board3, illustration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);
        title = findViewById(R.id.title);
        skip = findViewById(R.id.skip);
        board1 = findViewById(R.id.board1);
        board2 = findViewById(R.id.board2);
        board3 = findViewById(R.id.board3);
        board1.setImageResource(R.drawable.ellipse_1);
        illustration = findViewById(R.id.illustration);
    }
    public void onClick1(View view){
        title.setText(R.string.board1title);
        text.setText(R.string.board1text);
        skip.setText(R.string.skip);
        illustration.setImageResource(R.drawable.illustration);
        board1.setImageResource(R.drawable.ellipse_1);
        board2.setImageResource(R.drawable.ellipse_2);
        board3.setImageResource(R.drawable.ellipse_2);
    }
    public void onClick2(View view){
        title.setText(R.string.board2title);
        text.setText(R.string.board2text);
        skip.setText(R.string.skip);
        illustration.setImageResource(R.drawable.illustration2);
        board1.setImageResource(R.drawable.ellipse_2);
        board2.setImageResource(R.drawable.ellipse_1);
        board3.setImageResource(R.drawable.ellipse_2);
    }
    public void onClick3(View view){
        title.setText(R.string.board3title);
        text.setText(R.string.board3text);
        skip.setText(R.string.close);
        illustration.setImageResource(R.drawable.illustration3);
        board1.setImageResource(R.drawable.ellipse_2);
        board2.setImageResource(R.drawable.ellipse_2);
        board3.setImageResource(R.drawable.ellipse_1);
    }
    public void onClick4(View view){
        if(skip.getText().toString().contains("Завершить") ){
            Intent intent = new Intent(MainActivity.this,SignLogin.class);
            startActivity(intent);
        }
        else onClick3(view);
    }
}