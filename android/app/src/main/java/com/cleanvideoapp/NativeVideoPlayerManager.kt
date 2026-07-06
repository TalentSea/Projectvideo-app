package com.cleanvideoapp

import com.facebook.react.bridge.ReadableMap
import com.facebook.react.common.MapBuilder
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp

class NativeVideoPlayerManager : SimpleViewManager<NativeVideoPlayerView>() {
    override fun getName(): String = "NativeVideoPlayer"

    override fun createViewInstance(reactContext: ThemedReactContext): NativeVideoPlayerView {
        return NativeVideoPlayerView(reactContext)
    }

    @ReactProp(name = "source")
    fun setSource(view: NativeVideoPlayerView, source: ReadableMap?) {
        view.setUri(source?.getString("uri"))
    }

    @ReactProp(name = "paused", defaultBoolean = true)
    fun setPaused(view: NativeVideoPlayerView, paused: Boolean) {
        view.setPaused(paused)
    }

    override fun getExportedCustomDirectEventTypeConstants(): MutableMap<String, Any> {
        return MapBuilder.of(
                "onLoadStart", MapBuilder.of("registrationName", "onLoadStart"),
                "onLoad", MapBuilder.of("registrationName", "onLoad"),
                "onError", MapBuilder.of("registrationName", "onError")
        )
    }

    override fun onDropViewInstance(view: NativeVideoPlayerView) {
        view.releasePlayer()
        super.onDropViewInstance(view)
    }
}