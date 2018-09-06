package org.runebase.wallet.dataprovider.services.update_service.listeners;

import org.runebase.wallet.model.gson.token_balance.TokenBalance;

public interface TokenBalanceChangeListener {
    void onBalanceChange(TokenBalance tokenBalance);
}
