package com.sibuk.startup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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

        val final_list :ArrayList<Int> = intent.getIntArrayExtra("Result").toCollection(ArrayList())
//        val final_list :ArrayList<Int> = ArrayList()

        rm = final_list[0]
        rc = final_list[1]
        ra = final_list[2]

        bm = final_list[3]
        bc = final_list[4]
        ba = final_list[5]

        gm = final_list[6]
        gc = final_list[7]
        ga = final_list[8]

        textView2.text = "Slope : " + rm.toString()
        textView3.text ="Intercept : " + rc.toString()
        textView.text = "Accuracy : " + ra.toString()

        textView5.text = "Slope : " + bm.toString()
        textView6.text = "Intercept : " +  bc.toString()
        textView11.text = "Accuracy : " + ba.toString()

        textView8.text = "Slope : " + gm.toString()
        textView9.text = "Intercept : " +  gc.toString()
        textView10.text = "Accuracy : " + ga.toString()

        button1.setOnClickListener(){



        }
    }
}
