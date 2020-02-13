package com.example.game.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.game.R;
import com.example.game.Model.Helper.DatabaseHelper;

public class EditNameActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_edit_name);
    }

    public void submit(View view) {
        EditText userName = findViewById(R.id.editText);
        String oldUsername = getIntent().getExtras().getString("oldUsername");
        String newUsername = userName.getText().toString();
        int numUpdated = dbHelper.updateUserName(oldUsername, newUsername);
        if (numUpdated == 1) {
            toastMessage("Username updated successfully.");
        } else {
            Log.d("Database Query Error", "No. of fields updated: " + numUpdated);
        }
        startActivity(new Intent(EditNameActivity.this, LogInActivity.class));
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
