# 🐾 Logcat News 

A simple Android demo application built with **Jetpack Compose**, showcasing a list of news items and a detail screen.


## 🧱 Tech Stack

- **Jetpack Compose** – Modern declarative UI toolkit
- **Navigation Component** – For in-app navigation
- **MVI Architecture** – Model-View-Intent pattern for state management
- **Hilt** – Dependency injection
- **Kotlin** – Primary programming language

## ⚙️ Architecture Overview

The app follows a **clean, layered MVI architecture**:

- **Data Layer**: Fetches and parses the raw data
- **Domain Layer**: Contains business rules and use cases
- **Presentation Layer**: Contains `ViewModel`, UI state, and Composables for rendering UI

## 💾 Data Flow
Data Layer -> Domain Layer -> Presentation Layer
- Data Source provides raw data (DTOs).
- Use Cases transform DTOs to domain models.
- ViewModels convert domain models to UI state.
- Composables render UI state and send intents back to ViewModels.

## 🚀 Key Features

- Loads news articles from a local JSON file
- Displays categorized list of news
- Navigate to a detail screen on item click
- Filters news by type

## 📷 Screenshots

  <img src="https://i.imgur.com/VO1Mpwd.png" width="300" />
  <img src="https://i.imgur.com/AQ7LJdM.png" width="300" />
  <img src="https://i.imgur.com/I2St9GG.png" width="500" />
