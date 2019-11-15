package org.runebase.wallet.model;

import com.google.gson.annotations.SerializedName;

import org.runebase.wallet.model.backup.TemplateJSON;

public class ContractTemplate {
    @SerializedName("name")
    private String mName;
    @SerializedName("date")
    private Long mDate;
    @SerializedName("type")
    private String mType;
    @SerializedName("uuid")
    private String mUuid;
    @SerializedName("isFullContractTemplate")
    private boolean mIsFullContractTemplate;

    /**
     * Constructor for unit testing
     */
    public ContractTemplate() {
    }

    public ContractTemplate(String name, Long date, String type, String uuid, boolean isFullContractTemplate, boolean selectedABI) {
        mName = name;
        mDate = date;
        mType = type;
        mUuid = uuid;
        mIsFullContractTemplate = isFullContractTemplate;
        this.selectedABI = selectedABI;
    }

    public ContractTemplate(String name, Long date, String contractType, String uuid) {
        this.mName = name;
        this.mDate = date;
        this.mType = contractType;
        this.mUuid = uuid;
        mIsFullContractTemplate = true;
    }

    public ContractTemplate(TemplateJSON templateJSON) {
        this.mName = templateJSON.getName();
        //this.mDate = templateJSON.getCreationDate();
        this.mType = templateJSON.getType();
        this.mUuid = templateJSON.getUuid();
        mIsFullContractTemplate = true;
    }

    public boolean isSelectedABI() {
        return selectedABI;
    }

    public void setSelectedABI(boolean selectedABI) {
        this.selectedABI = selectedABI;
    }

    private boolean selectedABI;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public Long getDate() {
        return mDate;
    }

    public void setData(Long date) {
        this.mDate = date;
    }

    public String getContractType() {
        return mType;
    }

    public void setContractType(String contractType) {
        this.mType = contractType;
    }

    public String getUuid() {
        return mUuid;
    }

    public void setUuid(String uuid) {
        this.mUuid = uuid;
    }

    public boolean isFullContractTemplate() {
        return mIsFullContractTemplate;
    }

    public void setFullContractTemplate(boolean fullContractTemplate) {
        mIsFullContractTemplate = fullContractTemplate;
    }
}