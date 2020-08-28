package com.example.mqttkotlina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mqttkotlina.ui.mqtt.MqttFragment
import com.example.mqttkotlina.ui.room.RoomFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, RoomFragment() )
                    .commitNow()
        }
    }
}