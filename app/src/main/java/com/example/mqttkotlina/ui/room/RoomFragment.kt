package com.example.mqttkotlina.ui.room

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mqttkotlina.R
import com.example.mqttkotlina.adapter.RoomAdapter
import com.example.mqttkotlina.manager.MQTTConnectionParams
import com.example.mqttkotlina.manager.MQTTmanager
import com.example.mqttkotlina.manager.RoomManager
import com.example.mqttkotlina.model.view.response.ViewRes
import com.example.mqttkotlina.protocols.UIUpdaterInterface
import com.google.gson.Gson
import com.phayathai.dashboard_bedv2.adapter.RoomListener
import kotlinx.android.synthetic.main.mqtt_fragment.*
import kotlinx.android.synthetic.main.room_fragment.*

class RoomFragment : Fragment(), RoomListener, UIUpdaterInterface {

    var mqttManager: MQTTmanager? = null
    val ward = "Ward 1"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        connect()
        return inflater.inflate(R.layout.room_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun resetUIWithConnection(status: Boolean) {
        updateStatusViewWith("Connected")
    }

    override fun updateStatusViewWith(status: String) {
        textTitle.text = ward
    }

     fun update2(message: String) {
        var text = messageHistoryView.text.toString()
        var newText = text.toString() + "\n ประกาศ : " + message +  "\n"
        messageHistoryView.setText(newText)
        messageHistoryView.setSelection(messageHistoryView.text.length)
         Log.w("updateText","Hi")
    }

    override fun update(message: String) {
        val end_y = 8
        val start_x = end_y
        val json = message
        val gson = Gson()
        val ViewRes: ViewRes = gson.fromJson(json, ViewRes::class.java)
        val room_list = ViewRes?.body?.room
        val room_listY = room_list?.subList(0,end_y)
        val room_listX = room_list?.subList(start_x,room_list.size)
        val roomAdapterY = RoomAdapter( ArrayList(room_listY) , this@RoomFragment,context)
        val roomAdapterX = RoomAdapter( ArrayList(room_listX) , this@RoomFragment,context)
        recyclerView_room.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            isNestedScrollingEnabled = false
            adapter = roomAdapterY
            onFlingListener = null
        }
        recyclerView_room2.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
            isNestedScrollingEnabled = false
            adapter = roomAdapterX
            onFlingListener = null
        }
        roomAdapterY.notifyDataSetChanged()
        roomAdapterX.notifyDataSetChanged()
    }

    fun connect(){
        var host = "tcp://" + "broker.emqx.io" + ":1883"
        var topic = "PhyathaiIPD/"+ward
        var connectionParams = MQTTConnectionParams("MQTTSample",host,topic,"","")
        var applicationContext = context!!
        mqttManager = MQTTmanager(connectionParams,applicationContext,this)
        mqttManager?.connect()
    }

    fun sendMessage(view: View){
        mqttManager?.publish(messageField.text.toString())
        messageField.setText("")
    }

    override fun onItemClick() {
        TODO("Not yet implemented")
    }

}