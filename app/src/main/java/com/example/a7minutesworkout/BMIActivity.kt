package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {
    companion object{
        private const val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
        private const val US_UNITS_VIEW = "US_UNIT_VIEW"
    }

    private var currentVisibleView: String = METRIC_UNITS_VIEW
    private var binding: ActivityBmiBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBmiActivity)
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "Calculate BMI"
        }
        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            onBackPressed()
        }

        makeVisibleMetricUnitsView()

        binding?.rgUnits?.setOnCheckedChangeListener{_, checkedId: Int ->
            if(checkedId == R.id.rbMTUnits){
                makeVisibleMetricUnitsView()
            }
            else{
                makeVisibleUSUnitsView()
            }
        }
        binding?.btnCalculate?.setOnClickListener{
            calculationBmi()
        }
    }

    private fun validateMetricUnits(): Boolean{
        var isValid = true
        if(binding?.etWeight?.text.toString().isEmpty()){
            isValid = false
        }
        else if(binding?.etHeight?.text.toString().isEmpty()){
            isValid = false
        }

        return isValid
    }

    private fun validateUSUnits(): Boolean{
        var isValid = true
        when{
            binding?.etWeightPound?.text.toString().isEmpty() ->{
                isValid = false
            }
            binding?.etHeightFeet?.text.toString().isEmpty() -> {
                isValid = false
            }
            binding?.etHeightInch?.text.toString().isEmpty() ->{
                isValid = false
            }
        }

        return isValid
    }

    private fun displayBMIResult(bmi: Float){
        val bmiLabel: String
        val bmiDescription: String

        if(bmi.compareTo(15f) <= 0 ){
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        }
        else if(bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0){
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        }
        else if(bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0){
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        }
        else if(bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0){
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape"
        }
        else if(bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0){
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of yourself!Workout and eat healthy!"
        }
        else if(bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0){
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of yourself!Workout and eat healthy!"
        }
        else if(bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0){
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in very dangerous condition! Act now!"
        }
        else{
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in very dangerous condition! Act now!"
        }

        var bmiValue = BigDecimal(bmi.toDouble()).setScale(2,RoundingMode.HALF_EVEN).toString()
        binding?.llInfoText?.visibility = View.VISIBLE
        binding?.tvBMIResult?.text = bmiValue
        binding?.tvResultWeight?.text = bmiLabel
        binding?.tvResultText?.text = bmiDescription
    }

    private fun calculationBmi(){
        var bmi: Float?=null
        if(currentVisibleView == METRIC_UNITS_VIEW && validateMetricUnits()){
            val heightValue: Float = binding?.etHeight?.text.toString().toFloat() / 100
            val weightValue: Float = binding?.etWeight?.text.toString().toFloat()

            bmi = weightValue / (heightValue * heightValue)
            displayBMIResult(bmi)
        }
        else if(currentVisibleView == US_UNITS_VIEW && validateUSUnits()){
            val heightInchValue: Float = binding?.etHeightInch?.text.toString().toFloat()
            val heightFeetValue: Float = binding?.etHeightFeet?.text.toString().toFloat()
            val weightPoundValue: Float = binding?.etWeightPound?.text.toString().toFloat()
            val heightValue = heightInchValue + heightFeetValue * 12

            if(heightValue != null){
                bmi = 703 * (weightPoundValue / (heightValue * heightValue))
            }

            if(bmi != null){
                displayBMIResult(bmi)
            }
        }
        else{
            Toast.makeText(this@BMIActivity,
                "Please enter valid values.",
                Toast.LENGTH_SHORT).show()
        }
    }


    private fun makeVisibleMetricUnitsView(){
        currentVisibleView = METRIC_UNITS_VIEW

        binding?.tilWeight?.visibility = View.VISIBLE
        binding?.tilHeight?.visibility = View.VISIBLE

        binding?.tilWeightPound?.visibility = View.GONE
        binding?.tilHeightFeet?.visibility = View.GONE
        binding?.tilHeightInch?.visibility = View.GONE

        binding?.etWeight?.text!!.clear()
        binding?.etHeight?.text!!.clear()

        binding?.llInfoText?.visibility = View.INVISIBLE
    }

    private fun makeVisibleUSUnitsView(){
        currentVisibleView = US_UNITS_VIEW

        binding?.tilWeight?.visibility = View.INVISIBLE
        binding?.tilHeight?.visibility = View.INVISIBLE

        binding?.tilWeightPound?.visibility = View.VISIBLE
        binding?.tilHeightFeet?.visibility = View.VISIBLE
        binding?.tilHeightInch?.visibility = View.VISIBLE

        binding?.etWeightPound?.text!!.clear()
        binding?.etHeightFeet?.text!!.clear()
        binding?.etHeightInch?.text!!.clear()

        binding?.llInfoText?.visibility = View.INVISIBLE
    }
}