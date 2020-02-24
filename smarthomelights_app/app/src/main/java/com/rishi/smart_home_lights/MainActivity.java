package com.rishi.smart_home_lights;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button login;
    EditText uid,pwd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        setContentView(R.layout.activity_main);
        uid=findViewById(R.id.uid);
        pwd=findViewById(R.id.pwd);
        login=findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(uid.getText().toString(),pwd.getText().toString());
            }
        });


    }


    private void validate(String user,String pass)
    {
        if((user.equals("rishi"))&&(pass.equals("1234")))
        {
            Intent intent=new Intent(MainActivity.this,SecondActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Enter valid UserId and password",Toast.LENGTH_LONG).show();
        }
    }





}
