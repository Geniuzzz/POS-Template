package com.geniuz.postemplate.core.models

data class AID(
    val aid: String,
    val asi: Int,
    val appVerNum: String,
    val floorLimit: Long,
    val contactlessCvmLimit: Long,
    val contactlessTransLimit: Long,
    val contactlessFloorLimit: Long,
    val maxTargetPercent: Int,
    val onlinePinCap: Int,
    val tacDefault: String,
    val tacDenial: String,
    val tacOnline: String,
    val transLimit: Long
)
