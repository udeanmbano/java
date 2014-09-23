package com.pubnub.api;

import java.util.concurrent.CountDownLatch;

public class TestHelper {
    static class SimpleCallback extends Callback {

        protected CountDownLatch latch;
        Object response;
        Boolean error;

        public SimpleCallback(CountDownLatch latch) {
            this.latch = latch;
        }
        public SimpleCallback() {}

        public Object getResponse() {
            return response;
        }

        public Boolean responseIsError() {
            return error;
        }

        @Override
        public void successCallback(String channel, Object message) {
            this.response = message;
            this.error = false;
            if (this.latch != null) {
                this.latch.countDown();
            }
        }

        @Override
        public void errorCallback(String channel, PubnubError error) {
            this.error = true;
            if (this.latch != null) {
                this.latch.countDown();
            }
        }
    }
}
