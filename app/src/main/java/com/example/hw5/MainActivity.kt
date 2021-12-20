package com.example.hw5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TaskCallbacks {

    companion object {
        const val RESULT = "RESULT"
    }

    private val verticalLinearLayoutManager: LinearLayoutManager =
        LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    private lateinit var binding: ActivityMainBinding
    private var fragment: MyFragment? = null
    private var myResult: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


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


    private fun update(mes: String) {
        binding.itemPerson.layoutManager = verticalLinearLayoutManager
        binding.itemPerson.adapter = Adapter(Holder.createCollection(mes))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(RESULT, myResult)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        fragment?.cancelTask()
    }

    override fun onPreExecuted() {
    }

    override fun onCanceled() {
        Log.d("TAG", "CANCELLED")
    }

    override fun onPostExecute(i: String) {
        myResult = i
        Log.d("TAG2", "MESSAGE =  $i")
        update(i)
    }
}