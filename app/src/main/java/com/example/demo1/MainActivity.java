package com.example.demo1;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button btnEmail;
    private Button btnPhone;

    private EditText txtSquat;
    private EditText txtBench;
    private EditText txtDeadlift;
    private Button btnReportNumbers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEmail = findViewById(R.id.btnEmail);
        btnPhone = findViewById(R.id.btnPhone);

        txtSquat = findViewById(R.id.txtSquat);
        txtBench = findViewById(R.id.txtBench);
        txtDeadlift = findViewById(R.id.txtDeadlift);
        btnReportNumbers = findViewById(R.id.btnSendTextReport);

        // Send an email
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                // Add email addresses in the String array that you want to send to
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { btnEmail.getText().toString() });
                // Programatically set subject
                intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
                // Programatically set email body
                intent.putExtra(Intent.EXTRA_TEXT, "mail body");
                startActivity(Intent.createChooser(intent, ""));
            }
        });

        /****************************************************
         * MUST GIVE PERMISSIONS FOR SMS IN ANDROID MANIFEST*
         ****************************************************/
        // Sends a text message
        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add phone number of phone you want to send to
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + "15555555555"));
                // Set text message body
                intent.putExtra("sms_body", "Test");
                startActivity(intent);
            }
        });

        // Automate text message sending
        btnReportNumbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String squat = txtSquat.getText().toString();
                String bench = txtBench.getText().toString();
                String deadlift = txtDeadlift.getText().toString();

                // This builds message body
                String message = "Squat: " + squat + "\nBench: " + bench + "\nDeadlift: " + deadlift;

                // Add phone number of phone you want to send to and message body
                SmsManager.getDefault().sendTextMessage("+15555555555", null, message, null, null);
            }
        });
    }
}
