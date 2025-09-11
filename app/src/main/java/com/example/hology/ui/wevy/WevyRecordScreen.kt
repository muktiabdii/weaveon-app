package com.example.hology.ui.wevy

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.hology.R
import com.example.hology.cache.UserData
import com.example.hology.ui.common.ActionResultDialog
import com.example.hology.ui.common.TopNavbar
import com.example.hology.ui.theme.NeutralWhite
import com.example.hology.utils.WevyCameraManager
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WevyRecordScreen(
    viewModel: WevyViewModel,
    navController: NavController,
    wevyId: String,
    activityId: String
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by viewModel.state.collectAsState()
    val user = UserData

    var cameraManager by remember { mutableStateOf<WevyCameraManager?>(null) }
    var isRecording by remember { mutableStateOf(false) }
    var recordingTime by remember { mutableIntStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }

    val previewView = remember { PreviewView(context) }

    // timer recording
    LaunchedEffect(isRecording) {
        if (isRecording) {
            recordingTime = 0
            while (isRecording) {
                delay(1000)
                recordingTime++
            }
        }
    }

    // permission launcher
    val permissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { perms ->
        val granted = perms.values.all { it }
        if (granted) {
            val manager = WevyCameraManager(
                context = context,
                lifecycleOwner = lifecycleOwner,
                previewView = previewView,
                onVideoSaved = { file ->
                    viewModel.uploadVideo(file, user.uid, wevyId, activityId)
                    showDialog = true
                },
                onError = { e -> e.printStackTrace() }
            )
            manager.initCamera()
            cameraManager = manager
        }
    }

    LaunchedEffect(Unit) { launcher.launch(permissions) }

    fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return "%02d:%02d:%02d".format(0, minutes, remainingSeconds)
    }

    Scaffold(
        topBar = { TopNavbar(title = "Wevy", navController = navController) },
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    // record
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .clickable {
                                val hasCamera = ContextCompat.checkSelfPermission(
                                    context, Manifest.permission.CAMERA
                                ) == PackageManager.PERMISSION_GRANTED
                                val hasAudio = ContextCompat.checkSelfPermission(
                                    context, Manifest.permission.RECORD_AUDIO
                                ) == PackageManager.PERMISSION_GRANTED

                                if (hasCamera && hasAudio) {
                                    if (isRecording) {
                                        cameraManager?.stopRecording()
                                        isRecording = false
                                    } else {
                                        cameraManager?.startRecording()
                                        isRecording = true
                                    }
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Izin kamera/mic belum diberikan",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(
                                id = if (isRecording) R.drawable.ic_stop else R.drawable.ic_play
                            ),
                            contentDescription = "Record Button",
                            modifier = Modifier.size(70.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    // switch
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .clickable { cameraManager?.switchCamera() },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_switch),
                            contentDescription = "Flip Camera",
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {

                // timer bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black)
                        .padding(vertical = 17.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = formatTime(recordingTime),
                        color = NeutralWhite,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        modifier = Modifier
                            .background(Color(0xFFFF0707), RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                // camera preview
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    AndroidView(
                        factory = { previewView },
                        modifier = Modifier.fillMaxSize()
                    )

                    // overlay oval
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(width = 180.dp, height = 150.dp)
                                .offset(y = (-50).dp)
                                .border(
                                    width = 2.dp,
                                    color = NeutralWhite,
                                    shape = RoundedCornerShape(percent = 50)
                                )
                        )
                    }
                }
            }

            // dialog
            if (showDialog) {
                when (state) {
                    is WevyState.Loading -> {
                        ActionResultDialog(
                            isSuccess = false,
                            title = "",
                            message = "",
                            buttonText = "Lihat Hasil",
                            onDismissRequest = {},
                            onButtonClick = {},
                            isLoading = true
                        )
                    }
                    is WevyState.Success -> {
                        ActionResultDialog(
                            isSuccess = true,
                            title = "Video Berhasil Diproses!",
                            message = "Silahkan lihat hasil AI Detection",
                            buttonText = "Lihat Hasil",
                            onDismissRequest = {
                                showDialog = false
                                recordingTime = 0
                            },
                            onButtonClick = {
                                showDialog = false
                                recordingTime = 0
                            },
                            isLoading = false
                        )
                    }
                    is WevyState.Error -> {
                        ActionResultDialog(
                            isSuccess = false,
                            title = "Gagal Memproses Video",
                            message = (state as WevyState.Error).message,
                            buttonText = "Tutup",
                            onDismissRequest = {
                                showDialog = false
                                recordingTime = 0
                            },
                            onButtonClick = {
                                showDialog = false
                                recordingTime = 0
                            },
                            isLoading = false
                        )
                    }
                    else -> {}
                }
            }
        }
    }
}