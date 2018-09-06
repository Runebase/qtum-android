package org.runebase.wallet.ui.fragment.event_log_fragment;


import org.runebase.wallet.model.gson.history.History;

public interface EventLogInteractor {
    History getHistory(int position);
}
