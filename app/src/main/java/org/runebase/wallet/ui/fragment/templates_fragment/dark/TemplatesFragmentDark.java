package org.runebase.wallet.ui.fragment.templates_fragment.dark;

import org.runebase.wallet.model.ContractTemplate;
import org.runebase.wallet.ui.fragment.templates_fragment.TemplatesFragment;

import java.util.List;

public class TemplatesFragmentDark extends TemplatesFragment {

    @Override
    protected int getLayout() {
        return org.runebase.wallet.R.layout.fragment_templates;
    }

    @Override
    public void setUpTemplateList(List<ContractTemplate> contractTemplateList) {
        initializeRecyclerView(contractTemplateList, org.runebase.wallet.R.layout.item_template);
    }
}
