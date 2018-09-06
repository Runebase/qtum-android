package org.runebase.wallet.dataprovider.services.update_service.listeners;

import org.runebase.wallet.model.gson.history.History;

public interface TransactionListener {
    void onNewHistory(History history);

    boolean getVisibility();
}
