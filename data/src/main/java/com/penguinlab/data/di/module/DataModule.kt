package com.penguinlab.data.di.module

import dagger.Module

@Module(
    includes = [
        NetworkModule::class,
        CacheModule::class
    ]
)
class DataModule