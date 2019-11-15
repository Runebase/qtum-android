package org.runebase.wallet.ui.fragment.template_library_fragment;

import org.runebase.wallet.model.ContractTemplate;
import org.runebase.wallet.ui.base.base_fragment.BaseFragmentView;

import java.util.List;

public interface TemplateLibraryView extends BaseFragmentView {

    void setUpTemplateList(List<ContractTemplate> contractTemplateList);

}
