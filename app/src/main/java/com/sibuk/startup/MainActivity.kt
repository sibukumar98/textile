package com.sibuk.startup

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private val PERMISSION_CODE: Int = 1000
    private val IMAGE_CAPTURE_CODE: Int = 1001
    private val IMAGE_PICK_CODE: Int = 1002
    var ImgId : Int = 0
    var image_uri: Uri? = null
    val list1: MutableList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





    }

    fun imgClick(view: View) {
        val popup = PopupMenu(this, view)
        ImgId = view.id
        list1add(ImgId)
        popup.inflate(R.menu.option)
        popup.setOnMenuItemClickListener {

            if (it.title == "Camera") {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(android.Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_DENIED ||
                        checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_DENIED
                    ) {
                        //permission denied
                        val permission = arrayOf(
                            android.Manifest.permission.CAMERA,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                        )

                        //popup for granting permission
                        requestPermissions(permission, PERMISSION_CODE)
                    } else {
                        //permission granted
                        openCamera()
                    }
                } else {
                    openCamera()
                }
                // Toast.makeText(this,"hiii", Toast.LENGTH_LONG).show()
            }
            if (it.title == "Gallery") {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_DENIED
                    ) {
                        //permission denied
                        val permission =
                            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)

                        //popup for granting permission
                        requestPermissions(permission, PERMISSION_CODE)
                    } else {
                        //permission granted
                        pickImageFromGallary()
                    }
                } else {
                    // os is less than marshmallow
                    pickImageFromGallary()
                }

                //Toast.makeText(this,"hello", Toast.LENGTH_LONG).show()
            }

            true

        }
        popup.show()
    }

    private fun pickImageFromGallary() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }


    private fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the camera")
        image_uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        //camera intent
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    //permission granted from popup
                } else {
                    //permisssion denied
                    Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val ImgView = findViewById<ImageView>(ImgId)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_CAPTURE_CODE) {
            ImgView.setImageURI(image_uri)
        }
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            ImgView.setImageURI(data?.data)
        }
    }

    fun list1add(Id : Int){
        when(Id){
            img1.id -> list1 += 1
            img2.id -> list1 += 2
            img3.id -> list1 += 3
            img4.id -> list1 += 4
            img5.id -> list1 += 5
            img6.id -> list1 += 6
            img7.id -> list1 += 7
            img8.id -> list1 += 8
        }

    }

    fun getRcb(view :View) {

        // val bitmap = BitmapFactory.decodeFile(image1)
        //Setting up converted bitmap image inside imageview.
        // image1.setImageBitmap(bitmap)
        //BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable()
        var Id : Int = 0
        for (i in list1){
            when(i){
                1 -> Id = img1.id
                2 -> Id = img2.id
                3 -> Id = img3.id
                4 -> Id = img4.id
                5 -> Id = img5.id
                6 -> Id = img6.id
                7 -> Id = img7.id
                8 -> Id = img8.id
            }
            val ImgView = findViewById<ImageView>(Id)
            var bitmap = (ImgView.getDrawable() as BitmapDrawable).getBitmap()
            ImgView.setImageBitmap(bitmap)


            val width = bitmap.getWidth()
            val height = bitmap.getHeight()

            val centerX = width / 2
            val centerY = height / 2

            val colour = bitmap.getPixel(centerX, centerY)

            val red = Color.red(colour)
            val blue = Color.blue(colour)
            val green = Color.green(colour)
            val alpha = Color.alpha(colour)
            when(i){
                1 -> text1.text = " R: "+ red
                2 -> text1.text = " R: "+ red
                3 -> text1.text = " R: "+ red
                4 -> text1.text = " R: "+ red
                5 ->text1.text = " R: "+ red
                6 -> text1.text = " R: "+ red
                7 -> text1.text = " R: "+ red
                8 -> text1.text = " R: "+ red
            }

        }
        val ImgView = findViewById<ImageView>(Id)
        var bitmap = (ImgView.getDrawable() as BitmapDrawable).getBitmap()
        ImgView.setImageBitmap(bitmap)


        val width = bitmap.getWidth()
        val height = bitmap.getHeight()

        val centerX = width / 2
        val centerY = height / 2

        val colour = bitmap.getPixel(centerX, centerY)

        val red = Color.red(colour)
        val blue = Color.blue(colour)
        val green = Color.green(colour)
        val alpha = Color.alpha(colour)

        Log.i("color", " " + red)


        Toast.makeText(
            getApplicationContext(), " " + red + " " + blue + " " + green + " " + alpha,
            Toast.LENGTH_SHORT
        ).show()


    }

}
