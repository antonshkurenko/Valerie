package me.cullycross.valerie

import android.app.Application
import android.test.ApplicationTestCase

/**
 * [Testing Fundamentals](http://d.android.com/tools/testing/testing_android.html)
 */

// чtodo(tonyshkurenko), 1/10/16:  move tests to kotlin directory from java's one
class ApplicationTest : ApplicationTestCase<Application>(Application::class.java)