package com.ketan.calculator_11july

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
// TODO how can i extend this app for accepting full line input , period problem , BACK problem
class MainActivity : AppCompatActivity() {
    var tv: TextView? = null;
    var isOperatorDone = false;
    var isLastDigit = false
    var isLastOperator = false
    var isLastPeriod = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv = findViewById(R.id.tv); // initialise in onCreate
    }

    fun onDigit(view : View){
        tv?.append((view as Button).text);
        isLastDigit = true;
        isLastOperator = false
        isLastPeriod = false
    }

    fun onPeriod(view: View){
        if(!tv?.text.toString().contains(".")){
            tv?.append(".")
            isLastPeriod = true
        }
        isLastOperator = false
    }

    fun onClear(view: View){ // writting this parameters is must ig
        tv?.text="";
        isLastOperator = false
        isLastPeriod = false
    }

    fun onBack(view: View){
            if(!(tv?.text?.length ==0)) {
                tv?.text = tv?.text!!.substring(0, tv?.text!!.length - 1)

                if(tv?.text.toString().endsWith(".")){
                    isLastPeriod = true
                }
            }
           // check for period
    }

    fun onOperator(view: View){
            if(isLastOperator){
                onBack(view)
                tv?.append((view as Button).text)
            }
            else if(!isLastOperator && !isOperatorInString(tv?.text.toString())) {
                tv?.append((view as Button).text)
                isLastDigit = false
                isLastOperator = true
            } else if(isOperatorInString(tv?.text.toString())){
                onEqual(view)
                tv?.append((view as Button).text)
                isLastOperator = true
            }
        isLastPeriod = false
    }

    fun onEqual(view: View){
        if(isLastOperator){
            tv?.text = tv?.text?.substring(0,tv?.text!!.length -1)
        }
        else if(tv?.text.toString().substring(1).contains("+")) {
            if (isOperatorInString(tv?.text.toString())) {
                var str = tv?.text.toString()
                var arrOfTwoNumbers = str.split("+");
                var one = ""
                var two = ""
                if(arrOfTwoNumbers.size==2){
                    one = arrOfTwoNumbers[0]
                    two = arrOfTwoNumbers[1]
                } else{ // let size is 3
                   var prefix = arrOfTwoNumbers[0]
                    one = arrOfTwoNumbers[1]
                    two = arrOfTwoNumbers[2]
                    one = "$prefix"+one
                }
                var result = one.toDouble() + two.toDouble()
                tv?.text = "${result.toString()}"
            }

        } else if(tv?.text.toString().substring(1).contains("-")) {
            if (isOperatorInString(tv?.text.toString())) {
                var str = tv?.text.toString()
                var arrOfTwoNumbers = str.split("-");
                var one = ""
                var two = ""
                if(arrOfTwoNumbers.size==2){
                     one = arrOfTwoNumbers[0]
                     two = arrOfTwoNumbers[1]
                } else{ // let size is 3
                    one = arrOfTwoNumbers[1]
                    two = arrOfTwoNumbers[2]
                    one = "-"+one
                }
                var result = one.toDouble() - two.toDouble()
                tv?.text = "${result.toString()}"
            }
        } else if(tv?.text.toString().substring(1).contains("*")) {
            if (isOperatorInString(tv?.text.toString())) {
                var str = tv?.text.toString()
                var arrOfTwoNumbers = str.split("*");
                var one = arrOfTwoNumbers[0]
                var two = arrOfTwoNumbers[1]
                var result = one.toDouble() * two.toDouble()
                tv?.text = "${result.toString()}"
            }
        } else if(tv?.text.toString().substring(1).contains("/")) {
            if (isOperatorInString(tv?.text.toString())) {
                var str = tv?.text.toString()
                var arrOfTwoNumbers = str.split("/");
                var one = arrOfTwoNumbers[0]
                var two = arrOfTwoNumbers[1]
                var result = one.toDouble() / two.toDouble()
                tv?.text = "${result.toString()}"
            }
        }
        if(tv?.text.toString().contains(".0")){
            tv?.text = tv?.text!!.substring(0,tv?.text?.length!! - 2)
        }
        isLastPeriod = false // it should be inside the if blocks as then only we can consider it
    }

    fun isOperatorInString(value:String):Boolean{ // we can accomodate only one operator at a time
        if(value.length==0) return false
        if(value.contains("+") || value.contains("*") || value.contains("/") || value.substring(1).contains("-")){
            isOperatorDone = true;
            return true;
        }
        return false;
    }

}

