package io.github.linesegments.dal.rest.kotlin

import java.util.concurrent.Future
import javax.ws.rs.client.AsyncInvoker
import javax.ws.rs.client.Entity
import javax.ws.rs.core.GenericType

inline fun <reified T: Any> AsyncInvoker.get(): Future<T> {
    val clazz = object : GenericType<T>(){}

    return get(clazz)
}

inline fun <reified T: Any> AsyncInvoker.post(entity: Entity<out Any>): Future<T> {
    val clazz = object : GenericType<T>(){}

    return post(entity, clazz)
}