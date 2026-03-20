# 💱 CurrencyConverter KMP

A modern Kotlin Multiplatform (KMP) mobile application that provides real-time currency conversion.
This project demonstrates a shared-logic architecture where the core business logic, networking, and
dependency injection are unified, while the UI remains native to each platform.

<img width="300" height="600" alt="android-screenshot" src="https://github.com/user-attachments/assets/10f521b8-6734-43dd-9581-2d14abb76f17" />
<img width="300" height="600" alt="iOS-screenshot" src="https://github.com/user-attachments/assets/f5c29f5a-e413-4be8-8709-c3bd02926224" />

https://github.com/user-attachments/assets/03ea89b7-a514-4724-b63e-0a25a782a58c
[android-demo.webm](https://github.com/user-attachments/assets/11c4f124-103e-4d56-a745-0333a199f537)


---

## 🚀 Tech Stack

- **Shared Logic**: Kotlin Multiplatform (KMP)
- **Android UI Layer**: Android: Jetpack Compose
- **iOS UI Layer**: SwiftUI
- **Networking**: Ktor 3.0 (with Content Negotiation & Serialization)
- **Dependency Injection**: Koin 4.0
- **Architecture**: MVVM (Model-View-ViewModel)
- **Concurrency & Interop**: SKIE (For seamless Kotlin Coroutines/Flow to Swift Async/Await
  conversion)
- **Build Config**: BuildKonfig for secure API key management

---

## 📂 Project Structure

- `shared`: The heart of the app. Contains the API client, data models, repositories, and
  ViewModels
    - `commonMain`: Shared business logic
    - `androidMain / iosMain`: Platform-specific implementations (e.g., Ktor engines)
- `composeApp`: The Android-specific entry point and Jetpack Compose UI
- `iosApp`: The Xcode project for the SwiftUI application

## 🛠️ Setup & Configuration

1. API Key Setup. This project uses the `ExchangeRate-API`. Obtain an API key from their [ExchangeRate-API](https://www.exchangerate-api.com/).
2. Create a `local.properties` file in your root directory
3. Add your key: `EXCHANGE_API_KEY=your_api_key_here`
4. Building the Project! Android: Open in Android Studio and run the composeApp configuration. iOS: Open the iosApp/iosApp.xcworkspace in Xcode or run via the KMP plugin in Android Studio.
