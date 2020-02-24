package com.rishi.smart_home_lights;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SecondActivity extends AppCompatActivity {
    private String TAG;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final Switch light1switchbtn = findViewById(R.id.light1);
        final Switch manualswitchbtn = findViewById(R.id.manual);

        final TextView count_val = findViewById(R.id.count);
        final TextView pir1_val = findViewById(R.id.pir_1);
        final TextView pir2_val = findViewById(R.id.pir_2);

        final Button reset =findViewById(R.id.reset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();

                DatabaseReference reset = database.getReference("reset_mode");
                reset.setValue(1);

            }
        });



        light1switchbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonview, boolean isChecked) {
                if (isChecked == true) {
                    light1_on_fn();
                } else {
                    light1_off_fn();
                }
            }

        });



        manualswitchbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonview, boolean isChecked) {
                if (isChecked == true) {
                    manual_on_fn();
                } else {
                    manual_off_fn();
                }
            }

        });






        //Light1 Listener
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference light1_status = database.getReference("light_1");


        light1_status.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Integer value = dataSnapshot.getValue(Integer.class);

                if (value != null) {
                    if (value == 1) {
                        light1switchbtn.setChecked(true);

                    } else {
                        light1switchbtn.setChecked(false);

                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });






        // Manual Mode Listener

        DatabaseReference manual_status = database.getReference("manual_mode");


        manual_status.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Integer value = dataSnapshot.getValue(Integer.class);

                if (value != null) {
                    if (value == 1) {
                        manualswitchbtn.setChecked(true);

                    } else {
                        manualswitchbtn.setChecked(false);

                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });




        // Count Values Listener
        DatabaseReference count_status = database.getReference("count");
        count_status.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Integer value = dataSnapshot.getValue(Integer.class);

                if (value != null) {

                    count_val.setText(String.valueOf(value));

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        // PIR 1 listener

        DatabaseReference pir1_status = database.getReference("pir_1");
        pir1_status.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Integer value = dataSnapshot.getValue(Integer.class);

                if (value != null) {

                    pir1_val.setText(String.valueOf(value));

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });



        //PIR 2 listener
        DatabaseReference pir2_status = database.getReference("pir_2");
        pir2_status.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Integer value = dataSnapshot.getValue(Integer.class);

                if (value != null) {

                    pir2_val.setText(String.valueOf(value));

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });



    }


    // On - Off functions

    private void light1_on_fn()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("light_1");

        myRef.setValue(1);

    }

    private void light1_off_fn()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("light_1");

        myRef.setValue(0);

    }



    private void manual_on_fn()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("manual_mode");

        myRef.setValue(1);

    }

    private void manual_off_fn()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("manual_mode");

        myRef.setValue(0);

    }



}
