# Curbox DIY project

- Work on the user's PC, WSL account `acore`, in `/home/acore/proj/android/curbox-android`. Do not execute project work on the Codex cloud host or modify connection settings.
- This checkout belongs to GitHub account `carpediemtal`. `origin` must point to `carpediemtal/curbox-android`; `upstream` is `curbox-app/curbox-android`. Do not publish to `acore2026` or switch global authentication.
- Read `Readme.md`, `docs/validation.md`, and the existing local `agents.md` when present. Preserve the upstream GPL license and copyright notices.
- Keep Kotlin, Views, Fragments, ViewBinding, and Material 3. Do not add Compose or change the database schema for cosmetic work.
- Use `./scripts/build` and `./scripts/run` with the private tools under `build/tools/`. A fresh machine can use the standard Gradle wrapper and its own JDK/SDK as documented.
- Keep APKs, signing keys, logs, tool binaries, and caches out of Git. Register local inputs in `local-inputs.json`.
- Debug builds use `neth.iecal.curbox.debug`, named Curbox DIY. Preserve the official app and its data. Do not use upstream install-and-grant tasks that overwrite enabled accessibility services.
- Keep schedule copy behind existing settings-change gates and preserve group Save behavior. Build all three debug flavors after shared source/resource changes and run the focused schedule-copy tests.
- Distinguish builds, installation, launch, UI testing, and blocking verification. Never claim device testing while the phone is disconnected.
- Commit task changes and push to the configured personal fork. Do not publish upstream PRs or releases unless requested.
