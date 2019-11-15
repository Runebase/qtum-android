package org.runebase.wallet.ui.fragment.event_log_fragment;


import org.runebase.wallet.model.gson.history.History;
import org.runebase.wallet.model.gson.history.TransactionReceipt;
import org.runebase.wallet.ui.base.base_fragment.BaseFragmentPresenterImpl;

import io.realm.Realm;

public class EventLogPresenterImpl extends BaseFragmentPresenterImpl implements EventLogPresenter{

    EventLogView mEventLogView;
    EventLogInteractor mEventLogInteractor;

    EventLogPresenterImpl(EventLogView view, EventLogInteractor interactor){
        mEventLogInteractor = interactor;
        mEventLogView = view;
    }

    @Override
    public void initializeViews() {
        super.initializeViews();
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                TransactionReceipt transactionReceipt = realm.where(TransactionReceipt.class).equalTo("transactionHash",getView().getTxHash()).findFirst();
                getView().updateEventLog(transactionReceipt.getLog());
            }
        });

    }

    @Override
    public EventLogView getView() {
        return mEventLogView;
    }

    EventLogInteractor getInteractor(){
        return mEventLogInteractor;
    }
}
