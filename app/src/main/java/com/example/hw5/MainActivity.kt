package com.example.hw5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.core.view.isVisible
import com.example.hw5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TaskCallbacks {

    companion object {
        const val PROGRESS_IS_SHOWING = "PROGRESS_IS_SHOWING"
        const val RESULT = "RESULT"
    }

    private lateinit var binding: ActivityMainBinding
    private var fragment: MyFragment? = null
    private var myResult: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        savedInstanceState?.getBoolean(PROGRESS_IS_SHOWING)?.let {
            showProgress(it)
        }

        savedInstanceState?.getInt(RESULT)?.let {
            Log.d("MY TAG", "RESTORE STATE = $it")
        }

        val fm = supportFragmentManager
        val oldFragment = fm.findFragmentByTag(MyFragment.TAG)
        if (oldFragment == null) {
            fragment = MyFragment()
            fm
                .beginTransaction()
                .add(fragment!!, MyFragment.TAG)
                .commit()
        } else {
            fragment = oldFragment as MyFragment
        }

        fragment?.startTask()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(PROGRESS_IS_SHOWING, binding.progress.isVisible)
        outState.putInt(RESULT, myResult)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        showProgress(false)
        fragment?.cancelTask()
    }

    private fun showProgress(show: Boolean) {
        binding.progress.isVisible = show
    }

    override fun onPreExecuted() {
        showProgress(true)
    }

    override fun onCanceled() {
        Log.d("MY TAG", "CANCELLED")
    }

    override fun onPostExecute(i: Int) {
        myResult = i
        Log.d("MY TAG", "MESSAGE = $i")
    }
}