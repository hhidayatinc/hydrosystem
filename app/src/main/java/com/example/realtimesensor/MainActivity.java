package com.example.realtimesensor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView value_ph, value_nutrisi,value_suhu, date, time;
    private Firebase mRefSuhu;
    private Firebase mRefNutrisi;
    private Firebase mRefpH;
    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy");
    String currentDateString = df.format(c.getTime());
    SimpleDateFormat tf = new SimpleDateFormat("kk:mm");
    String currentTimeString = tf.format(c.getTime());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        date = (TextView)findViewById(R.id.date);
        date.setText(currentDateString);

        time = (TextView)findViewById(R.id.time);
        time.setText(currentTimeString);

        value_ph = (TextView)findViewById(R.id.value_ph);
        value_nutrisi = (TextView)findViewById(R.id.value_nutrisi);
        value_suhu = (TextView)findViewById(R.id.value_suhu);

        mRefSuhu = new Firebase("https://hidroponik-3865f-default-rtdb.firebaseio.com/ESP32_Device/Suhu/Data");
        mRefNutrisi = new Firebase("https://hidroponik-3865f-default-rtdb.firebaseio.com/ESP32_Device/Kepekatan/Data");
        mRefpH = new Firebase("https://hidroponik-3865f-default-rtdb.firebaseio.com/ESP32_Device/pH/Data");

        mRefSuhu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String suhu = dataSnapshot.getValue(String.class);
                value_suhu.setText(suhu);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mRefNutrisi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nutrisi = dataSnapshot.getValue(String.class);
                value_nutrisi.setText(nutrisi);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mRefpH.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String ph = dataSnapshot.getValue(String.class);
                value_ph.setText(ph);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}