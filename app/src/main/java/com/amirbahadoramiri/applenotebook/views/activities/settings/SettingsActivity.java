package com.amirbahadoramiri.applenotebook.views.activities.settings;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.amirbahadoramiri.applenotebook.R;
import com.amirbahadoramiri.applenotebook.tools.sharedhelper.SharedHelper;
import com.amirbahadoramiri.applenotebook.views.bases.BaseActivity;

public class SettingsActivity extends BaseActivity implements SettingsContract.SettingsView {

    SettingsActivityPresenter settingsActivityPresenter;
    AppCompatImageView bg0, bg1, bg2, bg3, bg4, activity_settings_backicon;
    SharedHelper sharedHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_settings);
        setViewCompat();
        findViews();
        setupViews();
        settingsActivityPresenter = new SettingsActivityPresenter();
        settingsActivityPresenter.onAttach(this);
    }

    private void findViews() {
        activity_settings_backicon = findViewById(R.id.activity_settings_backicon);
        bg0 = findViewById(R.id.bg0);
        bg1 = findViewById(R.id.bg1);
        bg2 = findViewById(R.id.bg2);
        bg3 = findViewById(R.id.bg3);
        bg4 = findViewById(R.id.bg4);
    }

    private void setupViews() {

        activity_settings_backicon.setOnClickListener(v->{
            finish();
        });

        sharedHelper = SharedHelper.getInstance(this);

        bg0.setOnClickListener(v -> {
            sharedHelper.insert("bg", 0);
            Toast.makeText(this, getString(R.string.choose_background_message), Toast.LENGTH_SHORT).show();
        });

        bg1.setOnClickListener(v -> {
            sharedHelper.insert("bg", 1);
            Toast.makeText(this, getString(R.string.choose_background_message), Toast.LENGTH_SHORT).show();
        });

        bg2.setOnClickListener(v -> {
            sharedHelper.insert("bg", 2);
            Toast.makeText(this, getString(R.string.choose_background_message), Toast.LENGTH_SHORT).show();
        });

        bg3.setOnClickListener(v -> {
            sharedHelper.insert("bg", 3);
            Toast.makeText(this, getString(R.string.choose_background_message), Toast.LENGTH_SHORT).show();
        });

        bg4.setOnClickListener(v -> {
            sharedHelper.insert("bg", 4);
            Toast.makeText(this, getString(R.string.choose_background_message), Toast.LENGTH_SHORT).show();
        });

    }

}
