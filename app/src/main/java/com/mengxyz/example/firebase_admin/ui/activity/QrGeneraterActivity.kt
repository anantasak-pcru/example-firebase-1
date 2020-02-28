package com.mengxyz.example.firebase_admin.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.mengxyz.example.firebase_admin.R
import com.mengxyz.example.firebase_admin.util.getBitmapFromView
import com.mengxyz.example.firebase_admin.util.saveImage
import kotlinx.android.synthetic.main.activity_qr_generater.*


class QrGeneraterActivity : AppCompatActivity(),SeekBar.OnSeekBarChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_generater)

        s_height.setOnSeekBarChangeListener(this)
        s_width.setOnSeekBarChangeListener(this)
        s_width.progress = 300
        s_height.progress = 300

        e_content.setText("EE4XyzVUyEYOPvbitb6IJS9uYww1")

        b_generate.setOnClickListener {

            val multiFormatWriter = MultiFormatWriter()
            val endcode:Map<EncodeHintType,Any> = mapOf(
                EncodeHintType.MARGIN to 0
            )
            try {
                val bitMatrix =
                    multiFormatWriter.encode(
                        e_content.text.toString(),
                        BarcodeFormat.QR_CODE,
                        e_height.text.toString().toInt(),
                        e_width.text.toString().toInt(),
                        endcode
                    )
                val barcodeEncoder = BarcodeEncoder()
                val bitmap = barcodeEncoder.createBitmap(bitMatrix)
                vQrCode.setImageBitmap(bitmap)
            } catch (e: WriterException) {
                e.printStackTrace()
            }
        }

        b_save.setOnClickListener {
            getBitmapFromView(billing,this){
                saveImage(it,applicationContext,"billing")
            }
//            val intent = Intent(this,Scanner::class.java)
//            startActivityForResult(intent, 0)
        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        when(seekBar){
            s_height ->{
                e_height.setText(progress.toString())
            }
            s_width ->{
                e_width.setText(progress.toString())
            }
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

    override fun onStopTrackingTouch(seekBar: SeekBar?) {}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0 && resultCode == Activity.RESULT_OK){
            data?.let {
                data.getStringExtra("SCAN_RESULT")?.let {
                    Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}