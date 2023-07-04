package com.geniuz.postemplate.emv.core.models

data class CAPK(
    val checksum: String,
    val idx: Int,
    val arithInd: Int,
    val rid: String,
    val hashInd: Int,
    val expiryDate: String,
    val exponent: String,
    val modulus: String
)
