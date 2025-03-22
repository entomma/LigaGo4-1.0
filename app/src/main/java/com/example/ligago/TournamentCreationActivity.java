package com.example.ligago;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TournamentCreationActivity extends AppCompatActivity {

    EditText etNumTeams;
    Button btnGenerateTournament;
    LinearLayout teamInputContainer;
    TextView tvTournamentBracket;
    List<EditText> teamInputs = new ArrayList<>();
    ArrayList<String> tournamentTeams = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_creation);

        etNumTeams = findViewById(R.id.etNumTeams);
        btnGenerateTournament = findViewById(R.id.btnGenerateTournament);
        teamInputContainer = findViewById(R.id.teamInputContainer);
        tvTournamentBracket = findViewById(R.id.tvTournamentBracket);

        etNumTeams.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) generateTeamInputs();
        });

        btnGenerateTournament.setOnClickListener(view -> generateTournament());
    }

    private void generateTeamInputs() {
        teamInputContainer.removeAllViews();
        teamInputs.clear();

        String input = etNumTeams.getText().toString().trim();
        if (input.isEmpty()) return;

        int numTeams;
        try {
            numTeams = Integer.parseInt(input);
            if (numTeams < 2) {
                tvTournamentBracket.setText("At least 2 teams are required!");
                return;
            }
        } catch (NumberFormatException e) {
            tvTournamentBracket.setText("Invalid number of teams!");
            return;
        }

        for (int i = 0; i < numTeams; i++) {
            EditText teamInput = new EditText(this);
            teamInput.setHint("Enter Team " + (i + 1));
            teamInputContainer.addView(teamInput);
            teamInputs.add(teamInput);
        }

        Log.d("TournamentDebug", "Total input fields created: " + teamInputs.size());

    }


    private void generateTournament() {
        tournamentTeams.clear(); // Clear previous list

        for (EditText input : teamInputs) {
            String teamName = input.getText().toString().trim();
            if (!teamName.isEmpty()) {
                tournamentTeams.add(teamName);
            }
        }

        Log.d("TournamentDebug", "Total Teams Added: " + tournamentTeams.size());

        if (tournamentTeams.size() < 2) {
            tvTournamentBracket.setText("At least 2 teams are required!");
            return;
        }

        Collections.shuffle(tournamentTeams);

        StringBuilder bracket = new StringBuilder("Tournament Bracket:\n\n");
        for (int i = 0; i < tournamentTeams.size(); i += 2) {
            if (i + 1 < tournamentTeams.size()) {
                bracket.append(tournamentTeams.get(i)).append(" vs ").append(tournamentTeams.get(i + 1)).append("\n");
            } else {
                bracket.append(tournamentTeams.get(i)).append(" gets a bye\n");
            }
        }

        tvTournamentBracket.setText(bracket.toString());

        // Save to SharedPreferences
        saveTournamentData();
    }


    private void saveTournamentData() {
        getSharedPreferences("TournamentData", MODE_PRIVATE)
                .edit()
                .putString("tournament_bracket", tvTournamentBracket.getText().toString())
                .apply();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
