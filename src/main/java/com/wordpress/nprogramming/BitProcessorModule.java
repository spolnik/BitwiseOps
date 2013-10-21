package com.wordpress.nprogramming;

import com.google.inject.AbstractModule;

public class BitProcessorModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(BitProcessor.class).to(BitProcessorImpl.class);
    }
}
