package com.amirbahadoramiri.applenotebook.views.activities.note;

import com.amirbahadoramiri.applenotebook.models.Note;
import com.amirbahadoramiri.applenotebook.views.bases.BaseContract;

public interface NoteContract {

    interface NoteView extends BaseContract.BaseView {

        void showNote(Note note);

        void saveNote(int result_code,Note note);

        void deleteNote(Note note);

        void showToast(String s, int length);

        void onFinishActivity();

        void openMenu();

        void shareText();

    }

    interface NotePresenter extends BaseContract.BasePresenter<NoteView> {

        @Override
        void onAttach(NoteView view);

        @Override
        void onDetach();

        void onDeleteCLick();

        void onBackClick();

        void onSaveClick(String title, String text);

        void onShareTextClick();

        void onMenuClick();

    }

}
