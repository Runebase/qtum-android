package org.runebase.wallet.ui.fragment.contract_confirm_fragment.dark;

import android.support.v7.widget.LinearLayoutManager;

import org.runebase.wallet.model.contract.ContractMethodParameter;
import org.runebase.wallet.ui.fragment.contract_confirm_fragment.ContractConfirmFragment;

import java.util.List;

import butterknife.OnClick;

public class ContractConfirmFragmentDark extends ContractConfirmFragment {

    public final int LAYOUT = org.runebase.wallet.R.layout.lyt_contract_confirm;

    @OnClick(org.runebase.wallet.R.id.cancel)
    public void onCancelClick() {
        getActivity().onBackPressed();
    }

    @Override
    protected int getLayout() {
        return LAYOUT;
    }

    @Override
    public void initializeViews() {
        super.initializeViews();
        getPresenter().setContractMethodParameterList((List<ContractMethodParameter>) getArguments().getSerializable(paramsKey));
        confirmList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ContractConfirmAdapterDark(getPresenter().getContractMethodParameterList(), "4jhbr4hjb4l23342i4bn2kl4b2352l342k35bv235rl23", this);
        confirmList.setAdapter(adapter);
    }
}
