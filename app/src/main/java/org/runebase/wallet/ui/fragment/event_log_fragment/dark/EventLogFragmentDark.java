package org.runebase.wallet.ui.fragment.event_log_fragment.dark;

import org.runebase.wallet.R;
import org.runebase.wallet.model.gson.history.Log;
import org.runebase.wallet.ui.fragment.event_log_fragment.EventLogFragment;

import java.util.List;


public class EventLogFragmentDark extends EventLogFragment {

    @Override
    protected int getLayout() {
        return R.layout.fragment_event_log;
    }

    @Override
    public void updateEventLog(List<Log> logs) {
        mEventLogAdapter = new EventLogAdapter(logs, R.layout.item_event_log, R.layout.item_event_log_data);
        mRecyclerViewLogs.setAdapter(mEventLogAdapter);
    }
}
