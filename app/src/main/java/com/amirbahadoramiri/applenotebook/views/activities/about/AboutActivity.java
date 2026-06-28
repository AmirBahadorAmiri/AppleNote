package com.amirbahadoramiri.applenotebook.views.activities.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.amirbahadoramiri.applenotebook.R;
import com.amirbahadoramiri.applenotebook.tools.packager.Packager;
import com.amirbahadoramiri.applenotebook.views.bases.BaseActivity;

public class AboutActivity extends BaseActivity implements AboutContract.AboutView {

    AboutActivityPresenter aboutActivityPresenter;
    AppCompatTextView activity_about_appversion;
    AppCompatImageView activity_about_telegram,activity_about_google,activity_about_github,
            activity_about_usericon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_about);
        setViewCompat();
        findViews();
        setupViews();
        aboutActivityPresenter = new AboutActivityPresenter();
        aboutActivityPresenter.onAttach(this);
    }

    private void findViews() {
        activity_about_appversion = findViewById(R.id.activity_about_appversion);
        activity_about_telegram = findViewById(R.id.activity_about_telegram);
        activity_about_google = findViewById(R.id.activity_about_google);
        activity_about_github = findViewById(R.id.activity_about_github);
        activity_about_usericon = findViewById(R.id.activity_about_usericon);
    }

    private void setupViews() {

        activity_about_appversion.setText(Packager.negareshApplication(this));
        activity_about_telegram.setOnClickListener(v -> aboutActivityPresenter.myketClick());
        activity_about_google.setOnClickListener(v -> aboutActivityPresenter.gmailClick());
        activity_about_github.setOnClickListener(v -> aboutActivityPresenter.appRepositoryClick());
        activity_about_usericon.setOnClickListener(v -> aboutActivityPresenter.userGithubClick());
    }

    @Override
    public void openAppRepository() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/AmirBahadorAmiri/AppleNote")));
    }

    @Override
    public void openMyket() {
        String url = "myket://comment?id=" + getPackageName();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public void openGmail() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"bahadoramiri.report@gmail.com"});
        startActivity(intent);
    }

    @Override
    public void openGithub() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/AmirBahadorAmiri")));
    }

}
