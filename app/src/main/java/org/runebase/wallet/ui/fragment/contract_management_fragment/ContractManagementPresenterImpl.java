package org.runebase.wallet.ui.fragment.contract_management_fragment;

import org.runebase.wallet.R;
import org.runebase.wallet.model.contract.Contract;
import org.runebase.wallet.model.contract.ContractMethod;
import org.runebase.wallet.ui.base.base_fragment.BaseFragment;
import org.runebase.wallet.ui.base.base_fragment.BaseFragmentPresenterImpl;

import java.util.List;

public class ContractManagementPresenterImpl extends BaseFragmentPresenterImpl implements ContractManagementPresenter {

    private ContractManagementView mContractManagementFragmentView;
    private ContractManagementInteractor mContractManagementInteractor;

    public ContractManagementPresenterImpl(ContractManagementView contractManagementFragmentView, ContractManagementInteractor contractManagementInteractor) {
        mContractManagementFragmentView = contractManagementFragmentView;
        mContractManagementInteractor = contractManagementInteractor;
    }

    @Override
    public void initializeViews() {
        super.initializeViews();
        String contractAddress = getView().getContractAddress();
        if (contractAddress != null && !contractAddress.isEmpty()) {
            String uiid = getView().getContractTemplateUiid();
            List<ContractMethod> contractMethodList = getInteractor().getContractListByUiid(uiid);
            if (contractMethodList != null) {
                getView().setRecyclerView(contractMethodList, true);
            } else {
                getView().setAlertDialog(R.string.error, R.string.fail_to_get_contract_methods, BaseFragment.PopUpType.error);
            }
        } else {
            getView().setTitleText(R.string.contract_details);
            String abi = getView().getContractABI();
            List<ContractMethod> contractMethodList = getInteractor().getContractListByAbi(abi);
            if (contractMethodList != null) {
                getView().setRecyclerView(contractMethodList, false);
            } else {
                getView().setAlertDialog(R.string.error, R.string.fail_to_get_contract_methods, BaseFragment.PopUpType.error);
            }
        }
    }

    @Override
    public ContractManagementView getView() {
        return mContractManagementFragmentView;
    }

    private ContractManagementInteractor getInteractor() {
        return mContractManagementInteractor;
    }

    @Override
    public Contract getContractByAddress(String contractAddress) {
        return getInteractor().getContractByAddress(contractAddress);
    }
}
