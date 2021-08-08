package com.example.p14musicstream;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SongCollection mySongCollection = new SongCollection();
    SQLDB peopleDB;

    Button btnAddData, btnViewData;
    EditText etEmail, etPass, etpassconfirm;
    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;

    //call activity page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_login);

        Name = (EditText) findViewById(R.id.edtEmail);
        Password = (EditText) findViewById(R.id.edtPass);
        Info = (TextView) findViewById(R.id.tvinfo);
        Login = (Button) findViewById(R.id.SignIn);
        Info.setText("No of attempts remaining: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });
    }

    //myView represents the item that has been clicked on
    public void handleSelection(View myView) {
        String resourceId = getResources().getResourceEntryName(myView.getId());
        int currentArrayIndex = mySongCollection.searchSongById(resourceId);
        Log.d("COMT", "The id of the clicked item is " + resourceId);
        sendDataToActivity(currentArrayIndex);
    }

    public void sendDataToActivity(int index) {
        Intent nextPage = new Intent(this, PlaySongActivity.class);
        nextPage.putExtra("index", index);
        startActivity(nextPage);
    }

    public void goToSignIn(View myView) {
        setContentView(R.layout.activity_login);
        //Intent nextPage = new Intent(this,MainActivity.class);
        //startActivity(nextPage);
    }
    public void playlist(View myView) {
        Intent intent = new Intent(this, Playlist.class);
        startActivity(intent);
    }

    //code to sign in
    private void validate(String Name, String Password) {
        if ((Name.equals("Axel")) && (Password.equals("1234"))) {
            setContentView(R.layout.activity_main);
        } else {
            counter--;
            Toast.makeText(MainActivity.this, "Wrong password", Toast.LENGTH_LONG).show();
            Info.setText("No. of attempts remaining: " + String.valueOf(counter));
            if (counter == 0) {
                Login.setEnabled(false);
            }
        }
    }


    //Code to show the activity and the buttons
    public void GotoSignup(View myView) {
        setContentView(R.layout.activity_sign_up);

        peopleDB = new SQLDB(this);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass1);
        btnAddData = (Button) findViewById(R.id.btnAddData);
        btnViewData = (Button) findViewById(R.id.SignUp);
        etpassconfirm = (EditText) findViewById(R.id.etPass2);
        AddData();
    }

    //code to add the infomation of the email,password and ensure that the password match
    public void AddData() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String pass = etPass.getText().toString();
                String passconfirm = etpassconfirm.getText().toString();

                boolean insertData = peopleDB.addData(email, pass);
                if (passconfirm.equals(pass)) {
                    if (insertData == true) {
                        Toast.makeText(MainActivity.this, "Successfully Signed Up!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Error :( ", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Wrong Password :( ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}































