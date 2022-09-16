package com.xd.camerademokotlinstartercode

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var tempImgFileName: String;
    private lateinit var tempImgUri: Uri;
    private lateinit var cameraResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.imageProfile)
        textView = findViewById(R.id.text_view)

        Util.checkPermissions(this)

        tempImgFileName= "img"
        val tempImgFile = File(getExternalFilesDir(null),tempImgFileName)
        tempImgUri = FileProvider.getUriForFile(this, "com.xd.camerademokotlin", tempImgFile)
        cameraResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val bitmap = Util.getBitmap(this, tempImgUri)
                imageView.setImageBitmap(bitmap)
            }
        }
    }

    fun onChangePhotoClicked(view: View){
        val intent:Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, tempImgUri)
        startActivity(intent)
        cameraResult.launch(intent)
    }
}