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

## Getting Started

1. Clone the repository
2. Open the project in Android Studio
3. Build and run the application
4. Use the following credentials for testing:
   - Username: (your test username)
   - Password: (your test password)

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