package com.wmy.core.http.error

/**
 * 错误类
 */
enum class ErrorEnum(private val code: Int, private val err: String) {

    /**
     * 未知错误
     */
    UNKNOWN(1000, "网络错误，请稍后重试"),
    /**
     * 解析错误
     */
    PARSE_ERROR(1001, "服务数据异常!请稍后再试!"),
    /**
     * 网络错误
     */
    NETWORD_ERROR(1002, "网络错误，请稍后重试"),
    /**
     * 协议出错
     */
    HTTP_ERROR(1003, "服务数据异常!请稍后再试!"),

    /**
     * 证书出错
     */
    SSL_ERROR(1004, "网络错误，请稍后重试"),

    /**
     * 连接超时
     */
    TIMEOUT_ERROR(1006, "连接超时，请稍后重试"),

    /**
     * 参数不合法
     */
    ARGUMENT_ERROR(1008, "不合法的参数"),
    RESULT_ERROR(1009, "返回数据结构错误"),

    /**
     * 空指针异常
     *
     */
    NULL_ERROR(1010, "操作的对象或属性不存在");

    fun getValue(): String {
        return err
    }

    fun getKey(): Int {
        return code
    }

}