package org.runebase.wallet.ui.fragment.contract_management_fragment.dark;

import org.runebase.wallet.R;
import org.runebase.wallet.model.contract.ContractMethod;
import org.runebase.wallet.ui.fragment.contract_management_fragment.ContractManagementFragment;

import java.util.List;

public class ContractManagementFragmentDark extends ContractManagementFragment {

    @Override
    protected int getLayout() {
        return R.layout.fragment_contract_management;
    }

    @Override
    public void setRecyclerView(List<ContractMethod> contractMethodList, boolean needToGetValue) {
        mMethodAdapter = new MethodAdapter(contractMethodList, R.layout.item_contract_property, R.layout.item_contract_method, needToGetValue);
        mRecyclerView.setAdapter(mMethodAdapter);
    }
}
