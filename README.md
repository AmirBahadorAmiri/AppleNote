# AppleNote

> 📖 [نسخه فارسی](./README.fa.md)

An iOS Notes-inspired notebook app for Android, built with Java and MVP architecture — a clean, practical example for learning MVP on Android.

[![Platform](https://img.shields.io/badge/Platform-Android-green)](https://developer.android.com)
[![Language](https://img.shields.io/badge/Language-Java-orange)](https://www.java.com)
[![minSdk](https://img.shields.io/badge/minSdk-26-blue)](https://developer.android.com)
[![Version](https://img.shields.io/badge/Version-1.2.2-yellow)](https://myket.ir/app/com.amirbahadoramiri.applenotebook)
[![Package](https://img.shields.io/badge/Myket-com.amirbahadoramiri.applenotebook-red)](https://myket.ir/app/com.amirbahadoramiri.applenotebook)

## 📥 Download

**[Get it on Myket](https://myket.ir/app/com.amirbahadoramiri.applenotebook)**

[![Get it on Myket](https://img.shields.io/badge/Myket-Download-red)](https://myket.ir/app/com.amirbahadoramiri.applenotebook)

## ✨ Features

### 📝 Notes
- Create, edit and delete notes in `NoteActivity`
- Notes list with `RecyclerView` + `NoteAdapter` in `MainActivity`
- Live title search via `MainActivityPresenter.onSearch()` (Room `LIKE` query in `NoteDao.search()`)
- Delete-all-notes with confirmation (`TeleDialogDouble`) via `MainActivityPresenter.onDeleteAllNote()`
- Offline persistence with Room (`RoomDB`, `NoteDao`, `Note` entity)

### 🍎 iOS look
- iOS Notes-style UI: San Francisco font (`san_francisco_medium.ttf`), iOS icons (`ic_ios_back`, `ic_ios_share`, `ic_ios_menu`), iOS dialogs (`DialogBottom`, `DialogNormal`, `DialogHud`)
- Simulated iOS status bar in `NoteActivity` (`tv_time`, `tv_battery`, `iv_wifi`, `iv_battery`) with Persian digits via `REPL`
- Share note as plain text via `Intent.ACTION_SEND` (`NoteActivity.shareText()`)

### ⚙️ Personalization
- 5 note backgrounds (`bg0`–`bg4`) saved with `SharedHelper` in `SettingsActivity`
- Adjustable editor text size (12–20sp) saved with `SharedHelper` in `SettingsActivity`
- Settings reload live in `NoteActivity.loadSettings()` / `onActivityResult()`

### ℹ️ About
- App version from `Packager` in `AboutActivity`
- Quick links: GitHub repo, developer GitHub, Gmail feedback, Myket comment screen

## 🛠 Tech Stack

| Layer | Library / Tool |
|---|---|
| Language | Java 11 |
| Architecture | MVP (`*Contract` + `*Presenter` + `*Activity`, `BaseActivity` / `BaseContract`) |
| Database | Room 2.8.4 (`room-runtime`, `room-rxjava3`) |
| Reactive | RxJava 3 + RxAndroid 3 |
| UI | AppCompat, Material, ConstraintLayout |
| Dialogs | ioslike dialogs + TelegramDialog 1.3.1 |
| Storage (prefs) | `SharedHelper` |
| Build | AGP 9.3.1, Gradle KTS, `libs.versions.toml` |

## 📁 Project Structure

```text
app/src/main/
├── java/com/amirbahadoramiri/applenotebook/
│   ├── views/activities/main|note|settings|about/
│   ├── views/bases/          # BaseActivity, BaseContract
│   ├── models/               # Note (Room @Entity)
│   ├── adapter/              # NoteAdapter
│   └── tools/                # roomdb, sharedhelper, logger, packager, text, copy_helper
├── java/com/civitasv/ioslike/  # iOS-style dialogs
└── res/ (layout, drawable, font, values, xml)
```

- `AppManager` — custom `Application` class from the manifest.
- `MainActivity` — notes list, search bar, iOS menu (`DialogBottom`).
- `NoteActivity` — editor with iOS status bar, save/share/delete menu.
- `SettingsActivity` — background picker + text-size stepper.
- `AboutActivity` — version, contact and store links.
- `NoteDao` / `RoomDB` — Room database layer with RxJava types.

## 🚀 Build & Run

1. Clone the repo:
```bash
git clone https://github.com/AmirBahadorAmiri/AppleNote.git
```
2. Open the folder in Android Studio.
3. Let Gradle sync finish.
4. Run on an emulator or device (minSdk 26):
```bash
./gradlew installDebug
```

> The app works fully offline — Internet is only needed for Gradle sync and the About-page links.

## 📋 Requirements

- Android 8.0 (API 26) or higher
- Android Studio with JDK 11 for building
- No special permissions needed — notes stay on-device

## 🤝 Contributing

Contributions are welcome! Please open an issue with clear reproduction steps (device, Android version, what you did, what you expected) before sending big changes.

---
Made with ❤️ by AmirBahador Amiri
