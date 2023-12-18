package com.example.hiltscopeexample

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

var counterA = 0
var counterB = 0

@Module
@InstallIn(ActivityRetainedComponent::class)
object MainModule {
    @Provides
    @ActivityRetainedScoped
    fun getScopedBinding(): StateA = StateA(++counterA)

    @Provides
    fun getUnscopedBinding() : StateB = StateB(++counterB)

}

data class StateA (val value : Int)
data class StateB (val value : Int)