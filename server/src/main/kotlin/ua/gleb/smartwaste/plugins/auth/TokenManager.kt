package ua.gleb.smartwaste.plugins.auth

import com.auth0.jwk.JwkProvider
import com.auth0.jwk.JwkProviderBuilder
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*
import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*
import java.util.concurrent.TimeUnit

object TokenManager {

    var issuer: String? = null
    var audience: String? = null
    var myRealm: String? = null
    var privateKey: String? = null
    var jwkProvider: JwkProvider? = null

    fun configureJwk() {
        val appConfig = HoconApplicationConfig(ConfigFactory.load())
        issuer = appConfig.property("jwt.issuer").getString()
        privateKey = appConfig.property("jwt.privateKey").getString()
        audience = appConfig.property("jwt.audience").getString()
        myRealm = appConfig.property("jwt.realm").getString()
        jwkProvider = JwkProviderBuilder(issuer)
            .cached(10, 24, TimeUnit.HOURS)
            .rateLimited(10, 1, TimeUnit.MINUTES)
            .build()
    }

    fun generateToken(email: String): String? {
        val publicKey = jwkProvider?.get("6f8856ed-9189-488f-9011-0ff4b6c08edc")?.publicKey
        val keySpecPKCS8 = PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey))
        val privateKey = KeyFactory.getInstance("RSA").generatePrivate(keySpecPKCS8)
        return JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("email", email)
            .withExpiresAt(Date(System.currentTimeMillis() + 120000))
            .sign(Algorithm.RSA256(publicKey as RSAPublicKey, privateKey as RSAPrivateKey))
    }
}