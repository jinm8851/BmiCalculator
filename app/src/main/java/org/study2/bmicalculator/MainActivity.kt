package org.study2.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import org.study2.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadData()

        binding.resultButton.setOnClickListener {
            if (binding.weightEditText.text.isNotBlank() && binding.heightEditText.text.isNotBlank()) {

//                결과버튼을 클릭할때 화면을 전환하기전에  saveData() 메서드를 호출하여 저장
                saveData(
                    binding.heightEditText.text.toString().toFloat(),
                    binding.weightEditText.text.toString().toFloat()
                )
                val intent = Intent(this, ResultActivity::class.java).apply {
                    putExtra("weight", binding.weightEditText.text.toString().toFloat())
                    putExtra("height", binding.heightEditText.text.toString().toFloat())
                }
                startActivity(intent)
            }

        }
    }

    //    데이터 저장코드
    private fun saveData(height: Float, weight: Float) {
//    프리퍼렌스메니져를 통해 프리퍼런스 객체를 생성
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
//    프리러런스 객체의 에디터 객체를 생성 (데이터를 담을수있음)
        val editor = pref.edit()

        editor.putFloat("KEY_HEIGHT", height)
            .putFloat("KEY_WEIGHT", weight)
            .apply()

    }

//    저장한 데이터 불러오기
    private fun loadData() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
    val height = pref.getFloat("KEY_HEIGHT", 0f)
    val weight = pref.getFloat("KEY_WEIGHT", 0f)

    if (height != 0f && weight != 0f) {
        binding.heightEditText.setText(height.toString())
        binding.weightEditText.setText(weight.toString())
    }
    }
}