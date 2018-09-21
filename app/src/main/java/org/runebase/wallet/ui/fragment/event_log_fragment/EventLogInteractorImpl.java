package org.runebase.wallet.ui.fragment.event_log_fragment;


import android.content.Context;

import org.runebase.wallet.datastorage.HistoryList;
import org.runebase.wallet.model.gson.history.History;

import java.lang.ref.WeakReference;
import java.util.List;

public class EventLogInteractorImpl implements EventLogInteractor{

    WeakReference<Context> mContext;

    EventLogInteractorImpl(Context context){
        mContext = new WeakReference<Context>(context);
    }

    public History getHistory(int position) {
        List<History> historyList = HistoryList.getInstance().getHistoryList();
        if (historyList != null && historyList.size() > position) {
            return historyList.get(position);
        } else {
            return null;
        }
    }

}
