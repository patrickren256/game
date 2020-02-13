package com.example.game.Controller;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.Model.Helper.DatabaseHelper;
import com.example.game.R;

public class SignUpActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        dbHelper = new DatabaseHelper(this);
    }

    public void logIn(View view) {
        startActivity(new Intent(this, LogInActivity.class));
    }

    public void submit(View view) {
        Intent intent = new Intent(this, LogInActivity.class);

        EditText userName = findViewById(R.id.editText);
        String user = userName.getText().toString();
        Cursor data = dbHelper.getData();
        boolean userNameTaken = false;
        while (data.moveToNext()) {
            if (data.getString(1).equals(user)) {
                userNameTaken = true;
                break;
            }
        }
        if (userNameTaken) {
            toastMessage("User name is taken.");
        } else {
            boolean userAdded = dbHelper.addUser(user);
            if (userAdded) {
                toastMessage("Sign up completed.");
                startActivity(intent);
            } else {
                toastMessage("Sign up failed.");
            }
        }
    }

    public void delete(View view) {
        dbHelper.dropTable();
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
