package org.runebase.wallet.ui.fragment.templates_fragment;

import org.runebase.wallet.model.ContractTemplate;
import org.runebase.wallet.ui.base.base_fragment.BaseFragmentView;

import java.util.List;

public interface TemplatesView extends BaseFragmentView {
    void setUpTemplateList(List<ContractTemplate> contractTemplateList);
}
