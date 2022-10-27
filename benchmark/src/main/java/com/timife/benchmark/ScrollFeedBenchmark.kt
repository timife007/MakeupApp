package com.timife.benchmark

import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ScrollFeedBenchmark {
    @get:Rule
    val rule = MacrobenchmarkRule()

    fun scrollFeed() = rule.measureRepeated(
        packageName = "com.timife.makeup",
        metrics = listOf(FrameTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD,
        setupBlock = {  //The setupBlock is not to be measured but must be executed before the measure block
            pressHome()
            startActivityAndWait()

            val items = device.findObject(By.res(""))
            repeat(3){
                items.children[it].click()
            }
        }
    ){
        val makeupList = device.findObject(By.res(""))
        makeupList.setGestureMargin(device.displayWidth / 5)
        makeupList.fling(Direction.DOWN)
    }


}