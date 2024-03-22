package com.example.app;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private EditText ipEditText;
    private Button connectButton;
    private RemoteMouseClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipEditText = findViewById(R.id.ipEditText);
        connectButton = findViewById(R.id.connectButton);

        client = new RemoteMouseClient();

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ipAddress = ipEditText.getText().toString();
                if (!ipAddress.isEmpty()) {
                    new ConnectTask().execute(ipAddress);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter IP address", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class ConnectTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            String ipAddress = params[0];
            try {
                client.connect(ipAddress, 12345); // Assuming port is 12345
            } catch (IOException e) {
                e.printStackTrace();
                // Handle connection failure
            }
            return null;
        }
    }
}
