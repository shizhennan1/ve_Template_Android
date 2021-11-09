package com.volcengine.mars.demo.h5

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.falconx.debug.WebOfflineAnalyze
import com.bytedance.veh5.debug.DebugTools
import kotlinx.android.synthetic.main.activity_offline_record.*

class OfflineRecordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offline_record)

        buildUi()
    }

    private fun buildUi() {

        if (DebugTools.getOfflineResourcesLoadRecord().isEmpty()) {
            emptyView.visibility = View.VISIBLE
            return
        }

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = RecordAdapter(WebOfflineAnalyze.getMatchResult())
    }

    private class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val urlTv: TextView = itemView.findViewById(R.id.url)

        val msgTv: TextView = itemView.findViewById(R.id.msg)
    }

    private inner class RecordAdapter(private val data: MutableList<WebOfflineAnalyze.MatchResult>) : RecyclerView.Adapter<Holder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val itemView = LayoutInflater.from(this@OfflineRecordActivity)
                .inflate(R.layout.item_record, parent, false)
            return Holder(itemView)
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            val matchResult = data[position]

            val text = if (matchResult.match) {
                if (matchResult.version == (-1).toLong()) "[内置离线] "
                else "[离线]"
            } else {
                "[在线] "
            }
            val spannableText = SpannableString(text + matchResult.url)
            val span = StyleSpan(Typeface.BOLD)
            spannableText.setSpan(span, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            holder.urlTv.text = spannableText
            holder.msgTv.text = matchResult.msg
        }

        override fun getItemCount(): Int = data.size


    }
}