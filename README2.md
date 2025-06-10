# API Project Ritesh - Technical Documentation

## Overview

This document provides detailed technical information about the API Project Ritesh Android application, focusing on implementation details, architecture decisions, and development guidelines.

## System Architecture

### MVVM Architecture Implementation

The application follows the MVVM (Model-View-ViewModel) architecture pattern:

1. **Model Layer**
   - `data/model/`: Contains data classes and entities
   - `data/repository/`: Implements data operations and API interactions
   - `data/api/`: Defines API interfaces and network configurations

2. **View Layer**
   - `ui/login/`: Login screen implementation
   - `ui/dashboard/`: Dashboard screen with RecyclerView
   - `ui/details/`: Detailed view of architectural landmarks
   - Uses ViewBinding for view access
   - Implements Material Design components

3. **ViewModel Layer**
   - `viewmodel/`: Contains ViewModels for each screen
   - Manages UI state and business logic
   - Handles data operations through repositories

### Dependency Injection

The project uses Hilt for dependency injection:

```kotlin
@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: ApiRepository
) : ViewModel()
```

Key components:
- `@HiltAndroidApp`: Application class annotation
- `@AndroidEntryPoint`: Activity and Fragment annotations
- `@Module` and `@Provides`: Dependency provision
- `@Inject`: Constructor injection

## API Integration

### Base Configuration
```kotlin
private const val BASE_URL = "https://nit3213api.onrender.com/"
```

### API Endpoints

1. **Authentication**
   ```kotlin
   @POST("sydney/auth")
   suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
   ```

2. **Dashboard Data**
   ```kotlin
   @GET("dashboard/{keypass}")
   suspend fun getDashboard(@Path("keypass") keypass: String): Response<DashboardResponse>
   ```

### Data Models

1. **Login**
   ```kotlin
   data class LoginRequest(
       val username: String,
       val password: String
   )

   data class LoginResponse(
       val keypass: String
   )
   ```

2. **Architecture Data**
   ```kotlin
   data class DashboardResponse(
       val entities: List<Entity>,
       val entityTotal: Int
   )

   data class Entity(
       val name: String,
       val architect: String,
       val location: String,
       val yearCompleted: Int,
       val style: String,
       val height: Int,
       val description: String
   )
   ```

## UI Implementation

### Dashboard Screen

1. **Layout Structure**
   - RecyclerView with MaterialCardView items
   - Each item displays:
     - Building name (primary text)
     - Architect (secondary text)
   - Progress indicator for loading state
   - Error message display

2. **Adapter Implementation**
   ```kotlin
   class EntityAdapter(
       private val onItemClick: (Entity) -> Unit
   ) : ListAdapter<Entity, EntityViewHolder>(EntityDiffCallback())
   ```

3. **Item Layout**
   ```xml
   <MaterialCardView>
       <LinearLayout>
           <TextView android:id="@+id/property1TextView" />
           <TextView android:id="@+id/property2TextView" />
       </LinearLayout>
   </MaterialCardView>
   ```

### Details Screen

1. **Layout Structure**
   - NestedScrollView for scrollable content
   - MaterialCardView container
   - Organized display of:
     - Building name (24sp, bold)
     - Architect information
     - Location
     - Year completed
     - Architectural style
     - Height in meters
     - Detailed description

2. **Data Passing**
   ```kotlin
   companion object {
       const val EXTRA_ENTITY_NAME = "extra_entity_name"
       const val EXTRA_ENTITY_ARCHITECT = "extra_entity_architect"
       // ... other extras
   }
   ```

## Error Handling

1. **Network Errors**
   - Repository layer catches and wraps exceptions
   - ViewModel exposes error states
   - UI displays user-friendly error messages

2. **Data Validation**
   - Input validation in login screen
   - Null safety checks throughout the app
   - Proper error states for missing data

## Performance Considerations

1. **Image Loading**
   - Efficient image caching
   - Proper image scaling
   - Memory management

2. **List Performance**
   - RecyclerView with ViewHolder pattern
   - Efficient diffing with DiffUtil
   - Proper view recycling

3. **Memory Management**
   - Proper lifecycle management
   - Coroutine scope handling
   - Resource cleanup

## Testing Strategy

1. **Unit Tests**
   - ViewModel testing
   - Repository testing
   - Data model validation

2. **UI Tests**
   - Activity navigation
   - User interaction
   - Error state handling

## Build Configuration

### Dependencies

```gradle
dependencies {
    // Core Android
    implementation 'androidx.core:core-ktx:1.12.0'
    implementation 'androidx.appcompat:appcompat:1.6.1'
    
    // UI Components
    implementation 'com.google.android.material:material:1.11.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    
    // Architecture Components
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0'
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.7.0'
    
    // Dependency Injection
    implementation 'com.google.dagger:hilt-android:2.50'
    kapt 'com.google.dagger:hilt-compiler:2.50'
    
    // Networking
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:4.12.0'
    
    // Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3'
}
```

## Development Guidelines

1. **Code Style**
   - Follow Kotlin coding conventions
   - Use meaningful variable and function names
   - Document public APIs
   - Keep functions focused and small

2. **Architecture Rules**
   - ViewModels should not hold View references
   - Repositories should be the single source of truth
   - Use LiveData for UI state management
   - Implement proper error handling

3. **UI Guidelines**
   - Follow Material Design principles
   - Support different screen sizes
   - Implement proper error states
   - Provide loading indicators

## Future Improvements

1. **Planned Features**
   - Image gallery for each landmark
   - Search functionality
   - Favorites system
   - Offline support

2. **Technical Improvements**
   - Implement caching
   - Add analytics
   - Improve error handling
   - Add more unit tests

## Troubleshooting

Common issues and solutions:

1. **API Connection Issues**
   - Check internet connectivity
   - Verify API endpoint
   - Check authentication token

2. **UI Rendering Problems**
   - Verify data binding
   - Check layout constraints
   - Validate data models

3. **Performance Issues**
   - Monitor memory usage
   - Check for memory leaks
   - Optimize image loading

## Support

For technical support or questions:
1. Check the documentation
2. Review the code comments
3. Submit an issue on the repository
4. Contact the development team 