package com.example.battery_level_android_channel

import io.flutter.embedding.android.FlutterActivity

import io.*
import android.*
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.annotation.NonNull
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {
    private val BATTERY_CHANNEL = "com.example.battery_level_android_channel/battery"
    private lateinit var channel: MethodChannel

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        channel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger , BATTERY_CHANNEL )

        channel.setMethodCallHandler {
                call, result ->
            if(call.method == "getBatteryLevel"){
                val arguments = call.arguments as Map<String,String>

                var batteryLevel = getBatteryLevel()

                result.success(batteryLevel)
            }

        }

    }

    private fun getBatteryLevel(): Int {
        var batteryLevel: Int
        if(VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP){
            val batteryManager = getSystemService(Context.BATTERY_SERVICE) as BatteryManager
            batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        } else{
            val intent = ContextWrapper(applicationContext).registerReceiver(null, IntentFilter())
            batteryLevel = intent!!.getIntExtra(BatteryManager.EXTRA_LEVEL,-1) * 100
        }

        return  batteryLevel

    }


}


//class MainActivity: FlutterActivity() {
//    private val CHANNEL = "flutter_screen_record"
//
//    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
//        super.configureFlutterEngine(flutterEngine)
//        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
//            if (call.method == "startScreenRecord") {
//                startScreenRecording(call, result)
//            } else {
//                result.notImplemented()
//            }
//        }
//    }
//
//    private fun startScreenRecording(call: MethodCall, result: MethodChannel.Result) {
//        val resultCode = call.argument<Int>("resultCode")!!
////        val data = call.argument<Intent>("data")!!
//
//        val serviceIntent = Intent(this, ScreenRecordService::class.java)
//        with(serviceIntent) {
//            putExtra("resultCode", resultCode)
////            putExtra("data", data)
//
//            startService(this)
//        }
//        result.success("Screen recording started")
//    }
//}
