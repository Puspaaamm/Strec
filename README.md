<div align="center">

<!-- Injected External Web Font Pipeline for Bebas Neue Rendering -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Bebas+Neue&display=swap">

<!-- Project Logo Identity Header -->
<h1 style="color: #FF0055; font-family: 'Bebas Neue', sans-serif; font-weight: 400; font-size: 84px; letter-spacing: 3px; margin-bottom: 0px; line-height: 1;">STREC</h1>
<p style="color: #666666; font-size: 11px; font-weight: bold; letter-spacing: 3px; margin-top: 5px; margin-bottom: 40px;">ULTRA 1080P 60FPS BROADCASTER</p>

[![Android Platform](https://img.shields.io/badge/Platform-Android%208.0%2B-brightgreen.svg?style=flat-square)](#)
[![Encoding Engine](https://img.shields.io/badge/Pipeline-GPU%20OpenGL-FF0055.svg?style=flat-square)](#)
[![License](https://img.shields.io/badge/License-MIT-blue.svg?style=flat-square)](#)

</div>

---

# STREC (Ultra 1080p 60FPS Broadcaster)

STREC is a high-performance, open-source Android screen broadcasting engine custom-optimized for mobile gamers and live streamers. Built on a low-latency native hardware pipeline, STREC enables seamless 1080p 60FPS system capture and audio transmission directly to RTMP servers (such as YouTube Live, Twitch, or custom server setups) with zero CPU overhead.

---

## 🚀 Key Features

* **Tuned 1080p 60FPS Streaming:** Pre-configured out of the box to push smooth, high-definition video assets at a fast 6Mbps bitrate profile.
* **Zero-Overhead GPU Architecture:** Binds directly to the device's hardware encoder using `FormatVideoEncoder.SURFACE`, bypassing standard CPU frame copy procedures to prevent performance drops during intense gaming sessions.
* **Live GPU Graphics Filters:** Injects custom real-time shader matrices (`Vivid`, `Ocean`, `Classic`, `4K Sharp`) straight through the OpenGL rendering pipeline at runtime with no impact on background frame stability.
* **Persistent Service Layer:** Utilizes a high-priority Android Foreground System Service (`mediaProjection`) to protect the active capture environment from aggressive OS background memory cleanups.
* **Premium Dark UI Theme:** Features a minimal, fully programmatic programmatic UI styled intentionally around a modern neon-accented gaming aesthetic.

---

## 🗂️ Project Architecture

The application design is modular, lightweight, and cleanly fits within standard Android subsystem boundaries without layout bloat:

```text
strec-android-streamer/
├── build.gradle (Project Level)
└── app/
    ├── build.gradle (App Level)
    └── src/
        └── main/
            ├── AndroidManifest.xml
            └── java/com/strec/stream/
                ├── MainActivity.java     # UI Thread Controls & MediaProjection Handshake
                └── StreamService.java    # Core OpenGL Rendering & Live RTMP Pipeline
