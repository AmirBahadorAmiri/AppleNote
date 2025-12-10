package com.amirbahadoramiri.applenotebook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.amirbahadoramiri.applenotebook.R;
import com.amirbahadoramiri.applenotebook.models.Note;
import com.amirbahadoramiri.applenotebook.tools.logger.Logger;
import com.amirbahadoramiri.applenotebook.views.activities.main.MainContract;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    public List<Note> noteList = new ArrayList<>();
    public MainContract.MainView mainView;

    public NoteAdapter(MainContract.MainView mainView) {
        this.mainView = mainView;
    }

    public void addNotes(List<Note> notes) { /* Not implemented */}

    public void updateNote(Note update_note) {
        Logger.logd("note adapter updateNote id: " + update_note.getId());
        for (int pos = 0, size = noteList.size(); pos < size; pos++) {
            if (noteList.get(pos).getId().equals(update_note.getId())) {
                int finalPos = pos;
                Observable.timer(500, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<>() {
                            @Override
                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                            }

                            @Override
                            public void onNext(@io.reactivex.rxjava3.annotations.NonNull Long aLong) {
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                            }

                            @Override
                            public void onComplete() {
                                noteList.remove(finalPos);
                                notifyItemRemoved(finalPos);
                                Observable.timer(500, TimeUnit.MILLISECONDS)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Observer<>() {
                                            @Override
                                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                                            }

                                            @Override
                                            public void onNext(@io.reactivex.rxjava3.annotations.NonNull Long aLong) {
                                            }

                                            @Override
                                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                            }

                                            @Override
                                            public void onComplete() {
                                                noteList.add(finalPos, update_note);
                                                notifyItemInserted(finalPos);
                                            }
                                        });
                            }
                        });
            }
        }
    }

    public void deleteNote(Note deleted_note) {
        Logger.logd("note adapter deleteNote id: " + deleted_note.getId());
        for (int pos = 0, size = noteList.size(); pos < size; pos++) {
            if (noteList.get(pos).getId().equals(deleted_note.getId())) {
                int finalPos = pos;
                Observable.timer(500, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<>() {
                            @Override
                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                            }

                            @Override
                            public void onNext(@io.reactivex.rxjava3.annotations.NonNull Long aLong) {
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                            }

                            @Override
                            public void onComplete() {
                                noteList.remove(finalPos);
                                notifyItemRemoved(finalPos);
                            }
                        });
            }
        }
    }

    public void addNote(Note new_note) {
        Logger.logd("adapter add note");
        Logger.logd("adapter note id: " + new_note.getId());
        Observable.timer(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull Long aLong) {
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        noteList.add(new_note);
                        notifyItemInserted(noteList.size() - 1);
                        mainView.scrollToPosition(noteList.size() - 1);
                    }
                });
    }

    public void deleteAllNotes() {
        notifyItemRangeRemoved(0, noteList.size());
        noteList.clear();
    }

    public void showNotes(List<Note> notes) {
        notifyItemRangeRemoved(0, noteList.size());
        noteList.clear();
        noteList.addAll(notes);
        notifyItemRangeInserted(0, noteList.size());
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        holder.bindViews(holder);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class NoteHolder extends RecyclerView.ViewHolder {

        AppCompatTextView item_note_title, item_note_text, item_note_date;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            findViews(itemView);
        }

        private void findViews(View itemView) {
            item_note_title = itemView.findViewById(R.id.item_note_title);
            item_note_text = itemView.findViewById(R.id.item_note_text);
            item_note_date = itemView.findViewById(R.id.item_note_date);
        }

        public void bindViews(NoteHolder holder) {
            item_note_title.setText(noteList.get(holder.getAbsoluteAdapterPosition()).getTitle());
            item_note_text.setText(noteList.get(holder.getAbsoluteAdapterPosition()).getNote());
            item_note_date.setText(new Date(noteList.get(holder.getAbsoluteAdapterPosition()).getDate()).toString());
            itemView.setOnClickListener(v -> mainView.openExistNoteView(noteList.get(holder.getAbsoluteAdapterPosition())));
        }
    }
}
