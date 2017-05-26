package com.aadityakumar.antisnoring;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

enum ServiceStatus {
    Stopped,
    Running
}

public class MainActivity extends AppCompatActivity {
    protected ServiceStatus serviceStatus = ServiceStatus.Stopped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(getFloatingActionButtonClickListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.action_about_us:
                break;

            case R.id.action_settings:
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                break;

            default:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener getFloatingActionButtonClickListener() {

        return (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

                if (serviceStatus == ServiceStatus.Running) {
                    serviceStatus = ServiceStatus.Stopped;

                    fab.setBackgroundTintList(
                            ColorStateList.valueOf(
                                    ResourcesCompat.getColor(
                                            getResources(), android.R.color.holo_red_dark, getTheme()
                                    )
                            )
                    );
                    fab.setImageResource(android.R.drawable.ic_media_pause);
                    showMessage("Anti Snoring is stopped!");
                } else {
                    serviceStatus = ServiceStatus.Running;

                    fab.setBackgroundTintList(
                            ColorStateList.valueOf(
                                    ResourcesCompat.getColor(
                                            getResources(), android.R.color.holo_green_dark, getTheme()
                                    )
                            )
                    );
                    fab.setImageResource(android.R.drawable.ic_media_play);
                    showMessage("Anti Snoring is running now...");
                }
            }
        });
    }

    private void showMessage(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG)
                .show();
    }
}
