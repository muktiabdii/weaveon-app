package com.example.hology.utils

import android.Manifest
import android.content.Context
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.*
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import java.io.File

class WevyCameraManager(
    private val context: Context,
    private val lifecycleOwner: LifecycleOwner,
    private val previewView: PreviewView,
    private val onVideoSaved: (File) -> Unit,
    private val onError: (Exception) -> Unit
) {
    private var videoCapture: VideoCapture<Recorder>? = null
    private var currentRecording: Recording? = null
    private var lensFacing = CameraSelector.LENS_FACING_FRONT // default front

    fun initCamera() {
        startCamera(lensFacing)
    }

    private fun startCamera(facing: Int) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().apply {
                setSurfaceProvider(previewView.surfaceProvider)
            }

            val recorder = Recorder.Builder()
                .setQualitySelector(QualitySelector.from(Quality.HD))
                .build()

            videoCapture = VideoCapture.withOutput(recorder)

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    CameraSelector.Builder().requireLensFacing(facing).build(),
                    preview,
                    videoCapture
                )
            } catch (e: Exception) {
                onError(e)
            }
        }, ContextCompat.getMainExecutor(context))
    }

    fun switchCamera() {
        lensFacing = if (lensFacing == CameraSelector.LENS_FACING_FRONT)
            CameraSelector.LENS_FACING_BACK
        else CameraSelector.LENS_FACING_FRONT

        startCamera(lensFacing)
    }

    @RequiresPermission(allOf = [Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO])
    fun startRecording() {
        val file = File(context.cacheDir, "video_${System.currentTimeMillis()}.mp4")
        val outputOptions = FileOutputOptions.Builder(file).build()

        currentRecording = videoCapture?.output
            ?.prepareRecording(context, outputOptions)
            ?.withAudioEnabled()
            ?.start(ContextCompat.getMainExecutor(context)) { event ->
                if (event is VideoRecordEvent.Finalize) {
                    if (!event.hasError()) {
                        onVideoSaved(file)
                    } else {
                        onError(Exception("Recording failed: ${event.error}"))
                    }
                }
            }
    }

    fun stopRecording() {
        currentRecording?.stop()
        currentRecording = null
    }
}
