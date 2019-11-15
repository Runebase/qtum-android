package org.runebase.wallet.dataprovider.services.update_service.listeners;

import org.runebase.wallet.model.gson.qstore.ContractPurchase;

public interface ContractPurchaseListener {
    void onContractPurchased(ContractPurchase purchaseData);
}
