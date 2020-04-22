package com.github.lamba92.utils.mongodb.bootstrap

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReplicaConfigurationDocument(
    @SerialName("_id") var id: String = "",
    @SerialName("configsvr") var enableConfigurationServer: Boolean = false,
    var members: List<ReplicaMember> = emptyList()
)
