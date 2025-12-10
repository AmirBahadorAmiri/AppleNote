package com.amirbahadoramiri.applenotebook.views.activities.settings;

public class SettingsActivityPresenter implements SettingsContract.SettingsPresenter {

    SettingsContract.SettingsView settingsView;

    @Override
    public void onAttach(SettingsContract.SettingsView view) {
        this.settingsView = view;
    }

    @Override
    public void onDetach() {
        if (settingsView != null) settingsView = null;
    }
}
