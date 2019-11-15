package org.runebase.wallet.ui.fragment.template_library_fragment.dark;

import org.runebase.wallet.model.ContractTemplate;
import org.runebase.wallet.ui.fragment.template_library_fragment.TemplateLibraryFragment;

import java.util.List;

public class TemplateLibraryFragmentDark extends TemplateLibraryFragment {

    @Override
    protected int getLayout() {
        return org.runebase.wallet.R.layout.fragment_template_library;
    }

    @Override
    public void setUpTemplateList(List<ContractTemplate> contractTemplateList) {
        initializeRecyclerView(contractTemplateList, org.runebase.wallet.R.layout.item_template);
    }
}
