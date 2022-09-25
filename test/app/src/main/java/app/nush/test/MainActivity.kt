package app.nush.test

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.app.ActivityCompat
import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.UnknownHostException


class MainActivity : Activity() {
    private var startButton: Button? = null
    private var stopButton: Button? = null
    lateinit var buffer: ByteArray
    private val port = 50005
    var recorder: AudioRecord? = null
    private val sampleRate = 16000 // 44100 for music
    private val channelConfig = AudioFormat.CHANNEL_CONFIGURATION_MONO
    private val audioFormat = AudioFormat.ENCODING_PCM_16BIT
    var minBufSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat)
    private var status = true
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startButton = findViewById<View>(R.id.start_button) as Button
        stopButton = findViewById<View>(R.id.stop_button) as Button
        startButton!!.setOnClickListener(startListener)
        stopButton!!.setOnClickListener(stopListener)
    }

    private val stopListener = View.OnClickListener {
        status = false
        recorder!!.release()
        Log.d("VS", "Recorder released")
    }
    private val startListener = View.OnClickListener {
        status = true
        startStreaming()
    }

    @SuppressLint("MissingPermission")
    fun startStreaming() {
        val streamThread = Thread {
            try {
                val socket = DatagramSocket()
                Log.d("VS", "Socket Created")
                val buffer = ByteArray(minBufSize)
                Log.d("VS", "Buffer created of size $minBufSize")
                var packet: DatagramPacket
                val destination = InetAddress.getByName("192.168.0.103")
                Log.d("VS", "Address retrieved")
                recorder = AudioRecord(
                    MediaRecorder.AudioSource.MIC,
                    sampleRate,
                    channelConfig,
                    audioFormat,
                    minBufSize * 10
                )
                Log.d("VS", "Recorder initialized")
                recorder!!.startRecording()
                while (status == true) {


                    //reading data from MIC into buffer
                    minBufSize = recorder!!.read(buffer, 0, buffer.size)

                    //putting buffer in the packet
                    packet = DatagramPacket(buffer, buffer.size, destination, port)
                    socket.send(packet)
                    println("MinBufferSize: $minBufSize")
                }
            } catch (e: UnknownHostException) {
                Log.e("VS", "UnknownHostException")
            } catch (e: IOException) {
                e.printStackTrace()
                Log.e("VS", "IOException")
            }
        }
        streamThread.start()
    }

    companion object {
        var socket: DatagramSocket? = null
    }
}