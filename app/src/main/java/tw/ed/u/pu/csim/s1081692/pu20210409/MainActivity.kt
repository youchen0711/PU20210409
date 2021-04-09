package tw.ed.u.pu.csim.s1081692.pu20210409

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),GestureDetector.OnGestureListener, View.OnTouchListener {
    lateinit var gDetector: GestureDetector
    var PictureNo:Int = 0  //目前顯示第幾張圖
    var TotalPictures:Int = 5 //總共幾張圖片(假設僅顯示pu0-pu3)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gDetector = GestureDetector(this, this)
        image.setOnTouchListener(this)

        var res:Int = -1
        var countDrawables:Int = -1
        while (res != 0) {
            countDrawables++;
            res = getResources().getIdentifier("pu" + (countDrawables),
                    "drawable", getPackageName());
        }
        TotalPictures = countDrawables



        txv.text = "作者：賴侑辰"
    }

       /* override fun onTouchEvent(event: MotionEvent?): Boolean {
            /*if (txv.text == "靜宜之美"){
                txv.text = "螢幕觸控"
            }
            else{
                txv.text = "靜宜之美"
            }

            txv.text = "x："+event?.x.toString()+"    "+"y："+event?.y.toString()

             */
            gDetector.onTouchEvent(event)
            return true


            return true
        }

        */

    override fun onShowPress(e: MotionEvent?) {
        //txv.text = "按下後無後續動作"

    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        //txv.text = "短按"
        PictureNo = 0
        ShowPicture()
        return true

    }

    override fun onDown(e: MotionEvent?): Boolean {
        //txv.text = "按下"
        return true

    }

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        /*txv.text = "快滑\nx1y1: " + e1.getX().toString() + ", " + e1.getY().toString() +
                "\nx2y2: " + e2.getX().toString() + ", " + e2.getY().toString() +
                "\nX軸Y軸速度:" + velocityX.toString() + ", " +  velocityY.toString()
         */
        if (e1.getX() < e2.getX()){  //向右快滑
            PictureNo++
            if (PictureNo == TotalPictures) {PictureNo = 0}
        }
        else{     //向左快滑
            PictureNo--;
            if (PictureNo < 0) {PictureNo = TotalPictures - 1 }
        }
        ShowPicture()
        return true
    }


    override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
       /*txv.text = "拖曳\nx1y1: " +  e1.getX().toString() + ", " + e1.getY().toString() +
                "\nx2y2: " + e2.getX().toString() + ", " + e2.getY().toString()
        */
        return true
    }


    override fun onLongPress(e: MotionEvent?) {
        //txv.text = "長按"
        PictureNo = TotalPictures - 1
        ShowPicture()
    }

    override fun onTouch(p0: View?, event: MotionEvent?): Boolean {
        //gDetector.onTouchEvent(event)
        gDetector.onTouchEvent(event)
        return true
    }

    fun ShowPicture(){
        /*when (PictureNo){
            0 -> img.setImageResource(R.drawable.pu0)
            1 -> img.setImageResource(R.drawable.pu1)
            2 -> img.setImageResource(R.drawable.pu2)
            3 -> img.setImageResource(R.drawable.pu3)
            }
      */
        var res:Int = getResources().getIdentifier("pu" + (PictureNo),
                "drawable", getPackageName())
        img.setImageResource(res)

        txv.text = PictureNo.toString()
    }
}