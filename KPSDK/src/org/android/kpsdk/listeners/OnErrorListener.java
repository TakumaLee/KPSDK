package org.android.kpsdk.listeners;

public interface OnErrorListener {
    void onException(Throwable throwable);
    void onFail(String reason);
}