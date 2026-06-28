package com.amirbahadoramiri.applenotebook.views.activities.about;

import com.amirbahadoramiri.applenotebook.views.bases.BaseContract;

public interface AboutContract {

    interface AboutView extends BaseContract.BaseView {
        void openAppRepository();
        void openMyket();
        void openGmail();
        void openGithub();
    }

    interface AboutPresenter extends BaseContract.BasePresenter<AboutView> {

        @Override void onAttach(AboutView view);
        @Override void onDetach();

        void appRepositoryClick();
        void myketClick();
        void gmailClick();
        void userGithubClick();

    }

}
