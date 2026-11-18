# Curbox DIY validation

Recorded 2026-11-17. Upstream base: `09958035ab6f444b4d9ceae0627dad37221425ad`; schedule-copy feature: `b113139aa69d919e563fedbc5b8ade678e5ca41e`.

## Scope

The standalone checkout is `/home/acore/proj/android/curbox-android`. The `diy` branch contains schedule copying and the Material 3 UI cleanup. The original AppBlock workspace retains historical task records and a forwarding build script. Java, Android SDK, Gradle, caches, and the existing debug signing key are private to this project under `build/tools/`; no global Java, GitHub authentication, or Codex configuration was changed.

Removed all entry points for developer biography, donations, social invitations, other-app advertising, and extension promotions, including delayed launch and onboarding prompts. Settings retains language, usage tracking, backup, diagnostics, documentation, and a source/license link. Source license and copyright notices are preserved.

UI changes use existing Material 3 Views: schedule cards, outlined time buttons, modern group switches, readable tool descriptions, and content laid out above bottom navigation. No dependencies, Compose, storage migration, blocking detection, or permissions automation were added.

## Automated checks

Command:

```sh
./scripts/build :app:testFdroidDebugUnitTest --tests '*DayScheduleCopyTest' \
  :app:assembleFdroidDebug :app:assembleFullDebug :app:assemblePlaystoreDebug
```

- Schedule-copy tests: 4 passed, 0 failures or errors.
- F-Droid, Full, and Play Store debug builds: passed.
- XML layouts parse successfully; `git diff --check` passes.
- All three delivery APK signatures are checked with Android `apksigner`. Package/version, certificate, file sizes, and SHA256 hashes are recorded in `build/delivery/manifest.json`.
- Build logs: `build/ui-build.log` and `build/final-ui-build.log`. The final run includes the last Settings layout adjustment.

Known baseline: the earlier full F-Droid suite had 198 tests with one failure in `ScriptLanguageTest.matchesRegexSupportsCommonFlags`. The untouched upstream suite had 194 tests and the identical failure (expected true/true, actual false/false). This UI-only change does not modify the script engine; the full suite was not repeated. Existing Kotlin/KSP compatibility, Java 8 target, and native-symbol warnings remain.

## Device status

On 2026-11-18, the F-Droid DIY APK was installed successfully on Xiaomi 15 running Android 16. ADB reported `Success`; package manager confirmed `neth.iecal.curbox.debug`, version `7-fdroid-debug` (code 7). Launch returned `Status: ok`, and the app process and resumed activity were confirmed. The original `neth.iecal.curbox` package remains installed. No original app data or permission settings were changed. Visual review, schedule-copy UI testing, and blocking verification remain pending.

The delivery app is **Curbox DIY**, `neth.iecal.curbox.debug`. It has separate data and permissions from official Curbox. Preserve the official installation and do not enable strict mode while validating the DIY build.

After initial setup, check the Settings and Tools screens, then copy Monday's ranges to Tuesday–Friday, save and reopen the group. Change Tuesday and verify Monday/Wednesday remain independent; cancel a copy and verify no destinations change. Check disabled destinations and replacement of existing ranges, light/dark appearance, large text, and scrolling above navigation.

## GitHub ownership

The intended repository is https://github.com/carpediemtal/curbox-android. Git uses the existing `github-carpediemtal` SSH identity and repository-local commit identity. The wrong-account fork at `acore2026/curbox-android` was created in the preceding task; its deletion was requested but GitHub rejected the API call with HTTP 403 because the stored token lacks `delete_repo`. On 2026-11-18 the user said they will remove the old fork themselves. No further deletion attempts are planned; removal has not been independently verified.
