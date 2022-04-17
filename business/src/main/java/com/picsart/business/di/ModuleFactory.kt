package com.picsart.business.di

interface ModuleFactory {
    fun create(environment: Environment): Module
}

fun moduleFactory(): ModuleFactory = BusinessModuleFactory