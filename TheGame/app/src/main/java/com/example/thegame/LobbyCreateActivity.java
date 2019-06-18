package com.example.thegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class LobbyCreateActivity extends AppCompatActivity {
    public static String lobbyName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby_create);

        final Intent intent = new Intent(this, LobbyOptionsList.class);

        EditText lobbyEditText = findViewById(R.id.lobby_name_edit);
        lobbyName = lobbyEditText.getText().toString();

        Button button = findViewById(R.id.lobby_name_button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}
