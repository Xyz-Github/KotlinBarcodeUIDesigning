package com.bignerdranch.android.kotlinbarcodeuidesigning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import com.google.zxing.integration.android.IntentIntegrator


class MainActivity : AppCompatActivity() {
    lateinit var btnBarcode: ToggleButton
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "KotlinApp"
        btnBarcode = findViewById(R.id.zxing_back_button)
        textView = findViewById(R.id.zxing_barcode_scanner)
        btnBarcode.setOnClickListener {
            val intentIntegrator = IntentIntegrator(this@MainActivity)
            intentIntegrator.setBeepEnabled(false)
            intentIntegrator.setCameraId(0)
            intentIntegrator.setPrompt("SCAN")
            intentIntegrator.setBarcodeImageEnabled(false)
            intentIntegrator.initiateScan()
        }
    }
    override fun onActivityResult(
            requestCode: Int,
            resultCode: Int,
            data: Intent?
    ) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "cancelled", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("MainActivity", "Scanned")
                Toast.makeText(this, "Scanned -> " + result.contents, Toast.LENGTH_SHORT)
                        .show()
                textView.text = String.format("Scanned Result: %s", result)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}