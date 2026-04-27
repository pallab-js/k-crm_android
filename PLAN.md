# Nexus CRM - Implementation Plan

## 1. Overview
- **Project**: Nexus CRM - Dark-mode native Android CRM for sales professionals
- **Package**: com.nexus.crm
- **Platform**: Android (Min SDK 24, Target SDK 34)
- **Architecture**: MVVM + Clean Architecture

---

## 2. Tech Stack
| Component | Technology |
|-----------|------------|
| Language | Kotlin 1.9.x |
| UI | Jetpack Compose + Material 3 |
| DI | Hilt |
| Local Storage | Room Database |
| Navigation | Compose Navigation |
| Async | Kotlin Coroutines + Flow |

---

## 3. Color Palette (Supabase-inspired)

```kotlin
// Primary Surfaces
val NearBlack = Color(0xFF0F0F0F)      // Primary buttons
val DarkSurface = Color(0xFF171717)     // Page backgrounds

// Brand Accent
val SupabaseGreen = Color(0xFF3ECF8E)   // Primary accent
val GreenLink = Color(0xFF00C573)       // Interactive links

// Border Hierarchy
val BorderSubtle = Color(0xFF242424)
val BorderDefault = Color(0xFF2E2E2E)
val BorderProminent = Color(0xFF363636)

// Text
val TextPrimary = Color(0xFFFAFAFA)
val TextSecondary = Color(0xFFB4B4B4)
val TextMuted = Color(0xFF898989)
```

---

## 4. Typography

| Style | Font | Size | Weight | Line Height |
|-------|------|------|--------|-------------|
| Hero | Plus Jakarta Sans | 72px | 400 | 1.00 |
| Section Heading | Plus Jakarta Sans | 36px | 400 | 1.25 |
| Card Title | Plus Jakarta Sans | 24px | 400 | 1.33 |
| Body | Plus Jakarta Sans | 16px | 400 | 1.50 |
| Caption | Plus Jakarta Sans | 14px | 400 | 1.43 |
| Code Label | Source Code Pro | 12px | 400 | 1.33 |

---

## 5. Data Model (Room Entities)

### Contact
| Field | Type | Notes |
|-------|------|-------|
| id | Long | Primary key, auto-generated |
| name | String | Required |
| email | String | Optional |
| phone | String | Optional |
| company | String | Optional |
| status | ContactStatus | LEAD, CUSTOMER, OPPORTUNITY |
| industry | String | Optional |
| notes | String | Optional |
| createdAt | Long | Timestamp |

### Deal
| Field | Type | Notes |
|-------|------|-------|
| id | Long | Primary key |
| title | String | Required |
| value | Double | Deal amount |
| stage | DealStage | APPOINTMENT, PRESENTATION, PROPOSAL, NEGOTIATION, CLOSED_WON, CLOSED_LOST |
| contactId | Long | FK to Contact |
| createdAt | Long | Timestamp |
| updatedAt | Long | Timestamp |

### Task
| Field | Type | Notes |
|-------|------|-------|
| id | Long | Primary key |
| title | String | Required |
| description | String | Optional |
| dueDate | Long | Timestamp |
| priority | Priority | HIGH, MEDIUM, LOW |
| isCompleted | Boolean | Default false |
| contactId | Long? | Optional FK |
| dealId | Long? | Optional FK |

---

## 6. Navigation Structure

```
BottomNavigation (4 items)
├── Dashboard (@/dashboard)
│   └── DashboardScreen
├── Contacts (@/contacts)
│   ├── ContactListScreen
│   └── ContactDetailScreen (@/contacts/:id)
├── Deals (@/deals)
│   └── DealsScreen
└── Tasks (@/tasks)
    └── TasksScreen
```

**Bottom Nav Items**:
- Dashboard (grid icon)
- Contacts (people icon)
- Deals (briefcase icon)
- Tasks (checklist icon)

---

## 7. Screen Specifications

### 7.1 Dashboard Screen
**Layout**: Vertical scroll
**Components**:
- Header: "Dashboard" title
- Metric Cards Row (3 cards, horizontal scroll):
  - Total Revenue ($XXX,XXX)
  - New Deals (XX)
  - Win Rate (XX%)
- Daily Tasks Section:
  - Section header: "Tasks Due Today"
  - Task list (max 5 items)

### 7.2 Contacts Screen
**Layout**: Vertical scroll + search
**Components**:
- SearchBar (text input with search icon)
- FilterChips: All | Lead | Customer | Opportunity
- ContactList (LazyColumn):
  - ContactListItem: Avatar, Name, Company, Status Badge
- FAB: Add new contact

### 7.3 Deals Screen
**Layout**: Tab-based by stage
**Components**:
- StageTabs: Appointment | Presentation | Proposal | Negotiation | Closed
- DealsList per stage:
  - DealItem: Title, Value, Company
- FAB: Create deal

### 7.4 Tasks Screen
**Layout**: Vertical scroll
**Components**:
- FilterChips: All | Today | Overdue
- TaskList:
  - TaskItem: Checkbox, Title, Due Date, Priority Badge
- FAB: Add new task

---

## 8. Component Library

### Core Components
| Component | Usage |
|-----------|-------|
| NexusScaffold | Main scaffold with bottom nav |
| MetricCard | Dashboard metrics |
| ContactListItem | Contact row |
| DealListItem | Deal row |
| TaskListItem | Task row |
| FilterChip | Status/priority filters |
| SearchBar | Search input |
| NexusFAB | Floating action button |
| StatusBadge | Lead/Customer/Opportunity |
| PriorityBadge | High/Medium/Low |

### Shape Definitions
- Pill Button: RoundedCorner (9999)
- Card: RoundedCorner (12.dp)
- Bottom Nav: RoundedCorner top (16.dp)

---

## 9. File Structure

```
crm/
├── PLAN.md
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── gradle/wrapper/
├── app/
│   ├── build.gradle.kts
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/nexus/crm/
│       │   ├── NexusApp.kt          # Application class
│       │   ├── MainActivity.kt     # Entry point
│       │   ├── data/
│       │   │   ├── local/
│       │   │   │   ├── NexusDatabase.kt
│       │   │   │   ├── ContactDao.kt
│       │   │   │   ├── DealDao.kt
│       │   │   │   └── TaskDao.kt
│       │   │   ├── model/
│       │   │   │   ├── Contact.kt
│       │   │   │   ├── Deal.kt
│       │   │   │   └── Task.kt
│       │   │   └── repository/
│       │   │       ├── ContactRepository.kt
│       │   │       ├── DealRepository.kt
│       │   │       └── TaskRepository.kt
│       │   ├── ui/
│       │   │   ├── theme/
│       │   │   │   ├── Color.kt
│       │   │   │   ├── Type.kt
│       │   │   │   └── Theme.kt
│       │   │   ├── components/
│       │   │   │   ├── MetricCard.kt
│       │   │   │   ├── ContactListItem.kt
│       │   │   │   ├── DealListItem.kt
│       │   │   │   ├── TaskListItem.kt
│       │   │   │   ├── StatusBadge.kt
│       │   │   │   ├── PriorityBadge.kt
│       │   │   │   ├── FilterChip.kt
│       │   │   │   ├── SearchBar.kt
│       │   │   │   └── NexusFAB.kt
│       │   │   ├── navigation/
│       │   │   │   ├── Screen.kt
│       │   │   │   └── NexusNavHost.kt
│       │   │   └── screens/
│       │   │       ├── dashboard/
│       │   │       │   └── DashboardScreen.kt
│       │   │       ├── contacts/
│       │   │       │   ├── ContactListScreen.kt
│       │   │       │   └── ContactDetailScreen.kt
│       │   │       ├── deals/
│       │   │       │   └── DealsScreen.kt
│       │   │       └── tasks/
│       │   │           └── TasksScreen.kt
│       │   └── di/
│       │       └── DatabaseModule.kt
│       └── res/
│           ├── values/
│           │   ├── strings.xml
│           │   └── themes.xml
│           └─��� drawable/
│               └── ic_launcher_foreground.xml
```

---

## 10. Build Configuration

### Gradle Properties
```properties
org.gradle.jvmargs=-Xmx4096m -XX:MaxMetaspaceSize=512m
android.useAndroidX=true
kotlin.code.style=official
android.nonTransitiveRClass=true
```

### Root build.gradle.kts Plugins
- com.android.application (8.2.0)
- com.android.library (8.2.0)
- org.jetbrains.kotlin.android (1.9.21)
- com.google.dagger.hilt.android (2.48.1)
- com.google.devtools.ksp (1.9.21-1.0.16)

### App Dependencies
- androidx.compose.ui (BOM)
- androidx.compose.material3
- androidx.navigation.compose
- androidx.room (2.6.1)
- androidx.hilt.navigation.compose
- dagger.hilt.android.compose

---

## 11. Implementation Order

1. **Setup Phase**: Gradle wrapper, build files, theme
2. **Data Phase**: Room entities, DAOs, database
3. **Navigation Phase**: Bottom nav shell
4. **Screens Phase**: Dashboard → Contacts → Deals → Tasks
5. **Integration Phase**: Connect data to UI

---

## 12. Success Criteria

- [ ] APK builds without errors
- [ ] Dark theme applied consistently
- [ ] All 4 screens render correctly
- [ ] Bottom navigation works
- [ ] Room database persists data
- [ ] No crashes on launch