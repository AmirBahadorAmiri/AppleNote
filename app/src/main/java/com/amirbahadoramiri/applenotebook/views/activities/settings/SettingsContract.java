package com.amirbahadoramiri.applenotebook.views.activities.settings;

import com.amirbahadoramiri.applenotebook.views.bases.BaseContract;

public interface SettingsContract {

    interface SettingsView extends BaseContract.BaseView {

    }

    interface SettingsPresenter extends BaseContract.BasePresenter<SettingsView> {

        @Override
        void onAttach(SettingsView view);

        @Override
        void onDetach();
    }

}
