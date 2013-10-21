package com.wordpress.nprogramming;

import com.google.inject.AbstractModule;

public class BitProcessorModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(BitTranslator.class).to(BitTranslatorImpl.class);
    }
}
