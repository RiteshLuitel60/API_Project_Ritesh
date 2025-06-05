# NIT3213 Final Assignment - Technical Documentation

## Project Structure and File Descriptions

### 1. Application Setup Files

#### `MyApplication.kt`
- **Location**: `app/src/main/java/com/example/api_project_ritesh/`
- **Purpose**: Application class with Hilt integration
- **Key Features**:
  - Annotated with `@HiltAndroidApp` for dependency injection
  - Entry point for the application
  - Initializes Hilt components

#### `build.gradle.kts` (Project Level)
- **Location**: Root directory
- **Purpose**: Project-level build configuration
- **Key Features**:
  - Defines project-wide repositories
  - Configures Hilt plugin
  - Sets up build script dependencies

#### `build.gradle.kts` (App Level)
- **Location**: `app/`
- **Purpose**: App-level build configuration
- **Key Features**:
  - Defines app dependencies
  - Configures Android build settings
  - Sets up Kotlin and Hilt plugins
  - Configures SDK versions and build tools

### 2. Data Layer

#### `ApiModels.kt`
- **Location**: `app/src/main/java/com/example/api_project_ritesh/data/model/`
- **Purpose**: Data models for API communication
- **Classes**:
  - `LoginRequest`: Username and password for authentication
  - `LoginResponse`: Contains keypass from successful login
  - `DashboardResponse`: List of entities and total count
  - `Entity`: Individual entity data structure

#### `ApiService.kt`
- **Location**: `app/src/main/java/com/example/api_project_ritesh/data/api/`
- **Purpose**: Retrofit interface for API calls
- **Endpoints**:
  - `POST footscray/auth`: Login endpoint
  - `GET dashboard/{keypass}`: Dashboard data endpoint
- **Features**:
  - Uses Retrofit annotations
  - Defines suspend functions for coroutines
  - Handles API responses

#### `ApiRepository.kt`
- **Location**: `app/src/main/java/com/example/api_project_ritesh/data/repository/`
- **Purpose**: Repository pattern implementation
- **Features**:
  - Handles API calls through ApiService
  - Provides clean API for ViewModels
  - Implements error handling
  - Uses Result type for success/failure

### 3. Dependency Injection

#### `NetworkModule.kt`
- **Location**: `app/src/main/java/com/example/api_project_ritesh/di/`
- **Purpose**: Hilt module for network dependencies
- **Components**:
  - `OkHttpClient`: HTTP client with logging
  - `Retrofit`: API client setup
  - `ApiService`: API interface implementation
- **Features**:
  - Singleton scoped dependencies
  - Base URL configuration
  - Interceptor setup

### 4. ViewModels

#### `LoginViewModel.kt`
- **Location**: `app/src/main/java/com/example/api_project_ritesh/viewmodel/`
- **Purpose**: Manages login screen logic
- **Features**:
  - Handles login requests
  - Manages loading state
  - Provides login result to UI
  - Uses coroutines for async operations

#### `DashboardViewModel.kt`
- **Location**: `app/src/main/java/com/example/api_project_ritesh/viewmodel/`
- **Purpose**: Manages dashboard screen logic
- **Features**:
  - Loads dashboard data
  - Manages entity selection
  - Handles loading states
  - Provides data to RecyclerView

### 5. UI Layer

#### Activities

##### `LoginActivity.kt`
- **Location**: `app/src/main/java/com/example/api_project_ritesh/ui/login/`
- **Purpose**: Login screen implementation
- **Features**:
  - User input handling
  - Error display
  - Navigation to dashboard
  - Loading state management

##### `DashboardActivity.kt`
- **Location**: `app/src/main/java/com/example/api_project_ritesh/ui/dashboard/`
- **Purpose**: Dashboard screen implementation
- **Features**:
  - RecyclerView setup
  - Entity list display
  - Navigation to details
  - Error handling

##### `DetailsActivity.kt`
- **Location**: `app/src/main/java/com/example/api_project_ritesh/ui/details/`
- **Purpose**: Entity details screen
- **Features**:
  - Displays full entity information
  - Handles intent extras
  - Clean layout presentation

#### Adapters

##### `EntityAdapter.kt`
- **Location**: `app/src/main/java/com/example/api_project_ritesh/ui/dashboard/`
- **Purpose**: RecyclerView adapter for entities
- **Features**:
  - ListAdapter implementation
  - ViewHolder pattern
  - Click handling
  - Efficient updates with DiffUtil

### 6. Layouts

#### `activity_login.xml`
- **Location**: `app/src/main/res/layout/`
- **Purpose**: Login screen layout
- **Components**:
  - TextInputLayout for username
  - TextInputLayout for password
  - Login button
  - Progress bar
  - Error text view

#### `activity_dashboard.xml`
- **Location**: `app/src/main/res/layout/`
- **Purpose**: Dashboard screen layout
- **Components**:
  - RecyclerView
  - Progress bar
  - Error text view

#### `activity_details.xml`
- **Location**: `app/src/main/res/layout/`
- **Purpose**: Details screen layout
- **Components**:
  - CardView for entity details
  - Text views for properties
  - Scrollable layout

#### `item_entity.xml`
- **Location**: `app/src/main/res/layout/`
- **Purpose**: RecyclerView item layout
- **Components**:
  - CardView for each entity
  - Text views for properties

### 7. Testing

#### `LoginViewModelTest.kt`
- **Location**: `app/src/test/java/com/example/api_project_ritesh/viewmodel/`
- **Purpose**: Unit tests for LoginViewModel
- **Features**:
  - Tests successful login
  - Tests failed login
  - Tests loading states
  - Uses Mockito for mocking
  - Uses Coroutines test utilities

### 8. Configuration Files

#### `AndroidManifest.xml`
- **Location**: `app/src/main/`
- **Purpose**: App configuration
- **Features**:
  - Activity declarations
  - Internet permission
  - Application class declaration
  - Theme configuration

## How It All Works Together

1. **Application Start**:
   - `MyApplication` initializes Hilt
   - Dependencies are injected through Hilt modules

2. **Login Flow**:
   - User enters credentials in `LoginActivity`
   - `LoginViewModel` processes the request
   - `ApiRepository` makes the API call
   - On success, user is navigated to dashboard

3. **Dashboard Flow**:
   - `DashboardActivity` receives keypass
   - `DashboardViewModel` loads data
   - `EntityAdapter` displays entities
   - Clicking an entity navigates to details

4. **Details Flow**:
   - `DetailsActivity` receives entity data
   - Displays full entity information
   - Uses clean layout for presentation

## Key Design Patterns Used

1. **MVVM Architecture**:
   - ViewModels for business logic
   - Activities/Fragments for UI
   - LiveData for reactive updates

2. **Repository Pattern**:
   - Clean API for data access
   - Separation of concerns
   - Error handling

3. **Dependency Injection**:
   - Hilt for DI
   - Singleton scoping
   - Clean dependency management

4. **Adapter Pattern**:
   - RecyclerView adapter
   - ViewHolder pattern
   - Efficient list updates

## Best Practices Implemented

1. **Code Organization**:
   - Clear package structure
   - Separation of concerns
   - Clean architecture principles

2. **Error Handling**:
   - Proper error states
   - User feedback
   - Network error handling

3. **Performance**:
   - Efficient RecyclerView
   - Coroutines for async operations
   - Proper lifecycle management

4. **Testing**:
   - Unit tests
   - Mocking
   - Test utilities

This documentation provides a comprehensive overview of the project's structure and implementation. Each component is designed to work together seamlessly while maintaining clean architecture principles and following Android best practices. 