package com.example.thegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.thegame.lobby.LobbyDriver;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class LobbyOptionsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby_options_list);
        final Intent intent = new Intent(this, MainActivity.class);

        final LobbyDriver lobby = new LobbyDriver(LobbyCreateActivity.lobbyName);

        Button addOption = findViewById(R.id.options_add_button);
        addOption.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText optionsAdder = findViewById(R.id.option_edit);
                lobby.add(optionsAdder.getText().toString());
                optionsAdder.setText("");
            }
        });

        Button done = findViewById(R.id.options_done_button);
        done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // HTTP request
                try {
                    URL url = new URL("http://localhost:8080/lobby/create");
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    con.setRequestProperty("Content-Type", "application/json");
                    con.setDoOutput(true);
                    con.setRequestMethod("POST");

                    JSONArray options = new JSONArray();

                    for (int i = 0; i < lobby.getOptionsSize(); ++i) {
                        options.put(lobby.getAtIndex(i));
                    }

                    JSONObject json = new JSONObject();
                    json.put("lobbyName", lobby.getLobbyName());
                    json.put("options", options.toString());

                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                    wr.write(json.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Go to main menu
                startActivity(intent);
            }
        });
    }
}
