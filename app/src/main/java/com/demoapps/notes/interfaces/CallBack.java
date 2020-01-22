package com.demoapps.notes.interfaces;

public interface CallBack {

    void onSuccess(String txnType, String txnStatus, String txnMessage);
    void onFailure(String txnStatus, String txnMessage);
}
