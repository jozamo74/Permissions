package com.example.permissions


import android.Manifest.permission.ACCESS_COARSE_LOCATION
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.*
import com.example.permissions.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    private val coarsePermission = PermissionRequester(
        this,
        ACCESS_COARSE_LOCATION,
        onRationale = { toast("Show Rationale")},
        onDenied = { toast("Denied")}

    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPermissions.setOnClickListener {
           coarsePermission.runWithPermission {
                //toast("Granted")
               openAppSettings()
           }
        }

    }
}

class PermissionRequester(
    activity: ComponentActivity,
    private val permission: String,
    private val onRationale: () -> Unit = {},
    private val onDenied: () -> Unit = {}
    ) {

    private var onGranted: () -> Unit = {}
    private val permissionLauncher =
        activity.registerForActivityResult(RequestPermission()){ isGranted ->
        when {
            isGranted -> onGranted()
            activity.shouldShowRequestPermissionRationale(permission) -> onRationale()
            else -> onDenied()
        }

    }

    fun runWithPermission(body: () -> Unit) {
        onGranted = body
        permissionLauncher.launch(permission)

    }

}

