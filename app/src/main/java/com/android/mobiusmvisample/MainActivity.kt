package com.android.mobiusmvisample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.spotify.mobius.Connection
import com.spotify.mobius.First
import com.spotify.mobius.Mobius
import com.spotify.mobius.MobiusLoop
import com.spotify.mobius.android.MobiusAndroid
import com.spotify.mobius.functions.Consumer
import drawable.ScreenLogic
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var loopFactory: MobiusLoop.Factory<ScreenModel, ScreenEvent, ScreenEffect>

    private lateinit var controller: MobiusLoop.Controller<ScreenModel, ScreenEvent>

    private var adapter = ImagesAdaper(emptyList(), this)

    init {
        loopFactory =
            Mobius.loop(ScreenLogic(this), ScreenEffectHandler())
                .init({ First.first(ScreenModel(isLoading = true, data = emptyList())) })

        controller =
            MobiusAndroid.controller(loopFactory, ScreenModel(isLoading = true, data = emptyList()))

    }

    private fun connectViews(eventConsumer: Consumer<ScreenEvent>): Connection<ScreenModel> {

        return object : Connection<ScreenModel> {
            override fun accept(model: ScreenModel) {
                if (model.isLoading) {
                    loader.show()
                    eventConsumer.accept(ScreenEvent.UploadData)
                } else {
                    recycleView.swapAdapter(ImagesAdaper(model.data, this@MainActivity), false)
                    loader.hide()
                }
            }

            override fun dispose() {
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = adapter

        controller.connect(this::connectViews)
    }

    public override fun onDestroy() {
        super.onDestroy()
        controller.disconnect()
    }

    public override fun onResume() {
        super.onResume()
        controller.start()
    }

    public override fun onPause() {
        super.onPause()
        controller.stop()
    }
}
