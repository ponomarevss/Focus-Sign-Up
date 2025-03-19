package ru.sspo.focussignup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.sspo.focussignup.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val signUpFragment = SignUpFragment()
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, signUpFragment)
                .commit()
        }
    }
}