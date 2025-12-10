package com.amirbahadoramiri.applenotebook.views.activities.settings;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.amirbahadoramiri.applenotebook.R;
import com.amirbahadoramiri.applenotebook.views.bases.BaseActivity;

public class SettingsActivity extends BaseActivity implements SettingsContract.SettingsView {

    SettingsActivityPresenter settingsActivityPresenter;

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
    }

    private void setupViews() {
    }

}
