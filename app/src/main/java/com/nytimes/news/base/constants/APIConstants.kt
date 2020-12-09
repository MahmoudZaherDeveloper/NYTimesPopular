package com.nytimes.news.base.constants

const val EndpointUrl = "https://api.nytimes.com/svc/mostpopular/v2/viewed/"
const val APIKey = "8YSuKZE1GgfFw9fOoKQdWZ0jWkkIvzJZ"

enum class Period(val value: String) {
    DAILY("1"), WEEKLY("7"), MONTHLY("30");

    companion object {
        fun getPeriod(index: Int): Period {
            return when (index) {
                0 -> {
                    DAILY
                }
                1 -> {
                    WEEKLY
                }
                else -> {
                    MONTHLY
                }
            }
        }
    }
}