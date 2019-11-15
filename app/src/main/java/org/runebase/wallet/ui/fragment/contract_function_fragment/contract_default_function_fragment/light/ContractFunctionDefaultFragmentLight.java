package org.runebase.wallet.ui.fragment.contract_function_fragment.contract_default_function_fragment.light;

import org.runebase.wallet.model.AddressWithBalance;
import org.runebase.wallet.model.contract.ContractMethodParameter;
import org.runebase.wallet.ui.fragment.contract_function_fragment.ParameterAdapter;
import org.runebase.wallet.ui.fragment.contract_function_fragment.contract_default_function_fragment.AddressesWithBalanceSpinnerAdapter;
import org.runebase.wallet.ui.fragment.contract_function_fragment.contract_default_function_fragment.ContractFunctionDefaultFragment;

import java.util.List;

public class ContractFunctionDefaultFragmentLight extends ContractFunctionDefaultFragment {

    @Override
    protected int getLayout() {
        return org.runebase.wallet.R.layout.fragment_function_detail_light;
    }

    @Override
    public void setUpParameterList(List<ContractMethodParameter> contractMethodParameterList) {
        mParameterAdapter = new ParameterAdapter(contractMethodParameterList, org.runebase.wallet.R.layout.lyt_constructor_input_light);
        mParameterList.setAdapter(mParameterAdapter);
    }

    @Override
    public void updateAddressWithBalanceSpinner(List<AddressWithBalance> addressWithBalances) {
        AddressesWithBalanceSpinnerAdapter adapter = new AddressesWithBalanceSpinnerAdapterLight(getContext(), addressWithBalances);
        mSpinner.setAdapter(adapter);
    }
}
