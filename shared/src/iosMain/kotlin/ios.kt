package br.com.rafaelgabriel

import platform.UIKit.UIDevice

actual fun platformName(): String {
    return "${UIDevice.currentDevice.systemName()} version ${UIDevice.currentDevice.systemVersion()}"
}