package com.amirbahadoramiri.applenotebook.views.activities.settings;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.amirbahadoramiri.applenotebook.R;
import com.amirbahadoramiri.applenotebook.tools.sharedhelper.SharedHelper;
import com.amirbahadoramiri.applenotebook.views.bases.BaseActivity;
import com.google.android.material.button.MaterialButton;

public class SettingsActivity extends BaseActivity implements SettingsContract.SettingsView {

    SettingsActivityPresenter settingsActivityPresenter;
    AppCompatImageView bg0, bg1, bg2, bg3, bg4, activity_settings_backicon;
    SharedHelper sharedHelper;
    MaterialButton minusButton, plusButton, textSizeButton;

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
        minusButton = findViewById(R.id.minusButton);
        plusButton = findViewById(R.id.plusButton);
        textSizeButton = findViewById(R.id.textSizeButton);
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

        int chachedTextSize = sharedHelper.readInt("textSize");
        if ( chachedTextSize != -1 ) {
            textSizeButton.setText(String.valueOf(chachedTextSize));
        }

        plusButton.setOnClickListener(v -> {
            int textSize = Integer.parseInt(textSizeButton.getText().toString());
            if ( textSize < 20 ) {
                textSizeButton.setText(String.valueOf(textSize + 2));
                sharedHelper.insert("textSize", textSize + 2);
            }
        });

        minusButton.setOnClickListener(v -> {
            int textSize = Integer.parseInt(textSizeButton.getText().toString());
            if (textSize > 12) {
                textSizeButton.setText(String.valueOf(textSize - 2));
                sharedHelper.insert("textSize", textSize - 2);
            }
        });

    }

}
