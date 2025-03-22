package com.example.ligago;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class AdminDashboardActivity extends AppCompatActivity {

    private Button createTournamentButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        createTournamentButton = findViewById(R.id.createTournamentButton);

        createTournamentButton.setOnClickListener(view -> {
            Intent intent = new Intent(AdminDashboardActivity.this, TournamentCreationActivity.class);
            startActivity(intent);
        });
    }
}
