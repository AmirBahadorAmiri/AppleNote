package com.amirbahadoramiri.applenotebook.views.activities.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.amirbahadoramiri.applenotebook.R;
import com.amirbahadoramiri.applenotebook.tools.packager.Packager;
import com.amirbahadoramiri.applenotebook.views.bases.BaseActivity;

public class AboutActivity extends BaseActivity implements AboutContract.AboutView {

    AboutActivityPresenter aboutActivityPresenter;

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
    }

    private void setupViews() {
        ((AppCompatTextView) findViewById(R.id.activity_about_appversion)).setText(Packager.negareshApplication(this));
        findViewById(R.id.activity_about_telegram).setOnClickListener(v->{ aboutActivityPresenter.myketClick();});
        findViewById(R.id.activity_about_google).setOnClickListener(v->{ aboutActivityPresenter.gmailClick();});
        findViewById(R.id.activity_about_github).setOnClickListener(v->{ aboutActivityPresenter.githubClick();});
    }

    @Override
    public void openGithub() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/AmirBahadorAmiri/AppleNote")));
    }

    @Override
    public void openMyket() {
        String url= "myket://comment?id="+getPackageName();
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
}
