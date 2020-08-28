package com.example.mqttkotlina.ui.mqtt

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mqttkotlina.R
import com.example.mqttkotlina.manager.MQTTConnectionParams
import com.example.mqttkotlina.manager.MQTTmanager
import com.example.mqttkotlina.protocols.UIUpdaterInterface
import kotlinx.android.synthetic.main.mqtt_fragment.*

class MqttFragment : Fragment(), UIUpdaterInterface {

    companion object {
        fun newInstance() = MqttFragment()
    }

    var mqttManager: MQTTmanager? = null

//    private lateinit var viewModel: MqttViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        connect()
        return inflater.inflate(R.layout.mqtt_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        viewModel = ViewModelProviders.of(this).get(MqttViewModel::class.java)
    }

    override fun resetUIWithConnection(status: Boolean) {
//        ipAddressField.isEnabled  = !status
//        topicField.isEnabled      = !status
//        messageField.isEnabled    = status
//        connectBtn.isEnabled      = !status
        sendBtn.isEnabled         = status

        if (status){
            updateStatusViewWith("Connected")
        }else{
            updateStatusViewWith("Disconnected")
        }
    }

    override fun updateStatusViewWith(status: String) {
        statusLabl.text = status
    }

    override fun update(message: String) {
        var text = messageHistoryView.text.toString()
        var newText = text.toString() + "\n ประกาศ : " + message +  "\n"
        messageHistoryView.setText(newText)
        messageHistoryView.setSelection(messageHistoryView.text.length)
    }

    fun connect(){
        var host = "tcp://" + "broker.emqx.io" + ":1883"
        var topic = "test/soso"
        var connectionParams = MQTTConnectionParams("MQTTSample",host,topic,"","")
        var applicationContext = context!!
        mqttManager = MQTTmanager(connectionParams,applicationContext,this)
        mqttManager?.connect()
    }

    fun sendMessage(view: View){
        mqttManager?.publish(messageField.text.toString())
        messageField.setText("")
    }

    //
    //

}