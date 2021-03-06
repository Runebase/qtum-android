package org.runebase.wallet.ui.fragment.addresses_fragment.dark;

import org.runebase.wallet.R;
import org.runebase.wallet.ui.fragment.addresses_fragment.AddressesFragment;
import org.bitcoinj.crypto.DeterministicKey;

import java.util.List;

public class AddressesFragmentDark extends AddressesFragment {
    @Override
    protected int getLayout() {
        return R.layout.fragment_addresses;
    }

    @Override
    public void updateAddressList(List<DeterministicKey> deterministicKeys) {
        mAddressAdapter = new AddressesAdapterDark(deterministicKeys, this);
        mRecyclerView.setAdapter(mAddressAdapter);
    }
}
