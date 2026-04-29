# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /usr/local/Cellar/android-sdk/24.3.1/tools/proguard/proguard-android.txt

# Keep Hilt generated classes
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep class * extends dagger.hilt.android.internal.managers.ApplicationComponentManager { *; }

# Keep Room entities
-keep class com.nexus.crm.data.model.** { *; }

# Keep Compose
-keep class androidx.compose.** { *; }

# SQLCipher rules
-keep class net.zetetic.database.** { *; }
-keep class net.zetetic.database.sqlcipher.** { *; }

# Timber rules
-keep class timber.log.** { *; }