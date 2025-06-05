# NIT3213 Final Assignment - Android Application

This Android application demonstrates API integration, user interface design, and Android development best practices. The app features three main screens: Login, Dashboard, and Details, interacting with the 'vu-nit3213-api'.

## Features

- **Login Screen**: Secure authentication using student credentials
- **Dashboard Screen**: Displays a list of entities in a RecyclerView
- **Details Screen**: Shows detailed information about selected entities
- **Modern Architecture**: Uses MVVM, Clean Architecture, and Dependency Injection
- **Unit Tests**: Comprehensive testing for critical components

## Technical Stack

- Kotlin
- Hilt for Dependency Injection
- Retrofit for API calls
- Coroutines for asynchronous operations
- ViewModel and LiveData
- Navigation Component
- Material Design Components

## Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/api_project_ritesh/
│   │   │   ├── di/           # Dependency Injection
│   │   │   ├── data/         # Data layer
│   │   │   │   ├── api/      # API interfaces
│   │   │   │   ├── model/    # Data models
│   │   │   │   └── repository/# Repositories
│   │   │   ├── ui/           # UI layer
│   │   │   │   ├── login/
│   │   │   │   ├── dashboard/
│   │   │   │   └── details/
│   │   │   └── viewmodel/    # ViewModels
│   │   └── res/              # Resources
│   └── test/                 # Unit tests
```

## Setup Instructions

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Run the application on an emulator or physical device

## API Details

Base URL: https://nit3213api.onrender.com/

### Endpoints:
- Login: `/footscray/auth` (POST)
- Dashboard: `/dashboard/{keypass}` (GET)

## Testing

Run unit tests using:
```bash
./gradlew test
```

## Author

Ritesh Luitel

## License

This project is for educational purposes only. 