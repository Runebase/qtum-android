package org.runebase.wallet.ui.fragment.my_contracts_fragment.dark;

import org.runebase.wallet.model.contract.Contract;
import org.runebase.wallet.ui.fragment.my_contracts_fragment.ContractItemListener;
import org.runebase.wallet.ui.fragment.my_contracts_fragment.MyContractsFragment;

import java.util.List;

public class MyContractsFragmentDark extends MyContractsFragment {

    @Override
    protected int getLayout() {
        return org.runebase.wallet.R.layout.fragment_my_contracts;
    }


    @Override
    public void setUpRecyclerView(List<Contract> contractList, ContractItemListener contractItemListener) {
        mContractAdapter = new ContractAdapter(contractList, org.runebase.wallet.R.layout.item_contract_list, contractItemListener);
        mRecyclerView.setAdapter(mContractAdapter);
    }
}
