package com.picsart.business.di

internal object BusinessModuleFactory : ModuleFactory {
    override fun create(environment: Environment): Module {
        return when (environment) {
            Dev -> DevelopmentModule
        }
    }
}