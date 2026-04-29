# Enhancements Blueprint for k-crm_android

## Goal Description

Transform the existing Android CRM prototype into a modern, professional, high‑performance mobile CRM application inspired by HubSpot and following the Supabase‑inspired design system (DESIGN.md). The plan covers architectural refactoring, UI/UX modernization, feature expansion, code quality, testing, CI/CD, and documentation.

## User Review Required

> [!IMPORTANT]
> Please review the proposed high‑level changes (especially addition of new dependencies like navigation) and confirm if any are out of scope or if you have preferences for specific libraries or versions.
>
> - Keep local Room‑only storage (no cloud backend).
> - Approve the decision to adopt Jetpack Compose Material 3 theming matching DESIGN.md.
> - Approve the addition of MVVM architecture with Repository pattern.

## Open Questions

> [!QUESTION]
> - No remote backend will be used; all data stays local.
> - Retain the existing seed data approach; no remote onboarding flow.
> - Are there specific CI platforms (GitHub Actions, Bitrise) you prefer?
> - Any constraints on app size or minimum SDK version?

## Proposed Changes

---
### Project Configuration & Dependencies

- **[MODIFY] build.gradle.kts** (project root) – update Gradle wrapper to latest stable (8.5), set Kotlin to 1.9.22.
- **[MODIFY] app/build.gradle.kts** – add/upgrade dependencies:
  - `androidx.compose.ui:ui` & `ui-tooling-preview` (latest)
  - `androidx.compose.material3:material3` for dark‑mode theming.
  - `androidx.navigation:navigation-compose`.
  - `androidx.hilt:hilt-navigation-compose` & `dagger:hilt-android` (latest).
  - `androidx.lifecycle:lifecycle-viewmodel-compose`.
  - `androidx.room:room-ktx` & `room-paging`.
  - (Supabase SDK omitted per user request; using local Room database only.)
  - Add **Coil** for image loading.
  - Update `kotlinx-coroutines` to latest version.
- Enable **Compose Compiler** metrics and **Kotlin Symbol Processing (KSP)** for Hilt.

---
### Architecture Refactor (MVVM + Repository)

- Create `ui/viewmodel` package with base ViewModel classes.
- Create `data/repository` package implementing Repository interfaces for Contacts, Deals, Tasks.
- Move existing DAO usage from UI into Repository, exposing `Flow<List<…>>`.
- Inject Repositories via Hilt.
- Add a `Domain` layer (optional) for use‑case classes.

---
### UI/UX Modernization (Compose + DESIGN.md)

- Implement a **Theme** object (`ui/theme/NexusTheme.kt`) that mirrors the color tokens, typography, and shapes defined in DESIGN.md (dark background, emerald accent, pill buttons, border‑only depth).
- Replace any legacy XML layouts (none currently) with composable screens:
  - `HomeScreen` – dashboard with summary cards.
  - `ContactsScreen`, `DealsScreen`, `TasksScreen` – list screens using `LazyColumn` and Material3 cards.
  - `DetailScreen` for each entity.
  - `AddEditScreen` with form fields.
- Use **NavigationCompose** (`ui/navigation/NexusNavHost.kt`) to handle routes.
- Add **BottomNavigationBar** with icons matching SUPABASE design.
- Apply **responsive breakpoints** (mobile‑first) using `WindowSizeClass` utilities.
- Integrate **Coil** for avatar images.
- Add **Pull‑to‑Refresh** via `SwipeRefresh` (Accompanist).

---
### Data Layer Enhancements

- Add **Paging 3** for large contact/deal lists.
- Implement **Database Migration** strategy (versioning) for future schema changes.
- Keep existing local seed data; no remote sync.
- Add **Encryption** (SQLCipher) for sensitive data.

---
### Testing & Quality

- Add unit tests for ViewModels and Repositories (`test/...`).
- Add UI tests with **Compose Testing** and **Espresso**.
- Integrate **Detekt** and **Ktlint** for linting.
- Add **Jacoco** code coverage reports.

---
### CI/CD & Documentation

- Create **GitHub Actions** workflow for building, linting, testing on each PR.
- Add **Release** workflow to generate signed APK/AAB.
- Expand `README.md` with setup, architecture diagram, and contribution guide.
- Add **CHANGELOG.md** and **CONTRIBUTING.md**.

---
### Performance & Optimizations

- Enable **ProGuard/R8** rules for Hilt and Room.
- Optimize image loading (Coil caching).
- Use **ViewModelScope** and **Flow** to avoid memory leaks.
- Ensure **Compose recomposition** stability by using `remember` correctly.

---
### Accessibility & Internationalization

- Add content descriptions for icons.
- Ensure contrast ratios meet WCAG AA (dark theme).
- Add string resources for all UI text.

---
### Optional Enhancements (Future)

- (Cloud-based optional features such as push notifications, analytics, realtime sync, and multi‑account support have been omitted as per user request.)

---
## Verification Plan

### Automated Tests
- `./gradlew testDebugUnitTest` – run all unit tests.
- `./gradlew connectedAndroidTest` – execute instrumented UI tests on emulator.
- `./gradlew detekt ktlintCheck` – lint checks.

### Manual Verification
- Launch app on emulator/device, verify dark theme, pill buttons, navigation, list scrolling.
- Test CRUD operations for contacts, deals, tasks.
- Verify local data operations (CRUD) function correctly.
- Verify that UI matches DESIGN.md specifications (colors, typography, spacing).

---
**Implementation notes**: All code changes will be performed within `/Users/pallabpc/Desktop/k-crm_android` respecting the existing package structure.

---
*Please review the above blueprint and provide feedback or approval to proceed.*
