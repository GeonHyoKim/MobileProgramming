package com.example.finalproject003;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SearchEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button baseballButton;
    private Button updownButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseballButton = findViewById(R.id.baseball_button);
        updownButton = findViewById(R.id.updown_button);

        baseballButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent baseballIntent = new Intent(MainActivity.this, baseball.class);
                startActivity(baseballIntent);
            }
        });

        updownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updownIntent = new Intent(MainActivity.this, updown.class);
                startActivity(updownIntent);
            }
        });

        Button bugReportButton = findViewById(R.id.bug_button);
        bugReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.police.go.kr");
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, "112");
                startActivity(intent);

            }
        });



    }
}
