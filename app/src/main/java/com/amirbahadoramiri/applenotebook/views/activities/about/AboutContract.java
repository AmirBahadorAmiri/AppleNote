package com.amirbahadoramiri.applenotebook.views.activities.about;

import com.amirbahadoramiri.applenotebook.views.bases.BaseContract;

public interface AboutContract {

    interface AboutView extends BaseContract.BaseView {
        void openGithub();
        void openTelegram();
        void openGmail();
    }

    interface AboutPresenter extends BaseContract.BasePresenter<AboutView> {

        @Override
        void onAttach(AboutView view);

        @Override
        void onDetach();

        void githubClick();
        void telegramClick();
        void gmailClick();

    }

}
