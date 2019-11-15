package org.runebase.wallet.ui.fragment.addresses_fragment;

import org.bitcoinj.crypto.DeterministicKey;
import org.runebase.wallet.ui.base.base_fragment.BaseFragmentView;

import java.util.List;

public interface AddressesView extends BaseFragmentView {
    void updateAddressList(List<String> deterministicKeys);

    void setAdapterNull();
}
