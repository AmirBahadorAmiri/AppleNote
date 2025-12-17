package com.amirbahadoramiri.applenotebook.views.activities.about;

import com.amirbahadoramiri.applenotebook.views.bases.BaseContract;

public interface AboutContract {

    interface AboutView extends BaseContract.BaseView {
        void openGithub();
        void openMyket();
        void openGmail();
    }

    interface AboutPresenter extends BaseContract.BasePresenter<AboutView> {

        @Override
        void onAttach(AboutView view);

        @Override
        void onDetach();

        void githubClick();
        void myketClick();
        void gmailClick();

    }

}
