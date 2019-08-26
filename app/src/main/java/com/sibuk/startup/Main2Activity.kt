package com.sibuk.startup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    var rm: Int = 0
    var gm: Int = 0
    var bm: Int = 0

    var rc: Int = 0;
    var gc: Int = 0;
    var bc: Int = 0;

    var ra: Int = 0;
    var ga: Int = 0;
    var ba: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val final_list: ArrayList<Int> = intent.getIntArrayExtra("Result").toCollection(ArrayList())
//        val final_list :ArrayList<Int> = ArrayList()

        rm = final_list[0]
        rc = final_list[1]
        ra = final_list[2]

        gm = final_list[3]
        gc = final_list[4]
        ga = final_list[5]

        bm = final_list[6]
        bc = final_list[7]
        ba = final_list[8]

        textView2.text = "Slope : " + rm.toString()
        textView3.text = "Intercept : " + rc.toString()
        textView.text = "Accuracy : " + ra.toString()

        textView5.text = "Slope : " + gm.toString()
        textView6.text = "Intercept : " + gc.toString()
        textView11.text = "Accuracy : " + ga.toString()

        textView8.text = "Slope : " + bm.toString()
        textView9.text = "Intercept : " + bc.toString()
        textView10.text = "Accuracy : " + ba.toString()

        buttonYR.setOnClickListener() {
            val n : Int = editText1.text.toString().toInt()
            val n2 : Int = n * rm + rc
            editText2.text = Editable.Factory.getInstance().newEditable("Y : $n2")
        }
        buttonXR.setOnClickListener() {
            val n : Int = editText2.text.toString().toInt()
            if(rm == 0){
                editText1.text = Editable.Factory.getInstance().newEditable("X : NULL")
            }
            else {
                val n2: Int = (n - rc) / rm
                editText1.text = Editable.Factory.getInstance().newEditable("X : $n2")
            }
        }
        buttonYG.setOnClickListener() {
            val n : Int = editText3.text.toString().toInt()
            val n2 : Int = n * gm + gc
            editText4.text = Editable.Factory.getInstance().newEditable("Y : $n2")
        }
        buttonXG.setOnClickListener() {
            val n : Int = editText4.text.toString().toInt()
            if(gm == 0){
                editText3.text = Editable.Factory.getInstance().newEditable("X : NULL")
            }
            else {
                val n2: Int = (n - gc) / gm
                editText3.text = Editable.Factory.getInstance().newEditable("X : $n2")
            }
        }
        buttonYB.setOnClickListener() {
            val n : Int = editText5.text.toString().toInt()
            val n2 : Int = n * bm + bc
            editText6.text = Editable.Factory.getInstance().newEditable("Y : $n2")
        }
        buttonXB.setOnClickListener() {
            val n : Int = editText6.text.toString().toInt()
            if(bm == 0){
                editText5.text = Editable.Factory.getInstance().newEditable("X : NULL")
            }
            else {
                val n2: Int = (n - bc) / bm
                editText5.text = Editable.Factory.getInstance().newEditable("X : $n2")
            }
        }
    }
}
