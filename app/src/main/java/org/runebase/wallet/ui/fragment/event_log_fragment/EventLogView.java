package org.runebase.wallet.ui.fragment.event_log_fragment;

import org.runebase.wallet.model.gson.history.Log;
import org.runebase.wallet.ui.base.base_fragment.BaseFragmentView;

import java.util.List;


public interface EventLogView extends BaseFragmentView {
    int getPosition();
    void updateEventLog(List<Log> logs);
}
