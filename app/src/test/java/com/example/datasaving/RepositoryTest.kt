package com.example.datasaving

import org.junit.Assert.assertEquals
import org.junit.Test


class RepositoryTest {

    @Test
    fun test() {

        val dataSource = FakeDataSource()
        val fakeNow = FakeNow()
        val repository = Repository(dataSource, fakeNow)

        fakeNow.time = 1000

        repository.startTracking()
        fakeNow.time = 10_000
        repository.stopTracking()
        assertEquals(9_000, dataSource.getLong("", 0))

        repository.startTracking()
        fakeNow.time = 15_000

        repository.stopTracking()
        assertEquals(14_000, dataSource.getLong("", 0))
    }
}

private class FakeNow : Now {

    var time = 0L
    override fun time(): Long {
        return time
    }
}

private class FakeDataSource : DataSource {
    var value: Long = 0

    override fun putLong(key: String, value: Long) {
        this.value = value
    }

    override fun getLong(key: String, default: Long): Long {
        return this.value
    }
}