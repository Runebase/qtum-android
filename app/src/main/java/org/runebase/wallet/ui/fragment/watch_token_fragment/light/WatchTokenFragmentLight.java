package org.runebase.wallet.ui.fragment.watch_token_fragment.light;

import org.runebase.wallet.model.ContractTemplate;
import org.runebase.wallet.ui.fragment.watch_contract_fragment.OnTemplateClickListener;
import org.runebase.wallet.ui.fragment.watch_contract_fragment.TemplatesAdapter;
import org.runebase.wallet.ui.fragment.watch_contract_fragment.WatchContractFragment;
import org.runebase.wallet.ui.fragment.watch_token_fragment.WatchTokenFragment;
import org.runebase.wallet.utils.FontManager;

import java.util.List;

public class WatchTokenFragmentLight extends WatchTokenFragment {

    @Override
    protected int getLayout() {
        return org.runebase.wallet.R.layout.fragment_watch_token_light;
    }

    @Override
    public void initializeViews() {
        super.initializeViews();
        mEditTextContractName.setTypeface(FontManager.getInstance().getFont(getResources().getString(org.runebase.wallet.R.string.proximaNovaSemibold)));
        mEditTextContractAddress.setTypeface(FontManager.getInstance().getFont(getResources().getString(org.runebase.wallet.R.string.proximaNovaSemibold)));
    }

}
