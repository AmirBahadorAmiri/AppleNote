package com.amirbahadoramiri.applenotebook.views.activities.note;

import android.widget.Toast;

import com.amirbahadoramiri.applenotebook.models.Note;
import com.amirbahadoramiri.applenotebook.tools.logger.Logger;
import com.amirbahadoramiri.applenotebook.tools.roomdb.NoteDao;
import com.amirbahadoramiri.applenotebook.views.activities.main.MainActivity;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NoteActivityPresenter implements NoteContract.NotePresenter {

    NoteContract.NoteView noteView;
    NoteDao noteDao;
    Note old_note;

    public NoteActivityPresenter(NoteDao noteDao, Note new_note) {
        this.noteDao = noteDao;
        this.old_note = new_note;
    }

    @Override
    public void onAttach(NoteContract.NoteView view) {
        this.noteView = view;
        if (old_note != null) {
            noteView.showNote(old_note);
        } else {

        }
    }

    @Override
    public void onDetach() {
        if (noteView != null) noteView = null;
    }

    @Override
    public void onDeleteCLick() {
        if (old_note == null) {
            noteView.showToast("نوت ساخته نشده که دیلیت نمیشه :)", Toast.LENGTH_SHORT);
        } else {
            Logger.logd("note presenter ondelete , old_note id: " + old_note.getId());
            noteDao.delete(old_note)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }

                        @Override
                        public void onComplete() {
                            Logger.logd("note presenter onComplete , old_note id: " + old_note.getId());
                            noteView.deleteNote(old_note);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                        }
                    });
        }
    }

    @Override
    public void onBackClick() {
        this.noteView.onFinishActivity();
    }

    @Override
    public void onSaveClick(String title, String text) {
        if (title == null || title.isEmpty() || text == null || text.isEmpty()) {
            noteView.showToast("تیتر و متن نمیتونن خالی باشن :)", Toast.LENGTH_SHORT);
        } else {
            if (old_note == null) {
                Note new_note = new Note(title, text, System.currentTimeMillis());
                noteDao.insert(new_note)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                            }

                            @Override
                            public void onSuccess(@NonNull Long aLong) {
                                Logger.logd("note presenter onSuccess");
                                Logger.logd("first new_note id: " + new_note.getId());
                                new_note.setId(aLong);
                                noteView.saveNote(MainActivity.RESULT_CODE_ADD_NOTE, new_note);
                                Logger.logd("last new_note id: " + new_note.getId());
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Logger.logd("note presenter onError");
                                Logger.logd(e.getMessage());
                            }
                        });
            } else {
                old_note.setTitle(title);
                old_note.setNote(text);
                old_note.setDate(System.currentTimeMillis());
                noteDao.update(old_note)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                            }

                            @Override
                            public void onComplete() {
                                Logger.logd("note presenter old note onComplete");
                                noteView.saveNote(MainActivity.RESULT_CODE_UPDATE_NOTE, old_note);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Logger.logd("note presenter old note onError");
                                Logger.logd(e.getMessage());
                            }
                        });
            }
        }
    }

    @Override
    public void onShareTextClick() {
        noteView.shareText();
    }

    @Override
    public void onMenuClick() {
        noteView.openMenu();
    }
}
