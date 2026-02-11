# Observer Pattern - Real-time News Subscription Service

## Overview

A scalable real-time news subscription system that demonstrates the **Observer Pattern** in action. The system enables a media company to publish breaking news and instantly notify all subscribed users while maintaining loose coupling between the news agency and subscribers.

## Key Features

### 📰 Breaking News Publishing
- **Central Hub**: NewsAgency acts as the central news publisher
- **Instant Notification**: All subscribed users receive updates immediately when news is published
- **Real-Time Delivery**: No polling required; push-based notification system

### 👥 Subscription Management
- **Dynamic Subscriptions**: Users can subscribe and unsubscribe at any time
- **Non-Disruptive**: Subscription changes don't affect other subscribers
- **Flexible Preferences**: Support for dynamic preference modifications
- **Easy Join/Leave**: Simple mechanisms for users to enter or exit the service

### 🔌 Decoupled Communication
- **Loose Coupling**: NewsAgency and Subscribers are independent of each other
- **Push-Based Model**: Subscribers don't pull for updates; they receive notifications
- **Observer Pattern**: Clean separation of concerns between publishers and subscribers
- **Extensibility**: Easy to add new subscriber types without modifying the agency

## System Architecture

### Design Pattern
This system implements the **Observer Pattern** to:
- Establish a one-to-many relationship between the NewsAgency and Subscribers
- Notify multiple subscribers automatically when news is published
- Maintain loose coupling and high cohesion
- Enable dynamic subscription management at runtime

## Component Structure

### 1. Subject Interface
**Class**: `Subject` (Observable)

Defines the contract for managing observers:
```
+ attach(observer: Observer): void       // Subscribe an observer
+ detach(observer: Observer): void       // Unsubscribe an observer
+ notifyObservers(): void                // Notify all observers
```

### 2. Concrete Subject
**Class**: `NewsAgency` (implements Subject)

The central hub for publishing news:
```
- subscribers: List<Observer>
- latestNews: String
- timestamp: LocalDateTime

+ attach(observer: Observer): void
+ detach(observer: Observer): void
+ notifyObservers(): void
+ publishNews(newsContent: String): void
+ getLatestNews(): String
```

**Responsibilities:**
- Maintain a list of subscribed users
- Publish breaking news to all subscribers
- Manage subscription/unsubscription requests
- Trigger notifications automatically

### 3. Observer Interface
**Class**: `Observer` (Observer)

Defines the contract for subscribers:
```
+ update(newsAgency: NewsAgency): void   // Receive news updates
```

### 4. Concrete Observer
**Class**: `Subscriber` (implements Observer)

Represents individual users subscribed to the service:
```
- name: String
- email: String
- subscriptionDate: LocalDateTime
- preferences: Map<String, Boolean>

+ update(newsAgency: NewsAgency): void
+ getName(): String
+ getEmail(): String
+ setPreferences(key: String, value: Boolean): void
```

**Responsibilities:**
- Receive and handle news updates from the agency
- Store subscriber information
- Manage notification preferences
- Maintain subscription state

#### Additional Observer Types

**Class**: `NewsportalSubscriber` (implements Observer)
- Receives news for website display
- Manages news feed formatting

**Class**: `MobileAppSubscriber` (implements Observer)
- Receives news for mobile notifications
- Handles push notification delivery

**Class**: `EmailSubscriber` (implements Observer)
- Receives news for email distribution
- Formats news for email delivery

## Usage Flow

### Sequence of Events

```
1. Subscriber subscribes to NewsAgency
   └─> Subscriber.attach(observer)

2. NewsAgency publishes breaking news
   └─> NewsAgency.publishNews(newsContent)

3. NewsAgency notifies all subscribers
   └─> NewsAgency.notifyObservers()

4. Each subscriber receives update
   └─> Observer.update(newsAgency)

5. Subscriber processes the news
   └─> Display, store, or format news
```

### Code Example

```java
// Create the subject (news agency)
NewsAgency agency = new NewsAgency();

// Create observers (subscribers)
Subscriber subscriber1 = new Subscriber("Alice", "alice@example.com");
Subscriber subscriber2 = new Subscriber("Bob", "bob@example.com");
Subscriber subscriber3 = new Subscriber("Charlie", "charlie@example.com");

// Subscribe to the agency
agency.attach(subscriber1);
agency.attach(subscriber2);
agency.attach(subscriber3);

// Publish breaking news
agency.publishNews("Breaking: Major technology breakthrough announced!");

// Output:
// Alice received: Breaking: Major technology breakthrough announced!
// Bob received: Breaking: Major technology breakthrough announced!
// Charlie received: Breaking: Major technology breakthrough announced!

// Subscriber unsubscribes
agency.detach(subscriber2);

// Publish another news item
agency.publishNews("Update: Stock market hits record high");

// Output:
// Alice received: Update: Stock market hits record high
// Charlie received: Update: Stock market hits record high
// (Bob does not receive because they unsubscribed)
```

## Benefits

✅ **Loose Coupling**: NewsAgency and Subscribers are independent objects  
✅ **Dynamic Relationships**: Subscribe and unsubscribe at runtime  
✅ **Automatic Notifications**: Subscribers automatically updated when news is published  
✅ **Scalability**: Handles growing numbers of subscribers efficiently  
✅ **Extensibility**: Easy to add new subscriber types without modifying NewsAgency  
✅ **Single Responsibility**: Each class has one clear purpose  
✅ **Open/Closed Principle**: Open for extension, closed for modification  
✅ **Real-Time Updates**: Push-based notification eliminates polling overhead  

## Class Diagram

![Observer Pattern UML Diagram](https://github.com/JLNerecina/Observer-Pattern/blob/main/Observer%20Pattern%20UML.png)

```
                    ┌─────────────────┐
                    │   Observer      │ (Interface)
                    ├─────────────────┤
                    │+ update()       │
                    └─────────────────┘
                           ▲
              ┌────────────┼────────────┐
              │            │            │
         ┌────────────┐ ┌──────────┐ ┌──────────────────┐
         │ Subscriber │ │ MobileApp│ │ EmailSubscriber  │
         │            │ │ Subscr.  │ │                  │
         └────────────┘ └──────────┘ └──────────────────┘


                    ┌──────────────────┐
                    │  Subject         │ (Interface)
                    ├──────────────────┤
                    │+ attach()        │
                    │+ detach()        │
                    │+ notifyObservers()
                    └──────────────────┘
                           ▲
                           │
                   ┌───────────────────┐
                   │  NewsAgency       │
                   ├───────────────────┤
                   │- subscribers[]    │
                   │- latestNews       │
                   ├───────────────────┤
                   │+ publishNews()    │
                   │+ attach()         │
                   │+ detach()         │
                   │+ notifyObservers()│
                   └───────────────────┘
```

## Supported Features

### ✨ Current Implementation

| Feature | Status | Description |
|---------|--------|-------------|
| Subscription Management | ✅ | Add/remove subscribers dynamically |
| Breaking News Publishing | ✅ | Publish news to all subscribers |
| Real-Time Notifications | ✅ | Instant updates to all observers |
| Subscriber List Management | ✅ | Track active subscribers |
| Loose Coupling | ✅ | Independent subject and observers |
| Multiple Observer Types | ✅ | Support different subscriber implementations |

### 🚀 Future Enhancements

| Feature | Description |
|---------|-------------|
| News Categorization | Filter news by category (sports, politics, tech) |
| Personalized Subscriptions | Allow subscribers to choose news categories |
| Priority Notifications | Different delivery mechanisms based on news importance |
| Notification History | Archive of sent notifications |
| Subscriber Analytics | Track engagement and preferences |
| Batch Notifications | Aggregate news for periodic delivery |

## Design Principles

### Observer Pattern Benefits in This System

1. **Single Responsibility Principle**
   - NewsAgency: Manages publishers
   - Subscriber: Handles notifications
   - Each class has one reason to change

2. **Open/Closed Principle**
   - Open for extension: Add new subscriber types
   - Closed for modification: No changes to NewsAgency needed

3. **Dependency Inversion Principle**
   - Both high-level and low-level modules depend on abstractions (Subject/Observer)
   - No direct dependencies between concrete classes

4. **Loose Coupling**
   - NewsAgency doesn't know specific subscriber implementations
   - Subscribers don't know about other subscribers
   - Communication happens through defined interfaces

## Performance Considerations

### Scalability
- **Time Complexity**: O(n) for publishing news (n = number of subscribers)
- **Space Complexity**: O(n) for maintaining subscriber list
- **Optimization**: Can be improved with observer groups or priority-based notifications

### Best Practices
- Use weak references for observers in memory-constrained environments
- Implement observer removal in case of notification failures
- Consider asynchronous notifications for large subscriber bases
- Monitor observer count and notification frequency

## Technical Implementation

- **Pattern**: Observer Pattern
- **Architecture**: Push-based notification system
- **Scalability**: Handles growing subscriber base efficiently
- **Coupling**: Loose coupling via interface-based communication
- **Extensibility**: Support for multiple observer types

## Getting Started

### Prerequisites
- Java 8 or higher (or equivalent language implementation)
- Basic understanding of design patterns and OOP concepts
- Familiarity with event-driven programming

### Running the System

1. **Create NewsAgency**:
   ```java
   NewsAgency agency = new NewsAgency();
   ```

2. **Create Subscribers**:
   ```java
   Subscriber sub1 = new Subscriber("User Name", "email@example.com");
   ```

3. **Subscribe**:
   ```java
   agency.attach(sub1);
   ```

4. **Publish News**:
   ```java
   agency.publishNews("Breaking news content");
   ```

## Extending the System

### Adding a New Subscriber Type

Create a new observer class:

```java
public class SMSSubscriber implements Observer {
    private String phoneNumber;
    private String name;
    
    public SMSSubscriber(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public void update(NewsAgency newsAgency) {
        String news = newsAgency.getLatestNews();
        sendSMS(phoneNumber, "News: " + news);
    }
    
    private void sendSMS(String number, String message) {
        // SMS delivery implementation
    }
}
```

### Adding News Categorization

Enhance the NewsAgency:

```java
public class NewsAgency implements Subject {
    private String category;
    
    public void publishNews(String newsContent, String category) {
        this.latestNews = newsContent;
        this.category = category;
        notifyObservers();
    }
    
    public String getCategory() {
        return category;
    }
}
```



---

**Last Updated**: February 2026  
**Pattern**: Observer Pattern  
**Organization**: Media Company  
**Service**: Real-time News Subscription System
