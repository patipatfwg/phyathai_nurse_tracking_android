package com.example.mqttkotlina.ui.client

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.*
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mqttkotlina.R
import com.example.mqttkotlina.model.detect.request.Androidbox
import com.example.mqttkotlina.model.view.response.Nurse
import com.fg.mdp.fwgfacilitiesfinder.clients.APIClient
import com.google.gson.Gson
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ClientFragment : Fragment() {

    //https://github.com/kevin-vista/bluetooth-scanner

    var FLAG_SharedPreferences = 1

    private val ACCESS_COARSE_LOCATION_CODE = 1
    private val REQUEST_ENABLE_BLUETOOTH = 2
    private val SCAN_MODE_ERROR = 3

    private var bluetoothReceiverRegistered: Boolean = false
    private var scanModeReceiverRegistered: Boolean = false
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var mBluetoothAdapter: BluetoothAdapter? = null
    private var mBluetoothDevice: BluetoothDevice? = null
    private var devices: ArrayList<Nurse>? = ArrayList()
    private var checkBluetoothStop = true
    private var handler = Handler()

    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    internal var textDeviceName: TextView? = null
    internal var textDeviceMac: TextView? = null
    internal var textDevicePaired: TextView? = null
    internal var textDeviceSignal: TextView? = null

    var gson = Gson()
    val date = Date()
    val dateFormatWithZone = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val currentDate = dateFormatWithZone.format(date)
    var DefaultGetVersion :String? = "3003202005001"
    var DefaultGetiTAGList :ArrayList<String>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
 
        return inflater.inflate(R.layout.client_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }



}