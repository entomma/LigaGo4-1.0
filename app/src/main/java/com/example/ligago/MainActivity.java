package com.example.ligago;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import android.content.Intent;


import android.widget.ImageView;


import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar); // Get Toolbar
        setSupportActionBar(toolbar); // Set it as ActionBar

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Default fragment on app launch
        if (savedInstanceState == null) {
            setFragment(new HomeFragment(), "Home");
            navigationView.setCheckedItem(R.id.nav_home);
        }
        final int HOME_ID = R.id.nav_home;
        final int TEAMS_ID = R.id.nav_teams;
        final int BRACKETS_ID = R.id.nav_brackets;
        final int CREDITS_ID = R.id.nav_credits;
        navigationView.setNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            String title = "App Name"; // Default title

            if (item.getItemId() == R.id.nav_home) {
                selectedFragment = new HomeFragment();
                title = "Home";
            } else if (item.getItemId() == R.id.nav_teams) {
                selectedFragment = new Teams();
                title = "Teams";
            } else if (item.getItemId() == R.id.nav_brackets) {
                selectedFragment = new Brackets();
                title = "Brackets";
            } else if (item.getItemId() == R.id.nav_credits) {
                selectedFragment = new Credits();
                title = "Credits";
            }
            else if (item.getItemId() == R.id.nav_admin) {
                startActivity(new Intent(this, AdminLoginActivity.class));
            }


            if (selectedFragment != null) {
                setFragment(selectedFragment, title);
            }

            drawerLayout.closeDrawers();
            return true;

        });
    }
    private void setFragment(Fragment fragment, String title) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
        toolbar.setTitle(title); // Update Toolbar title
    }

}