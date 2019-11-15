package org.runebase.wallet.ui.fragment.watch_contract_fragment;

import org.runebase.wallet.model.ContractTemplate;

public interface OnTemplateClickListener {
    void updateSelection(int adapterPosition);

    void onTemplateClick(ContractTemplate contractTemplate);
}
