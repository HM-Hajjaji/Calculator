package com.example.calculator

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
//import javax.script.*
//import org.jetbrains.kotlin.script.util.*
//import kotlin.script.experimental.annotations.KotlinScript
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var btnClear: Button
    private lateinit var btnPercent: Button
    private lateinit var btnBackspace: Button
    private lateinit var btnDivision: Button
    private lateinit var btnSeven: Button
    private lateinit var btnEight: Button
    private lateinit var btnNine: Button
    private lateinit var btnMultiplication: Button
    private lateinit var btnFour: Button
    private lateinit var btnFive: Button
    private lateinit var btnSix: Button
    private lateinit var btnSubtract: Button
    private lateinit var btnOne: Button
    private lateinit var btnTwo: Button
    private lateinit var btnThree: Button
    private lateinit var btnPlus: Button
    private lateinit var btnDoubleZero: Button
    private lateinit var btnZero: Button
    private lateinit var btnPoint: Button
    private lateinit var btnEqual: Button
    private lateinit var txtResult: TextView
    private lateinit var txtSubResult: TextView
    private var value:String = ""
    private var isPoint:Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.initialized()
        this.handelClickButtonText()
        this.handelClickButtonOperators()
        this.clear(this.btnClear)
        this.backspace(this.btnBackspace)
        this.point(this.btnPoint)
        this.equal()
    }

    fun initialized(){
        this.btnClear = findViewById(R.id.btn_clear)
        this.btnPercent = findViewById(R.id.btn_percent)
        this.btnBackspace = findViewById(R.id.btn_backspace)
        this.btnDivision = findViewById(R.id.btn_division)
        this.btnSeven = findViewById(R.id.btn_seven)
        this.btnEight = findViewById(R.id.btn_eight)
        this.btnNine = findViewById(R.id.btn_nine)
        this.btnMultiplication = findViewById(R.id.btn_multiplication)
        this.btnFour = findViewById(R.id.btn_four)
        this.btnFive = findViewById(R.id.btn_five)
        this.btnSix = findViewById(R.id.btn_six)
        this.btnSubtract = findViewById(R.id.btn_subtract)
        this.btnOne = findViewById(R.id.btn_one)
        this.btnTwo = findViewById(R.id.btn_two)
        this.btnThree = findViewById(R.id.btn_three)
        this.btnPlus = findViewById(R.id.btn_plus)
        this.btnDoubleZero = findViewById(R.id.btn_double_zero)
        this.btnZero = findViewById(R.id.btn_zero)
        this.btnPoint = findViewById(R.id.btn_point)
        this.btnEqual = findViewById(R.id.btn_equal)
        this.txtResult = findViewById(R.id.txt_result)
        this.txtSubResult = findViewById(R.id.txt_sub_result)
    }
    fun handelClickButtonText()
    {
        val listButtons = listOf(this.btnOne,this.btnTwo,this.btnThree,this.btnFour,this.btnFive,this.btnSix,this.btnSeven,this.btnEight,this.btnNine,this.btnZero,this.btnDoubleZero)
        for (btn in listButtons)
        {
            btn.setOnClickListener {
                this.value+=btn.text.toString()
                this.affectValue()
            }
        }
    }

    fun handelClickButtonOperators()
    {
        val listButtons = listOf(this.btnPercent,this.btnDivision,this.btnMultiplication,this.btnSubtract,this.btnPlus)
        for (btn in listButtons)
        {
            btn.setOnClickListener {
                this.value+=btn.text.toString()
                this.isPoint = true
                this.affectValue()
            }
        }
    }

    fun clear(btn:Button){
        btn.setOnClickListener {
            this.value = ""
            this.txtResult.text ="0"
            this.txtSubResult.text = "0"
            this.isPoint = true
        }
    }
    fun backspace(btn: Button){
        btn.setOnClickListener {
            this.value = this.value.dropLast(1)
            if(this.value.length < 1){
                this.value=""
                this.txtResult.text = "0"
                this.txtSubResult.text = "0"
            }else{
                this.affectValue()
            }
        }
    }
    fun point(btn: Button){
        btn.setOnClickListener {
            if (this.isPoint)
            {
                this.value+=btn.text
            }
            if (this.value == ".")
            {
                this.value = "0."
            }
            this.isPoint = false
            this.affectValue()
        }
    }

    fun affectValue(){
        this.txtResult.text=this.value
    }
    fun equal(){
        this.btnEqual.setOnClickListener {
            this.txtSubResult.text = this.eval(this.value)
        }
    }
    fun eval(expression : String):String{
        return DecimalFormat("#,##0.00").format(ExpressionBuilder(expression).build().evaluate()).toString()
    }
}