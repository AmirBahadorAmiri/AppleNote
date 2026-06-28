package com.amirbahadoramiri.applenotebook.views.activities.main;

import android.content.Context;
import android.widget.Toast;

import com.amirbahadoramiri.applenotebook.R;
import com.amirbahadoramiri.applenotebook.models.Note;
import com.amirbahadoramiri.applenotebook.tools.roomdb.NoteDao;
import com.github.amirbahadoramiri.telegramdialog.library.TeleDirection;
import com.github.amirbahadoramiri.telegramdialog.two.TeleDialogDouble;
import com.github.amirbahadoramiri.telegramdialog.two.TeleDialogDoubleListener;

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
    public void onDeleteAllNote(Context context, int count) {
        if (count == 0) {
            mainView.showToast("لیست نوت ها خالی میباشد", Toast.LENGTH_SHORT);
        } else {

            TeleDialogDouble teleDialogDouble = new TeleDialogDouble(context)
                    .setDirection(TeleDirection.RTL)
                    .setTitle("هشدار")
                    .setMessage("آیا از حذف همه یادداشت ها اطمینان دارید؟")
                    .setButtonOneText("بله")
                    .setButtonOneTextColor(R.color.ios_like_red)
                    .setButtonOneRippleColor(R.color.ios_like_red_tint)
                    .setButtonTwoText("خیر")
                    .setButtonTwoTextColor(R.color.ios_like_blue)
                    .setButtonTwoRippleColor(R.color.ios_like_blue_tint);

            teleDialogDouble.setOnClickListener(new TeleDialogDoubleListener() {
                @Override
                public void onFirstButtonClicked() {
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
                    teleDialogDouble.dismiss();
                }

                @Override
                public void onSecondButtonClicked() {
                    teleDialogDouble.dismiss();
                }
            });

            teleDialogDouble.show();
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
