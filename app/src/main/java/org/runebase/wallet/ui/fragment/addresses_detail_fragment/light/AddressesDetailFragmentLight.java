package org.runebase.wallet.ui.fragment.addresses_detail_fragment.light;

import org.runebase.wallet.R;
import org.runebase.wallet.model.gson.history.TransactionInfo;
import org.runebase.wallet.model.gson.history.Vin;
import org.runebase.wallet.model.gson.history.Vout;
import org.runebase.wallet.ui.fragment.addresses_detail_fragment.AddressesDetailFragment;

import java.util.List;

public class AddressesDetailFragmentLight extends AddressesDetailFragment {

    @Override
    protected int getLayout() {
        return R.layout.fragment_addresses_detail_light;
    }

    @Override
    public void setUpRecyclerView(List<Vin> transactionFrom, List<Vout> transactionTo, String symbol) {
        mAddressesDetailAdapterTo = new AddressesDetailAdapterLight<>(transactionTo,symbol);
        mAddressesDetailAdapterFrom = new AddressesDetailAdapterLight<>(transactionFrom,symbol);
        mRecyclerViewFrom.setAdapter(mAddressesDetailAdapterFrom);
        mRecyclerViewTo.setAdapter(mAddressesDetailAdapterTo);
    }
}
