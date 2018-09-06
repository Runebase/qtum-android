package org.runebase.wallet.ui.fragment.addresses_detail_fragment;


import org.runebase.wallet.model.gson.history.History;


public interface AddressesDetailInteractor {
    History getHistory(int position);
}
