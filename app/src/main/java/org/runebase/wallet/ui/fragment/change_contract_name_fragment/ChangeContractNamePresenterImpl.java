package org.runebase.wallet.ui.fragment.change_contract_name_fragment;


import org.runebase.wallet.ui.base.base_fragment.BaseFragmentPresenterImpl;

public class ChangeContractNamePresenterImpl extends BaseFragmentPresenterImpl implements ChangeContractNamePresenter{

    ChangeContractNameView view;
    ChangeContractNameInteractor interactor;

    ChangeContractNamePresenterImpl(ChangeContractNameView changeContractNameView, ChangeContractNameInteractor changeContractNameInteractor){
        view = changeContractNameView;
        interactor = changeContractNameInteractor;
    }

    @Override
    public ChangeContractNameView getView() {
        return view;
    }

    public ChangeContractNameInteractor getInteractor() {
        return interactor;
    }
}
