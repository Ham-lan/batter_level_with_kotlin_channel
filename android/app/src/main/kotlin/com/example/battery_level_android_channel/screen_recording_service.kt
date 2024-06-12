//package com.example.battery_level_android_channel
//
//import android.app.Activity
//import android.app.Service
//import android.content.Context
//import android.content.Intent
//import android.graphics.PixelFormat
//import android.hardware.display.DisplayManager
//import android.hardware.display.VirtualDisplay
//import android.media.MediaRecorder
//import android.media.projection.MediaProjection
//import android.media.projection.MediaProjectionManager
//import android.os.Build
//import android.os.Environment
//import android.os.IBinder
//import android.util.DisplayMetrics
//import android.util.Log
//import android.view.WindowManager
//import androidx.annotation.RequiresApi
//import java.io.IOException
//
//class ScreenRecordService : Service() {
//    private var mediaProjectionManager: MediaProjectionManager? = null
//    private var mediaProjection: MediaProjection? = null
//    private var mediaRecorder: MediaRecorder? = null
//    private var virtualDisplay: VirtualDisplay? = null
//    private var screenDensity: Int = 0
//    private var displayWidth: Int = 1280
//    private var displayHeight: Int = 720
//
//    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
//        mediaProjectionManager = getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
//
//        val resultCode = intent.getIntExtra("resultCode", Activity.RESULT_CANCELED)
////        val data = intent.getParcelableExtra<Intent>("data")
//
//        mediaProjection = mediaProjectionManager?.getMediaProjection(resultCode, data!!)
//        initRecorder()
//        createVirtualDisplay()
//
//        mediaRecorder?.start()
//        return START_NOT_STICKY
//    }
//
//    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//    private fun createVirtualDisplay() {
//        virtualDisplay = mediaProjection?.createVirtualDisplay(
//            "ScreenRecordService",
//            displayWidth, displayHeight, screenDensity,
//            DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
//            mediaRecorder?.surface, null, null
//        )
//    }
//
//    private fun initRecorder() {
//        mediaRecorder = MediaRecorder()
//        mediaRecorder?.apply {
//            setAudioSource(MediaRecorder.AudioSource.MIC)
//            setVideoSource(MediaRecorder.VideoSource.SURFACE)
//            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
//            setOutputFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).toString() + "/recorded_video.mp4")
//            setVideoSize(displayWidth, displayHeight)
//            setVideoEncoder(MediaRecorder.VideoEncoder.H264)
//            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
//            setVideoEncodingBitRate(512 * 1000)
//            setVideoFrameRate(30)
//            try {
//                prepare()
//            } catch (e: IOException) {
//                Log.e("ScreenRecordService", "MediaRecorder prepare failed: ${e.message}")
//            }
//        }
//    }
//
//    override fun onBind(intent: Intent?): IBinder? {
//        return null
//    }
//
//    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//    override fun onDestroy() {
//        super.onDestroy()
//        mediaRecorder?.stop()
//        mediaRecorder?.release()
//        mediaProjection?.stop()
//        virtualDisplay?.release()
//    }
//}
