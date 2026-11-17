# Curbox DIY

Personal Android fork maintained at [carpediemtal/curbox-android](https://github.com/carpediemtal/curbox-android), based on [Curbox](https://github.com/curbox-app/curbox-android) by Nethical and contributors. This is an independently modified build, not an official Curbox release.

## Changes

- Copy an active day's time ranges to any other days. Selected days are enabled and their existing ranges replaced; the source and unselected days stay unchanged.
- Simplified Settings with language, tracking, backup, diagnostics, documentation, and source/license information.
- Removed developer promotion, donations, social invitations, other-app advertising, and extension promotions, including launch and onboarding prompts.
- Material 3 schedule cards, time buttons, updated switches, clearer tool descriptions, and navigation that keeps content above the bottom bar. Dynamic colors and light/dark themes remain supported.

To reuse Monday's schedule: open the group's **Active hours**, choose the custom schedule, tap Monday's **Copy to days**, select Tuesday through Friday, tap **Copy**, then save the group. Copying works in the shared editors for app blocking, keywords, reels, automatic DND, and grayscale. Existing validation and settings-change delays still apply.

## Build

Use JDK 21, Android SDK platform 35 / build tools 35.0.0, and the Gradle 8.13 wrapper. Set `ANDROID_HOME` to your SDK, or configure `sdk.dir` in an untracked `local.properties`.

```sh
./gradlew :app:testFdroidDebugUnitTest --tests '*DayScheduleCopyTest'
./gradlew :app:assembleFdroidDebug :app:assembleFullDebug :app:assemblePlaystoreDebug
```

On the configured development PC, project-local tools and caches live in `build/tools/`:

```sh
./scripts/build :app:testFdroidDebugUnitTest --tests '*DayScheduleCopyTest' \
  :app:assembleFdroidDebug :app:assembleFullDebug :app:assemblePlaystoreDebug
./scripts/run adb.exe devices -l
```

These scripts do not install tools or change global configuration. The checkout is `/home/acore/proj/android/curbox-android`. Tool downloads are recorded in `local-inputs.json`; binaries, APKs, caches, logs, and keys are ignored by Git.

## Installation

The debug app is named **Curbox DIY**, package `neth.iecal.curbox.debug`. It installs alongside official Curbox with separate settings and permissions. The existing app is not replaced or uninstalled. Do not use the upstream `installAndGrantAccessibility*` tasks: they overwrite the phone's list of enabled accessibility services.

The F-Droid debug output is `app/build/outputs/apk/fdroid/debug/app-fdroid-universal-release-unsigned.apk`. Despite that inherited filename, the debug build is signed. Verified delivery copies are placed in `build/delivery/`.

Install with `adb install -r PATH_TO_APK`. On the configured WSL PC, use `./scripts/run adb.exe` and pass a Windows path produced by `wslpath -w`. Accept Xiaomi's USB installation prompt if shown. Grant permissions to Curbox DIY through Android settings when you want to test blocking; the official app's permissions are separate.

## Verification

The four schedule-copy tests cover replacement, enabling selected days, independent copies, source preservation, and empty or disabled source handling. The full upstream suite has a previously reproduced failure in `ScriptLanguageTest.matchesRegexSupportsCommonFlags`; see [validation notes](docs/validation.md) for current results and phone-test status.

No Compose migration, storage schema changes, or accessibility detection changes are included in this UI refresh.

## License and attribution

Curbox and this fork are licensed under [GNU GPL version 3 or later](LICENSE). Original code and copyright notices are retained. The source history identifies upstream contributors and modifications. Refer to [CONTRIBUTING.md](CONTRIBUTING.md) and [CURBOX_API.md](CURBOX_API.md) for upstream development guidance and API documentation.
