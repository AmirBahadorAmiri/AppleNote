# اپل‌نوت (AppleNote)

> 🌍 [English version](./README.md)

دفترچه یادداشت اندرویدی با حال‌وهوای نوت iOS، ساخته‌شده با جاوا و معماری MVP — بهترین تمرین برای یادگیری MVP در اندروید.

[![Platform](https://img.shields.io/badge/Platform-Android-green)](https://developer.android.com)
[![Language](https://img.shields.io/badge/Language-Java-orange)](https://www.java.com)
[![minSdk](https://img.shields.io/badge/minSdk-26-blue)](https://developer.android.com)
[![Version](https://img.shields.io/badge/Version-1.2.2-yellow)](https://myket.ir/app/com.amirbahadoramiri.applenotebook)
[![Package](https://img.shields.io/badge/Myket-com.amirbahadoramiri.applenotebook-red)](https://myket.ir/app/com.amirbahadoramiri.applenotebook)

## 📥 دانلود

**[دانلود از مایکت](https://myket.ir/app/com.amirbahadoramiri.applenotebook)**

[![دانلود از مایکت](https://img.shields.io/badge/Myket-دانلود-red)](https://myket.ir/app/com.amirbahadoramiri.applenotebook)

## ✨ امکانات

### 📝 یادداشت‌ها
- ساخت، ویرایش و حذف یادداشت در `NoteActivity`
- لیست یادداشت‌ها با `RecyclerView` و `NoteAdapter` در `MainActivity`
- جست‌وجوی زنده در عنوان با `MainActivityPresenter.onSearch()` (کوئری `LIKE` در `NoteDao.search()`)
- حذف همه یادداشت‌ها با دیالوگ تایید (`TeleDialogDouble`) از طریق `MainActivityPresenter.onDeleteAllNote()`
- ذخیره‌سازی آفلاین با Room (کلاس‌های `RoomDB`، `NoteDao` و انتیتی `Note`)

### 🍎 ظاهر آیفونی
- شبیه‌سازی دفترچه یادداشت iOS: فونت San Francisco، آیکون‌های iOS و دیالوگ‌های (`DialogBottom`، `DialogNormal`، `DialogHud`)
- نوار وضعیت تقلبی iOS در `NoteActivity` (ساعت، باتری و وای‌فای) با اعداد فارسی از طریق `REPL`
- اشتراک‌گذاری یادداشت به‌صورت متن با `Intent.ACTION_SEND` (متد `NoteActivity.shareText()`)

### ⚙️ شخصی‌سازی
- ۵ پس‌زمینه یادداشت (`bg0` تا `bg4`) با ذخیره‌سازی در `SharedHelper` از `SettingsActivity`
- تنظیم اندازه فونت ویرایشگر (۱۲ تا ۲۰) با `SharedHelper` در `SettingsActivity`
- اعمال زنده تنظیمات در `NoteActivity.loadSettings()` و `onActivityResult()`

### ℹ️ درباره برنامه
- نمایش نسخه برنامه با `Packager` در `AboutActivity`
- لینک‌های سریع: مخزن گیت‌هاب، گیت‌هاب توسعه‌دهنده، ایمیل و صفحه نظر مایکت

## 🛠 تکنولوژی‌ها

| بخش | ابزار |
|---|---|
| زبان | Java 11 |
| معماری | MVP (هر صفحه: `*Contract` + `*Presenter` + `*Activity`، با `BaseActivity` و `BaseContract`) |
| دیتابیس | Room نسخه 2.8.4 |
| برنامه‌نویسی واکنشی | RxJava 3 و RxAndroid 3 |
| رابط کاربری | AppCompat، Material، ConstraintLayout |
| دیالوگ‌ها | دیالوگ‌های iOS-like و TelegramDialog نسخه 1.3.1 |
| تنظیمات محلی | `SharedHelper` |
| بیلد | AGP نسخه 9.3.1، Gradle نسخه KTS، فایل `libs.versions.toml` |

## 📁 ساختار پروژه

```text
app/src/main/
├── java/com/amirbahadoramiri/applenotebook/
│   ├── views/activities/main|note|settings|about/
│   ├── views/bases/          # BaseActivity, BaseContract
│   ├── models/               # Note (انتیتی Room)
│   ├── adapter/              # NoteAdapter
│   └── tools/                # roomdb, sharedhelper, logger, packager, text, copy_helper
├── java/com/civitasv/ioslike/  # دیالوگ‌های استایل iOS
└── res/ (layout, drawable, font, values, xml)
```

- `AppManager` — کلاس `Application` معرفی‌شده در مانیفست.
- `MainActivity` — لیست یادداشت‌ها، نوار جست‌وجو و منوی iOS.
- `NoteActivity` — ویرایشگر با نوار وضعیت iOS و منوی ذخیره/حذف/اشتراک.
- `SettingsActivity` — انتخاب پس‌زمینه و اندازه متن.
- `AboutActivity` — نسخه برنامه و لینک‌های تماس و فروشگاه.
- `NoteDao` و `RoomDB` — لایه دیتابیس Room با تایپ‌های RxJava.

## 🚀 اجرا و بیلد

1. کلون کردن مخزن:
```bash
git clone https://github.com/AmirBahadorAmiri/AppleNote.git
```
2. باز کردن پوشه در اندروید استودیو.
3. صبر کنید تا Gradle sync تمام شود.
4. اجرا روی شبیه‌ساز یا گوشی (حداقل اندروید ۸):
```bash
./gradlew installDebug
```

> برنامه کاملاً آفلاین کار می‌کند — اینترنت فقط برای Gradle sync و لینک‌های صفحه درباره لازم است.

## 📋 پیش‌نیازها

- اندروید ۸ (API 26) یا بالاتر
- اندروید استودیو با JDK 11 برای بیلد
- بدون نیاز به دسترسی خاص — یادداشت‌ها روی خود گوشی می‌مانند

## 🤝 مشارکت

مشارکت شما خوشحال‌مان می‌کند! لطفاً برای هر مشکل، مراحل بازتولید (مدل گوشی، نسخه اندروید، کاری که کردید و چیزی که انتظار داشتید) را در ایشو بنویسید.

---
ساخته‌شده با ❤️ توسط امیربهادر امیری
