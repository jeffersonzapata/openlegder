package com.jdemolitions.openledger

import java.util.concurrent.Executors

val APP_TAG = "OL"

private val singleThreadExecutor = Executors.newSingleThreadExecutor()

fun runOnIoThread(f: () -> Unit): Unit = singleThreadExecutor.execute(f)
