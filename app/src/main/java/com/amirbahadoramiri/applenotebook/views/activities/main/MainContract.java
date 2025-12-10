package com.amirbahadoramiri.applenotebook.views.activities.main;

import com.amirbahadoramiri.applenotebook.models.Note;
import com.amirbahadoramiri.applenotebook.views.bases.BaseContract;

import java.util.List;

public interface MainContract {

    interface MainView extends BaseContract.BaseView {

        void showNotes(List<Note> notes);

        void deleteAllNotes();

        void openExistNoteView(Note note);
        void openNewNoteView();

        void showToast(String s, int length);

        void scrollToPosition(int pos);

        void openSettings();

        void openAbout();

        void openMenu();

    }

    interface MainPresenter extends BaseContract.BasePresenter<MainView> {

        @Override
        void onAttach(MainView view);

        @Override
        void onDetach();

        void onDeleteAllNote(int count);

        void onNewNoteClick();

        void onSearch(String s);

        void showAllNote();

        void openSettingsClick();

        void openAboutClick();

        void onMenuClick();

    }

}
