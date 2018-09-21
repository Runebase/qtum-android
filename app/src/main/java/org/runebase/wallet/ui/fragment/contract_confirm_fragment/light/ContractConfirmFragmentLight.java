package org.runebase.wallet.ui.fragment.contract_confirm_fragment.light;

import android.support.v7.widget.LinearLayoutManager;

import org.runebase.wallet.R;
import org.runebase.wallet.model.contract.ContractMethodParameter;
import org.runebase.wallet.ui.fragment.contract_confirm_fragment.ContractConfirmFragment;

import java.util.List;

public class ContractConfirmFragmentLight extends ContractConfirmFragment {

    public final int LAYOUT = R.layout.lyt_contract_confirm_light;

    @Override
    protected int getLayout() {
        return LAYOUT;
    }

    @Override
    public void initializeViews() {
        super.initializeViews();
        getPresenter().setContractMethodParameterList((List<ContractMethodParameter>) getArguments().getSerializable(paramsKey));
        confirmList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ContractConfirmAdapterLight(getPresenter().getContractMethodParameterList(), "4jhbr4hjb4l23342i4bn2kl4b2352l342k35bv235rl23", this);
        confirmList.setAdapter(adapter);
    }
}
