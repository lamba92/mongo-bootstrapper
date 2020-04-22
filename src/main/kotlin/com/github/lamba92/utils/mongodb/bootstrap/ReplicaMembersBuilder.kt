package com.github.lamba92.utils.mongodb.bootstrap

class ReplicaMembersBuilder {

    private val members = mutableListOf<ReplicaMember>()

    fun add(action: ReplicaMember.() -> Unit) =
        ReplicaMember().apply(action).also { members.add(it) }

    fun build() =
        members.toList()

}
