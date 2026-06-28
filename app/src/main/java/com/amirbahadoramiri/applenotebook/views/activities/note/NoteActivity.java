package com.amirbahadoramiri.applenotebook.views.activities.note;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.amirbahadoramiri.applenotebook.R;
import com.amirbahadoramiri.applenotebook.models.Note;
import com.amirbahadoramiri.applenotebook.tools.logger.Logger;
import com.amirbahadoramiri.applenotebook.tools.roomdb.RoomDB;
import com.amirbahadoramiri.applenotebook.tools.sharedhelper.SharedHelper;
import com.amirbahadoramiri.applenotebook.tools.text.REPL;
import com.amirbahadoramiri.applenotebook.views.activities.main.MainActivity;
import com.amirbahadoramiri.applenotebook.views.activities.settings.SettingsActivity;
import com.amirbahadoramiri.applenotebook.views.bases.BaseActivity;
import com.civitasv.ioslike.dialog.DialogBottom;
import com.civitasv.ioslike.model.DialogTextStyle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class NoteActivity extends BaseActivity implements NoteContract.NoteView, TextWatcher {

    NoteActivityPresenter noteActivityPresenter;
    AppCompatEditText activity_note_titlebar, activity_note_textbar;
    AppCompatTextView activity_note_timeview, tv_blobs;
    AppCompatImageView iv_wifi, iv_battery;
    TextView tv_time, tv_battery;
    ConstraintLayout main;
    SharedHelper sharedHelper;

    public final int OPEN_SETTINGS = 1004;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_note);
        setViewCompat();
        findViews();
        setupViews();
        noteActivityPresenter = new NoteActivityPresenter(RoomDB.getRoomDB(this).getNoteDao(), getIntent().getParcelableExtra(MainActivity.EXTRA_KEY_NOTE));
        noteActivityPresenter.onAttach(this);
    }

    private void findViews() {
        main = findViewById(R.id.main);
        activity_note_titlebar = findViewById(R.id.activity_note_titlebar);
        activity_note_textbar = findViewById(R.id.activity_note_textbar);
        activity_note_timeview = findViewById(R.id.activity_note_timeview);
        tv_time = findViewById(R.id.tv_time);
        tv_battery = findViewById(R.id.tv_battery);
        tv_blobs = findViewById(R.id.tv_blobs);
        iv_wifi = findViewById(R.id.iv_wifi);
        iv_battery = findViewById(R.id.iv_battery);
    }

    private void setupViews() {

        sharedHelper = SharedHelper.getInstance(this);

        loadSettings();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        tv_time.setText(REPL.replaceWithPersianNumber(timeFormat.format(calendar.getTime())));

//        Intent intent = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
//        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
//        tv_battery.setText(REPL.replaceWithPersianNumber(String.valueOf(level)) + "%");

        activity_note_titlebar.addTextChangedListener(this);
        activity_note_textbar.addTextChangedListener(this);

        findViewById(R.id.activity_note_backicon).setOnClickListener(v -> noteActivityPresenter.onBackClick());
        findViewById(R.id.activity_note_save_btn).setOnClickListener(v -> noteActivityPresenter.onSaveClick(getTitleEditText(), getTextEditText()));

        findViewById(R.id.activity_note_shareicon).setOnClickListener(v -> {
            noteActivityPresenter.onShareTextClick();
        });
        findViewById(R.id.activity_note_menuicon).setOnClickListener(v -> {
            noteActivityPresenter.onMenuClick();
        });
    }

    public String getTextEditText() {
        return Objects.requireNonNull(activity_note_textbar.getText()).toString();
    }

    public String getTitleEditText() {
        return Objects.requireNonNull(activity_note_titlebar.getText()).toString();
    }

    public String getNoteTime() {
        return Objects.requireNonNull(activity_note_timeview.getText()).toString();
    }

    @Override
    public void showNote(Note note) {
        activity_note_titlebar.setText(note.getTitle());
        activity_note_textbar.setText(note.getNote());
        activity_note_timeview.setText(new Date(note.getDate()).toString());
    }

    @Override
    public void saveNote(int result_code, Note new_note) {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.EXTRA_KEY_NOTE, new_note);
        setResult(result_code, intent);
        finish();
    }

    @Override
    public void deleteNote(Note delete_note) {
        Logger.logd("Note Activity , delete_note id: " + delete_note.getId());
        Intent intent = new Intent();
        intent.putExtra(MainActivity.EXTRA_KEY_NOTE, delete_note);
        setResult(MainActivity.RESULT_CODE_DELETE_NOTE, intent);
        finish();
    }

    @Override
    public void showToast(String s, int length) {
        Toast.makeText(this, s, length).show();
    }

    @Override
    public void onFinishActivity() {
        finish();
    }

    @Override
    public void openMenu() {
        DialogBottom dialogBottom = new DialogBottom(this);
        dialogBottom.setTitle("گزینه ها")
                .setCancel("لغو", true)
                .setCancelStyle(new DialogTextStyle.Builder(this).color(R.color.black).typeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD))
                        .build())
                .addBottomItem("ذخیره", v2 -> {
                    noteActivityPresenter.onSaveClick(getTitleEditText(), getTextEditText());
                    dialogBottom.dismiss();
                }, new DialogTextStyle.Builder(this).color(R.color.ios_like_green).textSize(20).typeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD))
                        .build())
                .addBottomItem("حذف یادداشت", v2 -> {
                    noteActivityPresenter.onDeleteCLick(this);
                    dialogBottom.dismiss();
                }, new DialogTextStyle.Builder(this).color(R.color.ios_like_red).textSize(20).typeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD))
                        .build())
                .addBottomItem("اشتراک گذاری", v2 -> {
                    noteActivityPresenter.onShareTextClick();
                    dialogBottom.dismiss();
                }, new DialogTextStyle.Builder(this).color(R.color.black).textSize(20).typeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD))
                        .build())
                .addBottomItem("تنظیمات", v2 -> {
                    noteActivityPresenter.openSettingsClick();
                    dialogBottom.dismiss();
                }, new DialogTextStyle.Builder(this).color(R.color.black).textSize(20).typeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD))
                        .build())
                .setCanceledOnTouchOutside(true)
                .show();
    }

    @Override
    public void shareText() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, getTitleEditText());
        intent.putExtra(Intent.EXTRA_TEXT, getTextEditText() + "\n\n" + getNoteTime());
        startActivity(intent);
    }

    @Override
    public void openSettings() {
        startActivityForResult(new Intent(this, SettingsActivity.class), OPEN_SETTINGS);
    }

    @Override
    public void loadSettings() {
        int bg = sharedHelper.readInt("bg");
        if (bg == 0) {
            main.setBackgroundResource(R.drawable.bg0);
        }
        if (bg == 1) {
            main.setBackgroundResource(R.drawable.bg1);
        }
        if (bg == 2) {
            main.setBackgroundResource(R.drawable.bg2);
        }
        if (bg == 3) {
            main.setBackgroundResource(R.drawable.bg3);
        }
        if (bg == 4) {
            main.setBackgroundResource(R.drawable.bg4);
        }

        if (bg == 0 | bg == 4) {
            tv_blobs.setTextColor(ContextCompat.getColor(this, R.color.black));
            tv_time.setTextColor(ContextCompat.getColor(this, R.color.black));
            tv_battery.setTextColor(ContextCompat.getColor(this, R.color.black));
            iv_wifi.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.black)));
            iv_battery.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.black)));
            activity_note_timeview.setTextColor(ContextCompat.getColor(this, R.color.ios_darkgray));
            activity_note_titlebar.setTextColor(ContextCompat.getColor(this, R.color.black));
            activity_note_textbar.setTextColor(ContextCompat.getColor(this, R.color.black));
        }
        if (bg == 1 | bg == 2 | bg == 3) {
            tv_blobs.setTextColor(ContextCompat.getColor(this, R.color.white));
            tv_time.setTextColor(ContextCompat.getColor(this, R.color.white));
            tv_battery.setTextColor(ContextCompat.getColor(this, R.color.white));
            iv_wifi.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white)));
            iv_battery.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white)));
            activity_note_timeview.setTextColor(ContextCompat.getColor(this, R.color.white));
            activity_note_titlebar.setTextColor(ContextCompat.getColor(this, R.color.white));
            activity_note_textbar.setTextColor(ContextCompat.getColor(this, R.color.white));
        }

        int fontSize = sharedHelper.readInt("textSize");
        if (fontSize != -1) {
            activity_note_textbar.setTextSize(fontSize);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if ((Objects.requireNonNull(activity_note_titlebar.getText()).toString().isEmpty() || Objects.requireNonNull(activity_note_textbar.getText()).toString().isEmpty())) {
            ((AppCompatTextView) findViewById(R.id.activity_note_save_btn)).setTextColor(ContextCompat.getColor(this, R.color.ios_gray));
        } else {
            ((AppCompatTextView) findViewById(R.id.activity_note_save_btn)).setTextColor(ContextCompat.getColor(this, R.color.ios_gold));
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OPEN_SETTINGS) {
            loadSettings();
        }
    }
}
