package com.amirbahadoramiri.applenotebook.views.activities.about;

public class AboutActivityPresenter implements AboutContract.AboutPresenter {

    AboutContract.AboutView aboutView;

    @Override
    public void onAttach(AboutContract.AboutView view) {
        this.aboutView = view;
    }

    @Override
    public void onDetach() {
        if (aboutView != null) aboutView = null;
    }

    @Override
    public void githubClick() {
        aboutView.openGithub();
    }

    @Override
    public void telegramClick() {
        aboutView.openTelegram();
    }

    @Override
    public void gmailClick() {
        aboutView.openGmail();
    }
}
