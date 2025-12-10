package com.amirbahadoramiri.applenotebook.views.bases;

public interface BaseContract {

    interface BaseView {}

    interface BasePresenter<T extends BaseView> {
        void onAttach(T view);
        void onDetach();
    }

}
