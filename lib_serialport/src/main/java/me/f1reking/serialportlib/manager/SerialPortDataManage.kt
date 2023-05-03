package me.f1reking.serialportlib.manager

/**
 * @author binjx
 * @date 2023/4/24 11:37
 * @purpose：
 **/
object SerialPortDataManage {

    /**
     * 串口号
     */
    val serial_port = arrayOf("/dev/ttyS0",
        "/dev/ttyS1",
        "/dev/ttyS2",
        "/dev/ttyS3",
        "/dev/ttyS4",
        "/dev/ttyS5",
        "/dev/ttyS6",
        "/dev/ttyS7",
        "/dev/ttyS8")

    /**
     * 波特率
     */
    val baud_rate = arrayOf("0",
        "50",
        "75",
        "100",
        "110",
        "150",
        "200",
        "300",
        "600",
        "1000",
        "1200",
        "1800",
        "2400",
        "4800",
        "9600",
        "19200",
        "38400",
        "57600",
        "115200",
        "230400",
        "460800",
        "500000",
        "576000",
        "921600",
        "1000000",
        "1152000",
        "1500000",
        "2000000",
        "2500000",
        "3000000",
        "3500000",
        "4000000")

    /**
     * 数据位
     */
    val data_bits = arrayOf("5", "6", "7", "8")

    /**
     * 校验位
     * 0：无奇偶校验
     * 1：奇校验
     * 2：偶校验
     */
    val parity = mapOf("None" to "0", "Odd" to "1", "Even" to "2")

    /**
     * 停止位
     */
    val stop_bits = arrayOf("1", "1.5", "2")

    /**
     * 流控
     * 0：不适用流控
     * 1：硬件流控
     * 2：软件流控
     */
    val flow_bits = mapOf("None" to "0", "RTS/CTS" to "1", "XON/XOFF" to "2")

    /**
     * 消息发送类型
     */
    val send_type = arrayOf("Hex", "Txt")
}