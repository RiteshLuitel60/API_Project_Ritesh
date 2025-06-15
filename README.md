# API Project Ritesh

An Android application that demonstrates API integration and modern Android development practices. The app allows users to explore famous architectural landmarks through a clean and intuitive interface.

## Features

- **Authentication**: Secure login system with username/password authentication
- **Dashboard**: Displays a list of famous architectural landmarks
  - Shows building name and architect for each entry
  - Clean card-based UI design
  - Clickable items for detailed view
- **Detailed View**: Comprehensive information about each architectural landmark
  - Building name
  - Architect
  - Location
  - Year completed
  - Architectural style
  - Height (in meters)
  - Detailed description

## Technical Stack

- **Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **Dependencies**:
  - Hilt for dependency injection
  - Retrofit for API communication
  - Coroutines for asynchronous operations
  - ViewBinding for view access
  - Material Design components for UI

## Project Structure

```
app/
├── data/
│   ├── api/           # API service and network related code
│   ├── model/         # Data models
│   └── repository/    # Repository layer for data operations
├── di/                # Dependency injection modules
├── ui/
│   ├── dashboard/     # Dashboard screen implementation
│   ├── details/       # Details screen implementation
│   └── login/         # Login screen implementation
└── viewmodel/         # ViewModels for each screen
```

## API Integration

The app integrates with a REST API that provides:
- Authentication endpoint for user login
- Dashboard endpoint that returns architectural landmark data
- Each landmark includes comprehensive details like name, architect, location, etc.

## Prerequisites

Before you begin, ensure you have the following installed:
- Android Studio Hedgehog (2023.1.1) or newer
- JDK 17 or newer
- Android SDK (API Level 35)
- Git

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/API_Project_Ritesh.git
cd API_Project_Ritesh
```

### 2. Project Setup
1. Open Android Studio
2. Select "Open an Existing Project"
3. Navigate to and select the cloned project directory
4. Wait for the project to sync and download dependencies

### 3. Build Configuration
The project uses Gradle with Kotlin DSL. Key build configurations:
- Minimum SDK: 26 (Android 8.0)
- Target SDK: 35
- Java/Kotlin version: 17
- Build tools: Latest Android Gradle Plugin

### 4. Dependencies
The project uses the following major dependencies:
- **Core Android**: androidx.core.ktx, appcompat, material, activity, constraintlayout
- **Navigation**: androidx.navigation (2.7.7)
- **Dependency Injection**: Hilt (2.50)
- **Networking**: Retrofit (2.9.0), OkHttp (4.12.0)
- **Coroutines**: kotlinx-coroutines (1.7.3)
- **Architecture Components**: ViewModel, LiveData (2.7.0)
- **Testing**: JUnit, Mockito, Espresso

### 5. Building the Project
1. In Android Studio, click "Build > Make Project" or press Ctrl+F9 (Cmd+F9 on macOS)
2. Alternatively, use the command line:
```bash
# On Windows
.\gradlew.bat build

# On macOS/Linux
./gradlew build
```

### 6. Running the Application
1. Connect an Android device or start an emulator
2. In Android Studio:
   - Click the "Run" button (green play icon)
   - Select your target device
   - Click "OK"
3. Alternatively, use the command line:
```bash
# On Windows
.\gradlew.bat installDebug

# On macOS/Linux
./gradlew installDebug
```

### 7. Testing Credentials
For testing the application, use the following credentials:
- Username: test_user
- Password: test_password

### Troubleshooting
If you encounter any issues:
1. Ensure all prerequisites are installed correctly
2. Sync project with Gradle files (File > Sync Project with Gradle Files)
3. Clean and rebuild the project (Build > Clean Project, then Build > Rebuild Project)
4. Check that your Android SDK and build tools are up to date
5. Verify that you have accepted all necessary SDK licenses

## Architecture Data

The app displays information about famous architectural landmarks including:
- Eiffel Tower (Paris, France)
- Taj Mahal (Agra, India)
- Sydney Opera House (Sydney, Australia)
- Fallingwater (Pennsylvania, USA)
- Burj Khalifa (Dubai, UAE)
- Guggenheim Museum Bilbao (Bilbao, Spain)
- Pantheon (Rome, Italy)

Each landmark entry includes detailed information about its architectural significance, history, and specifications.

## Contributing

Feel free to submit issues and enhancement requests.

## License

This project is licensed under the MIT License - see the LICENSE file for details. 