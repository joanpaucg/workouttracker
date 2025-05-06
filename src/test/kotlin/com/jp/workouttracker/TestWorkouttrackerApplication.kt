package com.jp.workouttracker

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<WorkouttrackerApplication>().with(TestcontainersConfiguration::class).run(*args)
}
