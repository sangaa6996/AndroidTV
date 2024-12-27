package com.dev.androidtv.common

interface ApiMapper<Domain,Entity> {
    fun mapToDomain(apiDto:Entity):Domain
}