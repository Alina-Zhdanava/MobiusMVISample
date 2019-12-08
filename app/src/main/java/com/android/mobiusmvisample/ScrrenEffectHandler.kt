package com.android.mobiusmvisample

import com.spotify.mobius.Connectable
import com.spotify.mobius.Connection
import com.spotify.mobius.functions.Consumer

class ScreenEffectHandler : Connectable<ScreenEffect, ScreenEvent> {
    override fun connect(output: Consumer<ScreenEvent>): Connection<ScreenEffect> {
        return object : Connection<ScreenEffect> {
            override fun accept(effect: ScreenEffect) {
            }

            override fun dispose() {}
        }
    }
}