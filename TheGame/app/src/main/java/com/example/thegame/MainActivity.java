package com.example.thegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = new Intent(this, LobbyCreateActivity.class);

        Button createLobby = findViewById(R.id.create_lobby);
        createLobby.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: make connector to the second screen
                Snackbar.make(v, R.string.create_lobby, Snackbar.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}
