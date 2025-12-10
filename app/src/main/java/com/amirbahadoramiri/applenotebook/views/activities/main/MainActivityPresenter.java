package com.amirbahadoramiri.applenotebook.views.activities.main;

import android.widget.Toast;

import com.amirbahadoramiri.applenotebook.models.Note;
import com.amirbahadoramiri.applenotebook.tools.roomdb.NoteDao;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivityPresenter implements MainContract.MainPresenter {

    MainContract.MainView mainView;
    public NoteDao noteDao;

    public MainActivityPresenter(NoteDao noteDao) {
        this.noteDao = noteDao;
        showAllNote();
    }

    @Override
    public void onAttach(MainContract.MainView view) {
        this.mainView = view;

    }

    @Override
    public void onDetach() {
        if (mainView != null) mainView = null;
    }

    @Override
    public void onDeleteAllNote(int count) {
        if (count == 0) {
            mainView.showToast("لیست نوت ها خالی میباشد", Toast.LENGTH_SHORT);
        } else {
            noteDao.deleteAll()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        }

                        @Override
                        public void onComplete() {
                            mainView.deleteAllNotes();
                            mainView.showToast("تمامی نوت ها پاک شدند", Toast.LENGTH_SHORT);
                        }

                        @Override
                        public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        }
                    });
        }
    }

    @Override
    public void onNewNoteClick() {
        this.mainView.openNewNoteView();
    }

    @Override
    public void onSearch(String s) {
        if (s.isEmpty()) {
            showAllNote();
        } else {
            noteDao.search(s)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }

                        @Override
                        public void onSuccess(@NonNull List<Note> notes) {
                            mainView.showNotes(notes);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                        }
                    });
        }
    }

    @Override
    public void showAllNote() {
        noteDao.selectAllNote()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onSuccess(@NonNull List<Note> notes) {
                        mainView.showNotes(notes);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                });
    }

    @Override
    public void openSettingsClick() {
        mainView.openSettings();
    }

    @Override
    public void openAboutClick() {
        mainView.openAbout();
    }

    @Override
    public void onMenuClick() {
        mainView.openMenu();
    }
}
