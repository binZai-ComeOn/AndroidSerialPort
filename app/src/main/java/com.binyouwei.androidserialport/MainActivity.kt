package com.binyouwei.androidserialport

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.binyouwei.androidserialport.adapter.MessageAdapter
import com.binyouwei.androidserialport.databinding.ActivityMainBinding
import com.binyouwei.androidserialport.manage.ConfigManage
import com.binyouwei.androidserialport.manage.ConfigManage.parity
import com.binyouwei.androidserialport.utils.DisplayUtil
import com.blankj.utilcode.util.ConvertUtils
import me.f1reking.serialportlib.SerialPortHelper
import me.f1reking.serialportlib.listener.IOpenSerialPortListener
import me.f1reking.serialportlib.listener.ISerialPortDataListener
import me.f1reking.serialportlib.listener.Status
import me.f1reking.serialportlib.manager.SerialPortDataManage


class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var inflate: ActivityMainBinding
    lateinit var messageAdapter: MessageAdapter

    var mSerialPortHelper = SerialPortHelper()
    lateinit var parityList: Array<String>
    lateinit var flowBitsList: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflate = ActivityMainBinding.inflate(layoutInflater)
        setContentView(inflate.root)

        initUI()
        parityList = SerialPortDataManage.parity.keys.toTypedArray()
        flowBitsList = SerialPortDataManage.flow_bits.keys.toTypedArray()
    }

    private fun openSerial() {
        mSerialPortHelper.port = ConfigManage.serialPort
        mSerialPortHelper.baudRate = ConfigManage.baudRate.toInt()
        mSerialPortHelper.dataBits = ConfigManage.dataBits.toInt()
        mSerialPortHelper.parity = SerialPortDataManage.parity[ConfigManage.parity]!!.toInt()
        mSerialPortHelper.stopBits = ConfigManage.stopBits.toInt()
        mSerialPortHelper.flowCon = SerialPortDataManage.flow_bits[ConfigManage.flowBits]!!.toInt()
        mSerialPortHelper.setISerialPortDataListener(object : ISerialPortDataListener {
            override fun onDataReceived(bytes: ByteArray?) {
                byteToHex(1, bytes)
            }

            override fun onDataSend(bytes: ByteArray?) {
                byteToHex(0, bytes)
            }

        })
       /* mSerialPortHelper.setIOpenSerialPortListener(object : IOpenSerialPortListener {
            override fun onSuccess(device: File?) {
                runOnUiThread {
                    inflate.sendInformation.visibility = View.VISIBLE
                    setText(inflate.open, "关闭串口")
                    showToast("串口打开成功")
                }
            }

            override fun onFail(device: File?, status: Status?) {
                runOnUiThread {
                    showToast("串口打开失败")
                }
            }

        })*/
        if (mSerialPortHelper.open()) {
            inflate.sendInformation.visibility = View.VISIBLE
            setText(inflate.open, "关闭串口")
            showToast("串口打开成功")
        } else {
            showToast("串口打开失败")
        }
    }

    private fun byteToHex(i: Int, bytes: ByteArray?) {
        runOnUiThread {
            if (i == 0) {
                messageAdapter.add("发送消息：${ConvertUtils.bytes2HexString(bytes)}")
            } else {
                messageAdapter.add("收到消息：${ConvertUtils.bytes2HexString(bytes)}")
            }
            inflate.data.smoothScrollToPosition(messageAdapter.items.size)
        }
    }

    private fun closeSerialPort() {
        mSerialPortHelper.close()
        if (mSerialPortHelper.isOpen) {
            showToast("串口关闭失败")
        } else {
            inflate.sendInformation.visibility = View.GONE
            setText(inflate.open, "开启串口")
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.send -> {
                val text = inflate.information.text.toString().trim()
                if (text.isNotEmpty()) {
                    if (ConfigManage.sendType == SerialPortDataManage.send_type[0]) {
                        mSerialPortHelper.sendHex(text)
                    } else {
                        mSerialPortHelper.sendTxt(text)
                    }
                } else {
                    showToast("消息不能为空")
                }
            }
            R.id.serial_port -> {
                showItemDialog(SerialPortDataManage.serial_port) { selection ->
                    setText(inflate.serialPort, selection)
                    ConfigManage.serialPort = selection
                    if (mSerialPortHelper.isOpen) closeSerialPort()
                }
            }
            R.id.baud_rate -> {
                showItemDialog(SerialPortDataManage.baud_rate) { selection ->
                    setText(inflate.baudRate, selection)
                    ConfigManage.baudRate = selection
                    if (mSerialPortHelper.isOpen) closeSerialPort()
                }
            }
            R.id.data_bits -> {
                showItemDialog(SerialPortDataManage.data_bits) { selection ->
                    setText(inflate.dataBits, selection)
                    ConfigManage.dataBits = selection
                    if (mSerialPortHelper.isOpen) closeSerialPort()
                }
            }
            R.id.parity -> {
                showItemDialog(parityList) { selection ->
                    setText(inflate.parity, selection)
                    ConfigManage.parity = selection
                    if (mSerialPortHelper.isOpen) closeSerialPort()
                }
            }
            R.id.stop_bits -> {
                showItemDialog(SerialPortDataManage.stop_bits) { selection ->
                    setText(inflate.stopBits, selection)
                    ConfigManage.stopBits = selection
                    if (mSerialPortHelper.isOpen) closeSerialPort()
                }
            }
            R.id.flow_bits -> {
                showItemDialog(flowBitsList) { selection ->
                    setText(inflate.flowBits, selection)
                    ConfigManage.flowBits = selection
                    if (mSerialPortHelper.isOpen) closeSerialPort()
                }
            }
            R.id.open -> {
                if (!mSerialPortHelper.isOpen) {
                    openSerial()
                } else {
                    closeSerialPort()
                }
            }
        }
    }

    private fun setText(id: AppCompatButton, text: String) {
        id.text = text
    }

    private fun setText(id: AppCompatTextView, text: String) {
        id.text = text
    }

    private fun showItemDialog(items: Array<String>, selection: (String) -> Unit) {
        val dialogList = LayoutInflater.from(this).inflate(R.layout.list_content, null)
        val listview = dialogList.findViewById<ListView>(R.id.listview)
        listview.adapter =
            ArrayAdapter(this, android.R.layout.select_dialog_item, android.R.id.text1, items)
        val dialog = AlertDialog.Builder(this)
            .setItems(items
            ) { _, which -> selection(items[which]) }
            .create()
        dialog.show()
        // 由于autosize适配原因，对话框需要重新设置宽度以适配设备
        val lp = dialog.window!!.attributes
        lp.width = DisplayUtil.getWindowWidth(this) * 9 / 10
        lp.gravity = Gravity.CENTER
        dialog.window!!.attributes = lp
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_clear, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.findItem(R.id.clear)?.setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener {
            val items = messageAdapter.items
            for (i in items.size downTo 0 step 1) {
                if (items.size - 1 !== -1) {
                    messageAdapter.remove(items[i - 1])
                }
            }
            false
        })
        return super.onPrepareOptionsMenu(menu)
    }

    private fun initUI() {
        setText(inflate.serialPort, ConfigManage.serialPort)
        setText(inflate.baudRate, ConfigManage.baudRate)
        setText(inflate.dataBits, ConfigManage.dataBits)
        setText(inflate.parity, ConfigManage.parity)
        setText(inflate.stopBits, ConfigManage.stopBits)
        setText(inflate.flowBits, ConfigManage.flowBits)
        setText(inflate.sendType, ConfigManage.sendType)

        inflate.send.setOnClickListener(this)
        inflate.serialPort.setOnClickListener(this)
        inflate.baudRate.setOnClickListener(this)
        inflate.dataBits.setOnClickListener(this)
        inflate.parity.setOnClickListener(this)
        inflate.stopBits.setOnClickListener(this)
        inflate.flowBits.setOnClickListener(this)
        inflate.open.setOnClickListener(this)

        messageAdapter = MessageAdapter()
        inflate.data.layoutManager = LinearLayoutManager(this)
        inflate.data.adapter = messageAdapter
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        closeSerialPort()
        super.onDestroy()
    }
}