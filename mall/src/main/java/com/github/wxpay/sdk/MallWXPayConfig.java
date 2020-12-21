package com.github.wxpay.sdk;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class MallWXPayConfig extends WXPayConfig{
    @Override
    String getAppID() {
        return "wx71dfe9d7cf33f2d2";
    }

    @Override
    String getMchID() {
        return "1525922291";
    }

    @Override
    String getKey() {
        return "798art798whgs798qsdgfdHGSDAdgsvb";
    }

    @Override
    InputStream getCertStream() {
        return new ByteArrayInputStream(new byte[1024]);
    }

    @Override
    IWXPayDomain getWXPayDomain() {
        IWXPayDomain iwxPayDomain = new IWXPayDomain() {

            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }

            public DomainInfo getDomain(WXPayConfig config) {
                return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
        return iwxPayDomain;

    }
}
