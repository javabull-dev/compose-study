package cn.ljpc.swipeable

/**
 * width: 与像素无关的尺寸
 * count: btn的个数
 * density: 密度
 */
data class BtnLen(val width: Float, val count: Int, val density: Float) {

    /**
     * 真实的像素值：px
     * px = density * dp
     */
    fun length(): Float {
        return width * count * density
    }
}