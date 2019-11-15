package org.runebase.wallet.ui.fragment.addresses_detail_fragment;


import org.runebase.wallet.model.gson.history.History;
import org.runebase.wallet.model.gson.token_history.TokenHistory;


public interface AddressesDetailInteractor {
    History getHistory(String txHash);
    TokenHistory getTokenHistory(String txHash);
}
