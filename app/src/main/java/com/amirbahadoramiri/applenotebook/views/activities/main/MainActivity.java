package com.amirbahadoramiri.applenotebook.views.activities.main;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amirbahadoramiri.applenotebook.R;
import com.amirbahadoramiri.applenotebook.adapter.NoteAdapter;
import com.amirbahadoramiri.applenotebook.models.Note;
import com.amirbahadoramiri.applenotebook.tools.logger.Logger;
import com.amirbahadoramiri.applenotebook.tools.roomdb.RoomDB;
import com.amirbahadoramiri.applenotebook.views.activities.about.AboutActivity;
import com.amirbahadoramiri.applenotebook.views.activities.note.NoteActivity;
import com.amirbahadoramiri.applenotebook.views.activities.settings.SettingsActivity;
import com.amirbahadoramiri.applenotebook.views.bases.BaseActivity;
import com.civitasv.ioslike.dialog.DialogBottom;
import com.civitasv.ioslike.model.DialogTextStyle;

import java.util.List;
import java.util.Objects;

public class MainActivity extends BaseActivity implements MainContract.MainView {

    MainActivityPresenter mainActivityPresenter;
    RecyclerView activity_main_notes_recyclerview;
    NoteAdapter noteAdapter;

    public static final String EXTRA_KEY_NOTE = "NOTE_EXTRA";
    public static final int REQUEST_CODE = 999;
    public static final int RESULT_CODE_ADD_NOTE = 1001;
    public static final int RESULT_CODE_UPDATE_NOTE = 1002;
    public static final int RESULT_CODE_DELETE_NOTE = 1003;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_main);
        setViewCompat();
        findViews();
        setupViews();
        mainActivityPresenter = new MainActivityPresenter(RoomDB.getRoomDB(this).getNoteDao());
        mainActivityPresenter.onAttach(this);
    }

    private void findViews() {
        activity_main_notes_recyclerview = findViewById(R.id.activity_main_notes_recyclerview);
    }

    private void setupViews() {

        noteAdapter = new NoteAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        activity_main_notes_recyclerview.setLayoutManager(linearLayoutManager);
        activity_main_notes_recyclerview.setAdapter(noteAdapter);

        findViewById(R.id.activity_main_ios_menu).setOnClickListener(v -> mainActivityPresenter.onMenuClick());
        findViewById(R.id.activity_main_create_note).setOnClickListener(v -> mainActivityPresenter.onNewNoteClick());

        ((AppCompatEditText) findViewById(R.id.activity_main_searchbar)).addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                mainActivityPresenter.onSearch(Objects.requireNonNull(s.toString()));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
    }

    @Override
    public void showNotes(List<Note> notes) {
        noteAdapter.showNotes(notes);
    }

    @Override
    public void deleteAllNotes() {
        noteAdapter.deleteAllNotes();
    }

    @Override
    public void openExistNoteView(Note note) {
        Intent intent = new Intent(this, NoteActivity.class);
        intent.putExtra(EXTRA_KEY_NOTE, note);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void openNewNoteView() {
        Intent intent = new Intent(this, NoteActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void showToast(String s, int length) {
        Toast.makeText(this, s, length).show();
    }

    @Override
    public void scrollToPosition(int pos) {
        activity_main_notes_recyclerview.scrollToPosition(pos);
    }

    @Override
    public void openSettings() {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    @Override
    public void openAbout() {
        startActivity(new Intent(this, AboutActivity.class));
    }

    @Override
    public void openMenu() {
        DialogBottom dialogBottom = new DialogBottom(this);
        dialogBottom.setTitle("گزینه ها")
                .setCancel("لغو", true)
                .setCancelStyle(new DialogTextStyle.Builder(this).color(R.color.black).typeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD))
                        .build())
                .addBottomItem("حذف همه‌ی یادداشت ها", v2 -> {
                    mainActivityPresenter.onDeleteAllNote(noteAdapter.getItemCount());
                    dialogBottom.dismiss();
                }, new DialogTextStyle.Builder(this).color(R.color.ios_like_red).textSize(20).typeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD))
                        .build())
//                    .addBottomItem("Settings", v2 -> {
//                        mainActivityPresenter.openSettingsClick();
//                        dialogBottom.dismiss();
//                    }, new DialogTextStyle.Builder(this).color(R.color.black).textSize(20).typeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD))
//                            .build())
                .addBottomItem("درباره ما", v2 -> {
                    mainActivityPresenter.openAboutClick();
                    dialogBottom.dismiss();
                }, new DialogTextStyle.Builder(this).color(R.color.black).textSize(20).typeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD))
                        .build())
                .setCanceledOnTouchOutside(true)
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            Note result_note = Objects.requireNonNull(data).getParcelableExtra(EXTRA_KEY_NOTE);
            Logger.logd("MainActivity onActivityResult");
            Logger.logd("result_note id: " + Objects.requireNonNull(result_note).getId());
            if (resultCode == RESULT_CODE_ADD_NOTE) {
                noteAdapter.addNote(result_note);
            } else if (resultCode == RESULT_CODE_UPDATE_NOTE) {
                noteAdapter.updateNote(result_note);
            } else if (resultCode == RESULT_CODE_DELETE_NOTE) {
                noteAdapter.deleteNote(result_note);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainActivityPresenter.onDetach();
    }
}