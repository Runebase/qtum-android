package org.runebase.wallet.ui.fragment.contract_function_fragment.contract_default_function_fragment;

import org.runebase.wallet.model.contract.ContractMethodParameter;
import org.runebase.wallet.ui.base.base_fragment.BaseFragmentPresenter;

import java.util.List;

public interface ContractFunctionDefaultPresenter extends BaseFragmentPresenter {

    void onCallClick(List<ContractMethodParameter> contractMethodParameterList, String contractAddress, String fee, int gasLimit, int gasPrice, String methodName, String addressFrom,String sendToAddress, String passphrase);

}