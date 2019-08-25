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
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private val PERMISSION_CODE: Int = 1000
    private val IMAGE_CAPTURE_CODE: Int = 1001
    private val IMAGE_PICK_CODE: Int = 1002
    var ImgId : Int = 0
    var image_uri: Uri? = null
    val listX: MutableList<Int> = ArrayList()
    val listR: MutableList<Int> = ArrayList()
    val listG: MutableList<Int> = ArrayList()
    val listB: MutableList<Int> = ArrayList()
    val lst: MutableList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener(){

            Log.i("Button", "Calculating result...");
            calc()

            for(i in lst) {
                Log.i("lstkey","i : $i")
            }

            var i = Intent(this@MainActivity, Main2Activity::class.java)
            i.putExtra("Result",lst.toIntArray())
            println("size of the array is "+lst.toIntArray().size)
            startActivity(i)
        }

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
        getRcb()
    }

    fun list1add(Id : Int){
        when(Id){
            img1.id -> listX += 1
            img2.id -> listX += 2
            img3.id -> listX += 3
            img4.id -> listX += 4
            img5.id -> listX += 5
            img6.id -> listX += 6
            img7.id -> listX += 7
            img8.id -> listX += 8
        }
    }



    fun getRcb() {

        // val bitmap = BitmapFactory.decodeFile(image1)
        //Setting up converted bitmap image inside imageview.
        // image1.setImageBitmap(bitmap)
        //BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable()
        var Id: Int = 0
        for (i in listX) {
            when (i) {
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
            val bitmap = (ImgView.getDrawable() as BitmapDrawable).getBitmap()
            ImgView.setImageBitmap(bitmap)

            val width = bitmap.getWidth()
            val height = bitmap.getHeight()

            val centerX = width / 2
            val centerY = height / 2

            val colour = bitmap.getPixel(centerX, centerY)

            val red = Color.red(colour)
            val green = Color.green(colour)
            val blue = Color.blue(colour)
            if( colour == Color.RED){
                Log.i("color","It is red")
            }
            Log.i("color", "R:$red")
            Log.i("color", "G:$green")
            Log.i("color", "B:$blue")
            when (i) {
                1 -> text1.text = "R:$red, G:$green, B:$blue"
                2 -> text2.text = "R:$red, G:$green, B:$blue"
                3 -> text3.text = "R:$red, G:$green, B:$blue"
                4 -> text4.text = "R:$red, G:$green, B:$blue"
                5 -> text5.text = "R:$red, G:$green, B:$blue"
                6 -> text6.text = "R:$red, G:$green, B:$blue"
                7 -> text7.text = "R:$red, G:$green, B:$blue"
                8 -> text8.text = "R:$red, G:$green, B:$blue"
            }
            listR += red
            listG += green
            listB += blue
        }
    }

    fun lstadd(lst1 : MutableList<Int>){
        for (i in lst1){
            lst.add(i)
        }
    }

    fun calc(){

        val arR :IntArray = listR.toIntArray()
        val arG :IntArray = listG.toIntArray()
        val arB :IntArray = listB.toIntArray()
        val arX :IntArray = listX.toIntArray()

        val lstR: MutableList<Int> = calculate(arX,arR)
        lstadd(lstR)

        val lstG: MutableList<Int> = calculate(arX,arG)
        lstadd(lstG)

        val lstB: MutableList<Int> = calculate(arX,arB)
        lstadd(lstB)
    }

    fun calculate(arr1:IntArray, arr2 : IntArray): MutableList<Int>{
//        var arr1: IntArray = intArrayOf(1, 2, 3, 4, 5)
//        var arr2: IntArray = intArrayOf(651, 762, 856, 1063, 1190)
        val llist : MutableList<Int> = ArrayList()
        val xbar = arr1.average().toInt()
        val ybar = arr2.average().toInt()
        var arr3: IntArray = delC(arr1,xbar)
        var arr6 = delC(arr2,ybar)
        val arr4: IntArray = proArr(arr3,arr6)
        val sum1 : Int = arr4.sum()
        arr3 = sqrArr(arr3)
        val sum2 = arr3.sum()
        val m = (sum1.toFloat()/sum2.toFloat()).toInt()
        val c = ybar - m * xbar

        val size = arr1.count()
        var arr5 = IntArray(size)
        for (i in 0..size - 1) {
            arr5[i] = c + m * arr1[i]
        }
        arr5 = delC(arr5,ybar)
        arr5 = sqrArr(arr5)
        arr6 = sqrArr(arr6)
        val sum3 = arr5.sum()
        val sum4 = arr6.sum()
        val acc = ((sum3.toFloat() / sum4.toFloat())*100).toInt()
        println("sum1 : "+sum1)
        println("sum2 : "+sum2)
        println("sum3 : "+sum3)
        println("sum4 :"+sum4)
        println("m = "+ m)
        println("c = "+c)
        println("Accuracy = "+acc)
        val y = m * 6 + c
        println("y : "+y)
        println("y9 : "+((m*9)+c))
        println("y8 : "+((m*8)+c))
        println("y7 : "+((m*7)+c))
        println("y6 : "+((m*6)+c))
        println("y3 : "+((m*3)+c))
        println("y1 : "+((m*1)+c))

        llist.add(m)
        llist.add(c)
        llist.add(acc)
        return llist

    }

    fun delC(Arr1: IntArray, c:Int):IntArray{
        val size = Arr1.count()
        val sum = IntArray(size)
        for (i in 0..size - 1) {
            sum[i] = Arr1[i] - c
        }
        return sum
    }
    fun proArr(Arr1:IntArray, Arr2:IntArray):IntArray{
        val size = Arr1.count()
        val pro = IntArray(size)
        for (i in 0..size - 1) {
            pro[i] = Arr1[i] * Arr2[i]
        }
        return pro

    }
//    fun sumArr(Arr1:IntArray, Arr2:IntArray):IntArray{
//        val size = Arr1.count()
//        val sum = IntArray(size)
//        for (i in 0..size - 1) {
//            sum[i] = Arr1[i] + Arr2[i]
//        }
//        return sum


    fun sqrArr(Arr1:IntArray):IntArray{
        val size = Arr1.count()
        val sum = IntArray(size)
        for (i in 0..size - 1) {
            sum[i] = Arr1[i] * Arr1[i]
        }
        return sum
    }
}