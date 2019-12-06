@file:Suppress("UnstableApiUsage")

package com.github.emyar.hematournament.tournamentcontrol.util

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import com.google.common.collect.ImmutableSet
import java.util.*

inline fun <T, R> Collection<T>.mapToImmutableList(crossinline mapFunc: (T) -> R): List<R> {
    return ImmutableList.builderWithExpectedSize<R>(this.size).apply {
        this@mapToImmutableList.forEach { add(mapFunc.invoke(it)) }
    }.build()
}

fun <T> Sequence<T>.toImmutableSet(): Set<T> {
    return ImmutableSet.builder<T>().apply {
        this@toImmutableSet.forEach { add(it) }
    }.build()
}

inline fun <T, R> Array<T>.mapToImmutableSet(crossinline mapFunc: ((T) -> R)): Set<R> =
    ImmutableSet.builderWithExpectedSize<R>(this.size).apply {
        this@mapToImmutableSet.forEach { add(mapFunc.invoke(it)) }
    }.build()

inline fun <T, R> Collection<T>.mapToImmutableSet(crossinline mapFunc: ((T) -> R)): Set<R> =
    ImmutableSet.builderWithExpectedSize<R>(this.size).apply {
        this@mapToImmutableSet.forEach { add(mapFunc.invoke(it)) }
    }.build()

fun <T : Enum<T>> enumSet(vararg values: T): Set<T> {
    return when {
        values.isEmpty() -> emptySet()
        values.size == 1 -> setOf(values[0])
        else -> Collections.unmodifiableSet(EnumSet.of(values[0], *values))
    }
}

fun <K, V> immutableMapOf(keyMapper: ((V) -> K), vararg values: V): Map<K, V> =
    ImmutableMap.builderWithExpectedSize<K, V>(values.size).apply {
        values.forEach { put(keyMapper.invoke(it), it) }
    }.build()