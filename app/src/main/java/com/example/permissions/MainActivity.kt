package com.example.permissions


import android.Manifest.permission.ACCESS_COARSE_LOCATION
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.permissions.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val coarsePermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted ->
            when {
                isGranted -> toast ("Granted")
                shouldShowRequestPermissionRationale(ACCESS_COARSE_LOCATION) ->
                    toast("Show rationale")
                else ->toast("Denied")
            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPermissions.setOnClickListener {
           coarsePermission.launch(ACCESS_COARSE_LOCATION)
        }

    }
}