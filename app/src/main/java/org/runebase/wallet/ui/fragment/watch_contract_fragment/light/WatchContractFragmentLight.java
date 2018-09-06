package org.runebase.wallet.ui.fragment.watch_contract_fragment.light;

import org.runebase.wallet.model.ContractTemplate;
import org.runebase.wallet.ui.fragment.watch_contract_fragment.OnTemplateClickListener;
import org.runebase.wallet.ui.fragment.watch_contract_fragment.TemplatesAdapter;
import org.runebase.wallet.ui.fragment.watch_contract_fragment.WatchContractFragment;
import org.runebase.wallet.utils.FontManager;

import java.util.List;

public class WatchContractFragmentLight extends WatchContractFragment {

    @Override
    protected int getLayout() {
        return org.runebase.wallet.R.layout.fragment_watch_contract_light;
    }

    @Override
    public void initializeViews() {
        super.initializeViews();
        mEditTextContractName.setTypeface(FontManager.getInstance().getFont(getResources().getString(org.runebase.wallet.R.string.proximaNovaSemibold)));
        mEditTextContractAddress.setTypeface(FontManager.getInstance().getFont(getResources().getString(org.runebase.wallet.R.string.proximaNovaSemibold)));
    }

    @Override
    public void setUpTemplatesList(List<ContractTemplate> contractTemplateList, OnTemplateClickListener listener) {
        mRecyclerViewTemplates.setAdapter(new TemplatesAdapter(contractTemplateList, listener, org.runebase.wallet.R.layout.item_template_chips_light, org.runebase.wallet.R.drawable.chip_selected_corner_background));
    }
}
