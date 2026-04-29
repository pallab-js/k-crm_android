# Nexus CRM

A dark-mode native Android CRM app built with Kotlin, Jetpack Compose, Room database, and Hilt dependency injection.

## Features

- **Dashboard** - Overview metrics: revenue, active deals, win rate, today's tasks
- **Contacts** - Manage leads, customers, and opportunities
- **Deals** - Pipeline tracking with deal stages
- **Tasks** - Task management with priorities
- **Security** - Encrypted local storage, secure logging, and network hardening

## Tech Stack

| Component | Technology |
|-----------|------------|
| Language | Kotlin 1.9.x |
| UI | Jetpack Compose + Material 3 |
| DI | Hilt |
| Database | Room (SQLite) + **SQLCipher** (Encryption) |
| Architecture | MVVM + Clean Architecture |
| Logging | **Timber** (Secure Logging) |
| Security | R8 Obfuscation, Network Security Config |
| Min SDK | 24 (Android 7.0) |
| Target SDK | 34 (Android 14) |

## Design

Supabase-inspired dark theme:
- Background: `#0f0f0f` / `#171717`
- Accent: `#3ECF8E` (green)
- Text: `#FAFAFA` / `#B4B4B4`

## Build

```bash
./gradlew assembleDebug
```

APK generated at: `app/build/outputs/apk/debug/app-debug.apk`

## Install

```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

## License

MIT License - see LICENSE.md