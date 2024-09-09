package com.example.a7minworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.example.a7minworkout.databinding.ActivityBmiactivityBinding
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    companion object {
        private const val METRIC_UNIT_VIEW = "METRIC_UNIT_VIEW"
        private const val US_UNITS_VIEW = "US_UNIT_VIEW"
    }

    private var currentVisibleView: String = METRIC_UNIT_VIEW
    private var binding: ActivityBmiactivityBinding? = null

    // private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // auth = FirebaseAuth.getInstance()
        // get the current user
        // val currentUser = auth.currentUser
        // if (currentUser != null) {
        //     val uid = currentUser.uid
        //     // Now you can use uid to map the BMI data to this user
        // }

        setSupportActionBar(binding?.toolbarBmiActivity)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATE BMI"
        }
        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            onBackPressed()
        }

        makeVisibleMetricUnitsView()

        binding?.rgUnits?.setOnCheckedChangeListener { _, checkedId: Int ->
            if (checkedId == R.id.rbMetricUnits) {
                makeVisibleMetricUnitsView()
            } else {
                makeVisibleUSView()
            }
        }

        binding?.btnCalculateUnits?.setOnClickListener {
            if (currentVisibleView == METRIC_UNIT_VIEW) {
                calculateMetricUnits()
            } else {
                calculateUSUnits()
            }
        }
    }

    private fun makeVisibleMetricUnitsView() {
        currentVisibleView = METRIC_UNIT_VIEW
        binding?.tilMetricUnitWeight?.visibility = View.VISIBLE
        binding?.tilMetricUnitHeight?.visibility = View.VISIBLE
        binding?.tilUSMetricUnitWeight?.visibility = View.GONE
        binding?.tilMetricUnitHeightFeet?.visibility = View.GONE
        binding?.tilMetricUnitHeightInch?.visibility = View.GONE

        binding?.etMetricUnitHeight?.text!!.clear()
        binding?.eMetricUnitWeight?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun makeVisibleUSView() {
        currentVisibleView = US_UNITS_VIEW
        binding?.tilMetricUnitWeight?.visibility = View.GONE
        binding?.tilMetricUnitHeight?.visibility = View.GONE
        binding?.tilUSMetricUnitWeight?.visibility = View.VISIBLE
        binding?.tilMetricUnitHeightFeet?.visibility = View.VISIBLE
        binding?.tilMetricUnitHeightInch?.visibility = View.VISIBLE

        binding?.etUSMetricUnitWeight?.text!!.clear()
        binding?.etMetricUnitHeightFeet?.text!!.clear()
        binding?.etMetricUnitHeightInch?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun calculateMetricUnits() {
        if (validateMetricUnit()) {
            val heightValue: Float = binding?.etMetricUnitHeight?.text.toString().toFloat() / 100
            val weightValue: Float = binding?.eMetricUnitWeight?.text.toString().toFloat()
            val bmi = weightValue / (heightValue * heightValue)
            displayBmiResult(bmi)
        } else {
            // if invalid
            MotionToast.createToast(
                this@BMIActivity,
                "Failed ✗",
                "Please enter valid values",
                MotionToastStyle.ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this@BMIActivity, www.sanju.motiontoast.R.font.helveticabold)
            )
        }
    }

    private fun calculateUSUnits() {
        if (validateUSUnit()) {
            val usUnitHeightValueFeet: String = binding?.etMetricUnitHeightFeet?.text.toString()
            val usUnitHeightValueInch: String = binding?.etMetricUnitHeightInch?.text.toString()
            val usWeightValue: Float = binding?.etUSMetricUnitWeight?.text.toString().toFloat()

            val heightValue = usUnitHeightValueInch.toFloat() + usUnitHeightValueFeet.toFloat() * 12
            val bmi = 703 * (usWeightValue / (heightValue * heightValue))
            displayBmiResult(bmi)
        } else {
            // if invalid
            MotionToast.createToast(
                this@BMIActivity,
                "Failed ✗",
                "Please enter valid values",
                MotionToastStyle.ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this@BMIActivity, www.sanju.motiontoast.R.font.helveticabold)
            )
        }
    }

    private fun displayBmiResult(bmi: Float) {
        val bmiLabel: String
        val bmiDescription: String

        when {
            bmi <= 15f -> {
                bmiLabel = "Very severely underweight"
                bmiDescription = "OOPS!! You really need to take better care of yourself! Eat more"
            }
            bmi > 15f && bmi <= 16f -> {
                bmiLabel = "Severely underweight"
                bmiDescription = "You need to take better care of yourself! Eat more"
            }
            bmi > 16f && bmi <= 18.5f -> {
                bmiLabel = "Underweight"
                bmiDescription = "You need to gain some weight"
            }
            bmi > 18.5f && bmi <= 25f -> {
                bmiLabel = "Normal"
                bmiDescription = "You're doing great! Keep it up"
            }
            bmi > 25f && bmi <= 30f -> {
                bmiLabel = "Overweight"
                bmiDescription = "You need to lose some weight"
            }
            else -> {
                bmiLabel = "Obese"
                bmiDescription = "You really need to take care of yourself! Lose weight"
            }
        }

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        // save BMI data to Firebase
        // val currentUser = auth.currentUser
        // currentUser?.let {
        //     saveBmiToFirebase(it.uid, bmiValue, bmiLabel, bmiDescription)
        // }

        binding?.llDisplayBMIResult?.visibility = View.VISIBLE
        binding?.tvBMIValue?.text = bmiValue
        binding?.tvBMIType?.text = bmiLabel
        binding?.tvBMIDescription?.text = bmiDescription
    }

    // private fun saveBmiToFirebase(uid: String, bmiValue: String, bmiLabel: String, bmiDescription: String) {
    //     val firestore = FirebaseFirestore.getInstance()
    //     val bmiData = hashMapOf(
    //         "bmiValue" to bmiValue,
    //         "bmiLabel" to bmiLabel,
    //         "bmiDescription" to bmiDescription
    //     )
    //     firestore.collection("users").document(uid)
    //         .set(bmiData)
    //         .addOnSuccessListener {
    //             // Successfully saved data
    //             MotionToast.createToast(
    //                 this,
    //                 "Success",
    //                 "BMI data saved successfully",
    //                 MotionToastStyle.SUCCESS,
    //                 MotionToast.GRAVITY_BOTTOM,
    //                 MotionToast.SHORT_DURATION,
    //                 ResourcesCompat.getFont(this, www.sanju.motiontoast.R.font.helveticabold)
    //             )
    //         }
    //         .addOnFailureListener { e ->
    //             // Handle the error
    //             MotionToast.createToast(
    //                 this,
    //                 "Failed",
    //                 "Failed to save BMI data: ${e.message}",
    //                 MotionToastStyle.ERROR,
    //                 MotionToast.GRAVITY_BOTTOM,
    //                 MotionToast.SHORT_DURATION,
    //                 ResourcesCompat.getFont(this, www.sanju.motiontoast.R.font.helveticabold)
    //             )
    //         }
    // }

    private fun validateMetricUnit(): Boolean {
        return binding?.eMetricUnitWeight?.text.toString().isNotEmpty() &&
                binding?.etMetricUnitHeight?.text.toString().isNotEmpty()
    }

    private fun validateUSUnit(): Boolean {
        return binding?.etUSMetricUnitWeight?.text.toString().isNotEmpty() &&
                binding?.etMetricUnitHeightFeet?.text.toString().isNotEmpty() &&
                binding?.etMetricUnitHeightInch?.text.toString().isNotEmpty()
    }
}
