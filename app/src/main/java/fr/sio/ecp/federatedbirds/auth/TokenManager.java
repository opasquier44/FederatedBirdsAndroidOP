package fr.sio.ecp.federatedbirds.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * The token manager holds the API token in the SharedPreferences
 */
public class TokenManager {

    private static final String AUTH_PREFERENCES = "auth";
    private static final String TOKEN_KEY = "token";

    public static String getUserToken(Context context) {
        // Get the SharedPreferences dedicated to the authentication settings
        SharedPreferences sp = context.getSharedPreferences(AUTH_PREFERENCES, Context.MODE_PRIVATE);
        // Reading is simple, SharedPreferences are a key-value storage
        return sp.getString(TOKEN_KEY, null);
    }

    public static void setUserToken(Context context, String token) {
        // Get the SharedPreferences dedicated to the authentication settings
        SharedPreferences sp = context.getSharedPreferences(AUTH_PREFERENCES, Context.MODE_PRIVATE);
        // To write value, we have to open an Editor on the SharedPreferences, perform the changes then apply() (transaction-like)
        sp.edit().putString(TOKEN_KEY, token).apply();
        Log.i(TokenManager.class.getSimpleName(), "User token saved: " + token);
    }

    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(AUTH_PREFERENCES, Context.MODE_PRIVATE);
        // Clearing is just like writing
        sp.edit().clear().apply();
        Log.i(TokenManager.class.getSimpleName(), "Auth preferences cleared");
    }

}
