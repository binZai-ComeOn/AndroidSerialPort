# Android-SerialPort
该项目Fork自[licheedev](https://github.com/licheedev)的[Android-SerialPort-API](https://github.com/licheedev/Android-SerialPort-API)

此项目移植于谷歌官方串口库[android-serialport-api](https://code.google.com/archive/p/android-serialport-api/),但该项目仅支持串口名称及波特率，所以在项目的基础上添加支持数据位、数据位、停止位、流控等配置。

[![](https://jitpack.io/v/F1ReKing/Android-SerialPort.svg)](https://jitpack.io/#F1ReKing/Android-SerialPort)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/F1ReKing/Android-SerialPort/blob/master/LICENSE)

![](https://github.com/binZai-ComeOn/AndroidSerialPort/blob/master/app/img.png)

## 使用

### 1. 将`lib_serialport`模块导入到自己的项目

### 2. 查询串口列表

```java
SerialPortHelper#getAllDevices();
// 查询串口设备地址列表
SerialPortHelper#getAllDeicesPath();
```

### 3. 配置串口参数

```kotlin
mSerialPortHelper.port = // 支持配置串口号
mSerialPortHelper.flags(int flags); // 支持设置标志
mSerialPortHelper.baudRate = // 支持配置波特率
mSerialPortHelper.dataBits = // 支持设置数据位 
mSerialPortHelper.parity = // 支持设置检验位
mSerialPortHelper.stopBits = // 支持设置停止位
mSerialPortHelper.flowCon = // 支持设置流控
mSerialPortHelper.setISerialPortDataListener(object : ISerialPortDataListener { // 支持数据回调监听
    override fun onDataReceived(bytes: ByteArray?) {
	byteToHex(1, bytes)
    }

    override fun onDataSend(bytes: ByteArray?) {
	byteToHex(0, bytes)
    }

})
```

### 4. 打开串口

```java
// 同步打开串口（return false or ture）
SerialPortHelper#open(); 
// 打开串口监听（可选）
mSerialPortHelper.setIOpenSerialPortListener(object : IOpenSerialPortListener {
    override fun onSuccess(device: File?) {
	runOnUiThread {
	    showToast("串口打开成功")
	}
    }

    override fun onFail(device: File?, status: Status?) {
	runOnUiThread {
	    showToast("串口打开失败")
	}
    }

})
```

### 5. 关闭串口

```java
// 同步关闭串口（return false or ture）
SerialPortHelper#close();
```

### 6. 发送数据

```java
SerialPortHelper#sendBytes(byte[] bytes); // 支持发送byte[]
SerialPortHelper#sendHex(String hex); // 支持发送Hex
SerialPortHelper#sendTxt(String txt); // 支持发送ASCII码
```

### 7. 接收数据

```java
public interface ISerialPortDataListener {
	// 接收数据回调
    void onDataReceived(byte[] bytes);
   	// 发送数据回调
    void onDataSend(byte[] bytes);
}
```

### 8. 回调

```java
//  串口打开状态监听
void setIOpenSerialPortListener(IOpenSerialPortListener IOpenSerialPortListener);

// 串口消息监听
void setISerialPortDataListener(ISerialPortDataListener ISerialPortDataListener);
```
### 9. proguard-rules 
```
-keep class me.f1reking.serialportlib.** {*;}
```

## License
```
Copyright 2019 F1ReKing. 

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

