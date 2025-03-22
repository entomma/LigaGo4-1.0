package com.example.ligago;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Teams extends Fragment {

    TextView tvStoredTournament;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teams, container, false);

        // Make sure this ID exists in fragment_teams.xml
        TextView tvStoredTournament = view.findViewById(R.id.tvStoredTournament);

        if (tvStoredTournament == null) {
            Log.e("TournamentDebug", "tvStoredTournament is NULL. Check fragment_teams.xml for missing ID.");
            return view;
        }

        SharedPreferences prefs = requireActivity().getSharedPreferences("TournamentData", Context.MODE_PRIVATE);
        String tournamentData = prefs.getString("tournament_bracket", "No tournament data available.");

        Log.d("TournamentDebug", "Loaded Tournament Data: " + tournamentData);

        tvStoredTournament.setText(tournamentData);

        return view;
    }


    private void loadTournamentData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("TournamentData", Context.MODE_PRIVATE);
        String tournamentBracket = sharedPreferences.getString("tournament_bracket", "No Tournament Created");

        tvStoredTournament.setText(tournamentBracket);
    }
}
