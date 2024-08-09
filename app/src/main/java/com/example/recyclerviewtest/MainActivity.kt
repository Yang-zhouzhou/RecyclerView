package com.example.recyclerviewtest

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewtest.ui.theme.Adapter
import com.example.recyclerviewtest.ui.theme.Item
import com.example.recyclerviewtest.ui.theme.RecyclerViewTestTheme

const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {

    private val adapter = Adapter(getData())

    private var count = 0

    private val rv by lazy {
        findViewById<RecyclerView>(R.id.rv)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpRv()
        setUpBtn()
    }

    private fun setUpRv() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        rv.adapter = adapter
        rv.layoutManager = layoutManager
    }

    private fun setUpBtn() {
        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener {
            val data = getData()
            adapter.setData(data)
            adapter.notifyDataSetChanged()
            count++
        }
    }

    private fun getData(): List<Item> {
        val first10Items = (0..5).map {
            Item(it, "$it")
        }
        val last10Items = (6..15).map {
            Item(it, "$it" + "_".repeat(count))
        }
        return first10Items + last10Items
    }
}