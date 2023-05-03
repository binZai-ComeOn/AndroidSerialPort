package com.binyouwei.androidserialport.manage

import com.blankj.utilcode.util.SPStaticUtils
import me.f1reking.serialportlib.manager.SerialPortDataManage

/**
 * @author binjx
 * @date 2023/4/26 16:21
 * @purpose：
 **/
object ConfigManage {
    private const val SERIAL_PORT = "SERIAL_PORT"
    private const val BAUD_RATE = "BAUD_RATE"
    private const val DATA_BITS = "DATA_BITS"
    private const val PARITY = "PARITY"
    private const val STOP_BITS = "STOP_BITS"
    private const val FLOW_BITS = "FLOW_BITS"
    private const val SEND_TYPE = "SEND_TYPE"

    var serialPort = ""
        get() = SPStaticUtils.getString(SERIAL_PORT, SerialPortDataManage.serial_port[0])
        set(value) {
            SPStaticUtils.put(SERIAL_PORT, value)
            field = value
        }

    var baudRate = ""
        get() = SPStaticUtils.getString(BAUD_RATE, SerialPortDataManage.baud_rate[0])
        set(value) {
            SPStaticUtils.put(BAUD_RATE, value)
            field = value
        }

    var dataBits = ""
        get() = SPStaticUtils.getString(DATA_BITS, SerialPortDataManage.data_bits[3])
        set(value) {
            SPStaticUtils.put(DATA_BITS, value)
            field = value
        }

    var parity = ""
        get() = SPStaticUtils.getString(PARITY, getNoOneKey(SerialPortDataManage.parity))
        set(value) {
            SPStaticUtils.put(PARITY, value)
            field = value
        }

    var stopBits = ""
        get() = SPStaticUtils.getString(STOP_BITS, SerialPortDataManage.stop_bits[0])
        set(value) {
            SPStaticUtils.put(STOP_BITS, value)
            field = value
        }

    var flowBits = ""
        get() = SPStaticUtils.getString(FLOW_BITS,getNoOneKey(SerialPortDataManage.flow_bits))
        set(value) {
            SPStaticUtils.put(FLOW_BITS, value)
            field = value
        }

    var sendType = ""
        get() = SPStaticUtils.getString(SEND_TYPE, SerialPortDataManage.send_type[0])
        set(value) {
            SPStaticUtils.put(SEND_TYPE, value)
            field = value
        }

    private fun getNoOneKey(map: Map<String, String>): String {
        map.forEach {
            return it.key
        }
        return ""
    }
}