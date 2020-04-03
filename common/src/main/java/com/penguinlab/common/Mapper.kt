package com.penguinlab.common

interface Mapper<R, D> {
    fun mapFrom(type: R): D
}