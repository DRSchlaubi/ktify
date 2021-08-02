package com.github.warriorzz.ktify.user

import com.github.warriorzz.ktify.Ktify
import com.github.warriorzz.ktify.model.user.CurrentUser
import com.github.warriorzz.ktify.model.user.PublicUser
import io.ktor.http.*

/**
 *  @return The [CurrentProfile](https://github.com/warriorzz/ktify/blob/ac318a38f72f770893f2c4c9cf64dc18a2e05a86/src/main/kotlin/com/github/warriorzz/ktify/model/user/User.kt#L11) object corresponding to the current user
 */
suspend fun Ktify.getCurrentProfile() = requestHelper.makeRequest<CurrentUser>(
    HttpMethod.Get,
    requestHelper.baseUrl + "me",
    null, null,
    null
)

/**
 *  @param  userId  The [ID](https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids) of the Spotify user
 *  @return Instance of the [PublicUser](https://github.com/warriorzz/ktify/blob/f989c569494518ce3a86c5c391c4bd09bc9bb437/src/main/kotlin/com/github/warriorzz/ktify/model/user/User.kt#L30) class, null if the user wasn't found
 */
suspend fun Ktify.getUserProfile(userId: String) : PublicUser? {
    if (requestHelper.makeRequest(
        HttpMethod.Get,
        requestHelper.baseUrl + "users/$userId",
        parameters = null, headers = null, body = null
    ) != HttpStatusCode.OK) {
        return null
    }
    return requestHelper.makeRequest<PublicUser>(
        HttpMethod.Get,
        requestHelper.baseUrl + "users/$userId",
        parameters = null, headers = null, body = null
    )
}